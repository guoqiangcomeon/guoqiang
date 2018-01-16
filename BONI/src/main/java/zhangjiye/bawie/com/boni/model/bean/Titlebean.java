package zhangjiye.bawie.com.boni.model.bean;

/**
 * Created by 0.0 on 2018/1/2.
 */

public class Titlebean {
    public String name;
    public String autor;
    public String path;

    public Titlebean(String name, String autor, String path) {
        this.name = name;
        this.autor = autor;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
