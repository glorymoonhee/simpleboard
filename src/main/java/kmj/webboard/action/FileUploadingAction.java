package kmj.webboard.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class FileUploadingAction implements IAction {

	static int MEM_THRESHOLD = 1 * 1024 * 1024 ; // memory
	static int MAX_FILE_SIZE = 1 * 1024 * 1024; // max files
	static File rootDir =  new File("d:/tmp");
	@Override
	public View process(ServletContext ctx, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if ( ! ServletFileUpload.isMultipartContent(request) ) {
			return Views.REDIRECT("/error.html");
		}
		FileItemFactory fac = new DiskFileItemFactory(MEM_THRESHOLD, rootDir);
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setSizeMax(MAX_FILE_SIZE);
		upload.setHeaderEncoding("utf-8");
		
		try {
			FileItemIterator itr = upload.getItemIterator(request);
			while ( itr.hasNext()) {
				FileItemStream item = itr.next();
				
				if ( item.isFormField()) {
					String param = item.getFieldName();
					String value = Streams.asString(item.openStream());
					System.out.println(String.format("Not a file item. %s:%s", param, value));
				} else {
					String param = item.getFieldName().trim();
					String filename = item.getName().trim();
					if ( "".equals ( filename)) {
						continue;
					}
					System.out.println("new file : " + filename + " of " + param);
					File f = new File ( rootDir, filename );
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
			return Views.REDIRECT("/ok");
		} catch (FileUploadException | IOException e) {
			System.out.println("Error");
			e.printStackTrace();
			return Views.REDIRECT("/error");
		}
	}

}
