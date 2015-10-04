package kmj.webboard.action.ajax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.json.simple.JSONObject;

import kmj.webboard.action.IAction;
import kmj.webboard.action.View;
import kmj.webboard.action.Views;
import kmj.webboard.dao.IPostDao;
import kmj.webboard.model.PostVO;
import kmj.webboard.model.UserVO;
import kmj.webboard.util.BoardContext;

public class AjaxPostWrite implements IAction {
	
	static int MEM_THRESHOLD = 1 * 1024 * 1024 ; // memory
	static int MAX_FILE_SIZE = 1 * 1024 * 1024; // max files

	@Override
	public View process(BoardContext ctx, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		UserVO user = (UserVO)session.getAttribute("user");
		IPostDao postDao = ctx.getPostDao();
		
		if ( ! ServletFileUpload.isMultipartContent(request) ) {
			return Views.REDIRECT("/error.html");
		}
		File rootDir = ctx.getUploadDir();
		FileItemFactory fac = new DiskFileItemFactory(MEM_THRESHOLD, rootDir);
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setSizeMax(MAX_FILE_SIZE);
		upload.setHeaderEncoding("utf-8");
		
		String title = null, content = null;
		try {
			FileItemIterator itr = upload.getItemIterator(request);
			while ( itr.hasNext()) {
				FileItemStream item = itr.next();
				
				if ( item.isFormField()) { 
					String param = item.getFieldName();
					String value = Streams.asString(item.openStream(), "utf-8");
					System.out.println(String.format("normal form value  name:%s, value:%s", param, value));
					if ( param.equals("title")) {
						title = value;
					} else if ( param.equals("content")){
						content = value;
					}
					
				} else {
					String dirPath = null;
					
					String param = item.getFieldName().trim();
					String filename = item.getName().trim();
					String encFileName = encodeFileName(filename); // SHA-1
					
					if ( "".equals ( filename)) {
						
						continue;
					}
					System.out.println("new file : " + encFileName + "(" + filename + ") of " + param);
					File f = new File ( rootDir, encFileName );
					f.createNewFile();
					long nCopied = Streams.copy(
							item.openStream(), 
							new FileOutputStream(f),
							true);
					System.out.println(String.format("param(%s): filename; %s : %d bytes copied to %s",
							param,
							filename, 
							nCopied, 
							f.getAbsolutePath()));
				}
			}

			JSONObject root = new JSONObject();

			if ( user == null) {
				makeNoLogin(root, session);
			} else {
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
				
				IPostDao postdao =ctx.getPostDao();
				PostVO newPost = postdao.insertPost(user,title,content);
				/*
				 * List<Attachement> attatchments = makeAttachment(req);
				 */
//				PostVO newPost = postdao.insertPost(user,title,content, attachements);
				/**
				 * { successs : true, nextUrl: '/simpleboard//post/all' }
				 *  
				 *  {success : false, cause : "xxxxx" }
				 */
				
				root.put("success", Boolean.TRUE);
				root.put("nextUrl", ctx.getContextPath() + "/post/all");
				
			}
			
			return Views.JSON(root.toJSONString());
			
		} catch (FileUploadException | IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			return Views.JSON(createError());
		}
	}

	private String createError() {
		JSONObject json = new JSONObject();
		json.put("success", Boolean.FALSE);
		json.put("cause", "too_bit_file");
		return json.toJSONString();
		
	}

	/**
	 * 로그인 정보가 없을때의 응답.
	 * @param root
	 * @param session
	 */
	private void makeNoLogin(JSONObject root, HttpSession session) {
		
		root.put("success", Boolean.FALSE);
		root.put("cause", "no login info");
	}
	
	private String encodeFileName ( String fileName) {
		String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(fileName.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}
	
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}

}
