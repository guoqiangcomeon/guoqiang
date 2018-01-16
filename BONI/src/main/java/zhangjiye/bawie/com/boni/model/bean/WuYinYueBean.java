package zhangjiye.bawie.com.boni.model.bean;

/**
 * Created by 张继业 on 2018/1/12.
 */

public class WuYinYueBean {
    private String musicName;
    private String musicUrl;
    private String name;

    public WuYinYueBean(String musicName, String musicUrl, String name) {
        this.musicName = musicName;
        this.musicUrl = musicUrl;
        this.name = name;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
