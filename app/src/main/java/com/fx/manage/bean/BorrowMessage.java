package com.fx.manage.bean;

import java.io.Serializable;

public class BorrowMessage implements Serializable{

private static final long serialVersionUID = 1L;
private int borrowid;
private String loantime;
private String returntime;
private String expiretime;
private int isoverdue;//1Ϊ���ڣ�0Ϊδ����
private double fine;
private String bookname;
private byte[] image;
public int getBorrowid() {
	return borrowid;
}
public void setBorrowid(int borrowid) {
	this.borrowid = borrowid;
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
public String getExpiretime() {
	return expiretime;
}
public void setExpiretime(String expiretime) {
	this.expiretime = expiretime;
}
public int getIsoverdue() {
	return isoverdue;
}
public void setIsoverdue(int isoverdue) {
	this.isoverdue = isoverdue;
}
public double getFine() {
	return fine;
}
public void setFine(double fine) {
	this.fine = fine;
}
public String getBookname() {
	return bookname;
}
public void setBookname(String bookname) {
	this.bookname = bookname;
}
public byte[] getImage() {
	return image;
}
public void setImage(byte[] image) {
	this.image = image;
}

}