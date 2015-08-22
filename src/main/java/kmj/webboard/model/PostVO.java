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
    private Integer viewCount;
    private String date;
    UserVO writer;
    
    public PostVO(String title, String content, String date, UserVO writer) {
        super();
        this.title = title;
        this.content = content;
        this.date = date;
        this.writer = writer;
        viewCount = 0;
    }

    public PostVO(Integer seq, String title, String content, Integer viewCount, String date, UserVO writer) {
        super();
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.date = date;
        this.writer = writer;
    }
    
    
    
}
