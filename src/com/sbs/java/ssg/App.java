package com.sbs.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	public void start() {
		System.out.println("== 프로그램 시작 ==");
		String command;
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.printf("명령어를 입력해주세요:");
			command = sc.nextLine();
			
			command = command.trim(); 
			
			if (command.length() == 0) {
				continue;
			}
			
			if (command.equals("system out")) {
				break;
			}
			
			if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				
				String regData = Util.getNowDateStr();
				
				System.out.println("게시글을 작성합니다.");
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, regData, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}
			else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				System.out.printf("    번호   |  조회   | 제목\n");
				for (int i = articles.size() - 1 ; i >= 0 ; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%4d  |%4d  | %s\n" , article.id, article.hit, article.title);
				}
			}
			else if (command.startsWith("article detail")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = null; //찾은 게시글의 정보 저장을 위한 변수
				
				for (int i = 0 ; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if ( article.id == id ) {
						foundArticle = article; //게시글을 찾았을 때 사용하기 위한 재설정
						break; 
					}
				}
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.increaseHit();
				
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regData);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);
			}
			
			else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = null;
				
				for (int i = 0 ; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if ( article.id == id ) {
						foundArticle = article;
						break; 
					}
					
				}
				
				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.printf("%d번 게시글을 수정합니다.\n", id);
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
				continue;
					
			}
			
			else if (command.startsWith("article delete")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				int foundIndex = -1; // 게시물 삭제 시 index를 찾기 위해 변수 생성
				
				for (int i = 0 ; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if ( article.id == id ) {
						
						foundIndex = i;
						break; 
					}
				}
				
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id+1);
					continue;
				}
				
					articles.remove(foundIndex);
					System.out.printf("- %d번 게시물 삭제 -\n", foundIndex+1);
			}
			
			else {
				System.out.printf("%s은(는) 존재하지 않는 명령어입니다.\n", command);
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 ==");
		
	}
}

class Article {
	int id;
	String title;
	String body;
	String regData;
	int hit;
	
	public Article(int id, String regData, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regData = regData;
		this.hit = 0 ;
	}
	
	public void increaseHit() {
	hit++;
	}
}

