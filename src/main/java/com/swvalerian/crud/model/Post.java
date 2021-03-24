package com.swvalerian.crud.model;

public class Post {
    String post;

    @Override
    public String toString() {
        return "Post{" +
                "post='" + post + '\'' +
                '}';
    }

    public Post(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
