package com.sbs.java.ssg;

import java.util.Scanner;



public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		String command;
		int lastArticleId = 0;
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
				
				System.out.println("게시글을 작성합니다.");
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}
			else if (command.equals("article list")) {
				System.out.println("게시물이 없습니다.");
			}
			else {
				System.out.printf("%s은(는) 존재하지 않는 명령어입니다.\n", command);
			}
		}
		
		sc.close();
		System.out.println("== 프로그램 종료 ==");
		
	}
}
