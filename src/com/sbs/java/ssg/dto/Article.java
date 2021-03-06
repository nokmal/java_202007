package com.sbs.java.ssg.dto;

public class Article extends Dto {
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int hit;
	public int memberId;
	public String memberName;
	
	public Article(int id, String regDate, int memberId, String title, String body, String memberName) {
		this(id, regDate, memberId, title, body, 0, memberName);
	}
	
	public Article(int id, String regDate, int memberId, String title, String body, int hit, String memberName) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit ;
		this.memberName = memberName;
	}
	
	public void increaseHit() {
	hit++;
	}
}