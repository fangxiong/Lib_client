package com.fx.manage.bean;

/**
 * 收藏
 * Created by 15934 on 2017/12/21.
 */
public class Collection {
    private String bookname;
    private String author;
    private byte[] image;
    private String description;

    public Collection(String bookname,String author,byte[] image,String description){
        this.bookname = bookname;
        this.author = author;
        this.image = image;
        this.description = description;
    }
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
