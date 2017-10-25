package com.lanou.staff.domain;

/**
 * Created by dllo on 17/10/25.
 */
public class Post {

    private String postId;
    private String postName;
    private Department depId;

    public Post(){

    }

    public Post(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

    public Post(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                ", depId=" + depId +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Department getDepId() {
        return depId;
    }

    public void setDepId(Department depId) {
        this.depId = depId;
    }
}
