package scu.cj.community.model;

/**
 * @author : lkk
 * @date : 2:28 2019/12/17
 * @creed: Talk is cheap,show me the code
 */
public class Advertisement {

    private  Integer id;
    private String title;
    private String url;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
