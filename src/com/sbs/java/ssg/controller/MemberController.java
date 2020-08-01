package com.sbs.java.ssg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.container.Container;
import com.sbs.java.ssg.dao.MemberDao;
import com.sbs.java.ssg.dto.Article;
import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.service.MemberService;
import com.sbs.java.ssg.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;
		memberService = Container.memberService;
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "관리자"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user1", "user1", "홍길동"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "user2", "user2", "김영희"));
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch (actionMethodName) {
		case "join" :
			doJoin();
			break;
		case "login" :
			doLogin();
			break;
		case "logout" :
			doLogout();
			break;
		default: 
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doLogin() {
		System.out.printf("로그인 아이디: ");
		String loginId = sc.nextLine();
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}
		
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
				
		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}
		
		loginedMember = member;
		System.out.printf("%s님이 로그인하였습니다.\n", member.name);
	}
	
	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void doJoin() {
		int id = Container.memberDao.getNewId();
		String loginId = null;
		String regDate = Util.getNowDateStr();
		
		System.out.println("회원가입을 시작합니다.");
			
		while (true) {
			System.out.printf("로그인 아이디: ");
			loginId = sc.nextLine();
			
			if ( alreadyJoinLoginId(loginId) == false ) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}
		
		String loginPw = null;
		String loginPwCorrect = null;
		
		while (true ) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인: ");
			loginPwCorrect = sc.nextLine();
			
			if (loginPw.equals(loginPwCorrect) == false ) {
				System.out.println("비밀번호를 확인 해주세요.");
				continue;
			}			
			break;
		}
		
		
		
		System.out.printf("이름 : ");
		String name = sc.nextLine();
		
		Member member = new Member(id, regDate, loginId, loginPw, name);
		memberService.join(member);
		
		loginedMember = member;
		
		System.out.printf("%s님이 가입하였습니다.\n", name);
	}

	private boolean alreadyJoinLoginId(String loginId) {
		int index = memberService.getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return true;
		}
		return false;
	}
}

