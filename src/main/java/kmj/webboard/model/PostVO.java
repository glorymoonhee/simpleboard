package kmj.webboard.model;
/**
 * 사용자들이 작성한 각각의 글들을 나타내는 entity입니다.
 * 
 * @author Administrator
 *
 */
public class PostVO {
	
	private Integer seq;
	private String title;
	private String content;
	private String creationTime; // "2015-08-22 12:21:54"
	private Integer viewCount;   // 조회수
	
	private UserVO writer;

	
	public PostVO(Integer seq, String title, String content,
			String creationTime, Integer viewCount, UserVO writer) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
		this.viewCount = viewCount;
		this.writer = writer;
	}


	public Integer getSeq() {
		return seq;
	}


	public void setSeq(Integer seq) {
		this.seq = seq;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}


	public Integer getViewCount() {
		return viewCount;
	}


	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}


	public UserVO getWriter() {
		return writer;
	}


	public void setWriter(UserVO writer) {
		this.writer = writer;
	}
	
	
	
	
}
