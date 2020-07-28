package com.sbs.java.ssg.controller;

import java.util.List;
import java.util.Scanner;

import com.sbs.java.ssg.dto.Member;
import com.sbs.java.ssg.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;
	private Member loginedMember;
	
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
		default: 
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	
	public void doJoin() {
		int id = members.size() + 1;
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
		members.add(member);
		
		loginedMember = member;
		
		System.out.printf("%s님이 가입하였습니다.\n", name);
	}


	private void doLogin() {
		System.out.printf("로그인 아이디: ");
		String loginId = sc.nextLine();
		
		Member member = getMemberByLoginId(loginId);
		
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
		
		System.out.printf("%s님이 로그인하였습니다.\n", member.name);
	}
	
	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		
		if (index == -1) {
			return null;
		}
		return members.get(index);
	}

	private boolean alreadyJoinLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

