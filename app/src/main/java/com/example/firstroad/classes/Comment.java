package com.example.firstroad.classes;

public class Comment {

    private int imageId;          // 用户头像
    private String authorId;      // 用户名称
    private String commentContent;// 评论内容
    private int praiseNum;        // 点赞数目

    /*
    构造方法
     */
    public Comment(int imageId, String authorId, String commentContent, int praiseNum) {
        this.imageId = imageId;
        this.authorId = authorId;
        this.commentContent = commentContent;
        this.praiseNum = praiseNum;
    }

    /*
    get()  &  set()
     */

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }
}
