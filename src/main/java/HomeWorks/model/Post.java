package HomeWorks.model;

import com.google.gson.Gson;

public class Post {
    private long id;
    private String content;

    public Post(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static Post fromJson(String json) {
        return new Gson().fromJson(json, Post.class);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
