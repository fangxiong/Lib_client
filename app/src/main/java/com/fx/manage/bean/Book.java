package com.fx.manage.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 图书类
 * @author fx
 *
 */
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private int bookid;
	private String bookname;
	private String author;
	private String publishhouse;
	private String publishtime;
	private String description;
	private String loantime;
	private String returntime;
	private double price;
	private int isloan;//1Ϊ�����0Ϊδ���
	private int loancount;//�������
	private byte[] image;
	private int isreserved;
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
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
	public String getPublishhouse() {
		return publishhouse;
	}
	public void setPublishhouse(String publishhouse) {
		this.publishhouse = publishhouse;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLoantime() {
		return loantime;
	}
	public void setLoantime(String loantime) {
		this.loantime = loantime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getIsloan() {
		return isloan;
	}
	public void setIsloan(int isloan) {
		this.isloan = isloan;
	}
	public int getLoancount() {
		return loancount;
	}
	public void setLoancount(int loancount) {
		this.loancount = loancount;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getIsreserved() {
		return isreserved;
	}
	public void setIsreserved(int isreserved) {
		this.isreserved = isreserved;
	}
}
