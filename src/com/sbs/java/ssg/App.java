package com.sbs.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.controller.ArticleController;
import com.sbs.java.ssg.controller.Controller;
import com.sbs.java.ssg.controller.MemberController;
import com.sbs.java.ssg.dto.Article;
import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class App {
	public void start() {
		System.out.println("== 프로그램 시작 ==");
		String command;
				
		Scanner sc = new Scanner(System.in);
				
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
			
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
			
			String[] commandBits = command.split((" "));
			
			if ( commandBits.length == 1 ) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String controllerName = commandBits[0];
			String actionMethodName = commandBits[1];
			
			Controller controller = null;
			
			if ( controllerName.equals("article")) {
				controller = articleController;
			}
			else if ( controllerName.equals("member")) {
				controller = memberController;
			}
			
			controller.doAction(command, actionMethodName);
			
		}

		sc.close();
		System.out.println("== 프로그램 종료 ==");
}

}

