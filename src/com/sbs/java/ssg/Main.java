package com.sbs.java.ssg;

public class Main {
	public static void main(String[] args) {
		new App().start();
		
	}
}

//스캐너 입력 - 완료
//게시물 작성 구현 - 완료
//입력된 게시물 저장 및 리스팅화 -완료
//게시물 작성시 날짜 구현 - 완료
//게시물 삭제기능 구현 - 완료
//문제점1) 게시글 5개 중 2번 삭제 후 4번 게시물 삭제할 경우 id 5번 게시물이 삭제되는 것이 확인됨. - 해결
//게시물 수정기능 구현 - 완료
//조회수 기능 추가 - 완료
//게시물 리스팅 시 숫자 데이터 출력자리수 지정 - 완료
//테스트 데이터 생성 메서드 구현 및 적용 - 완료 + 게시물 작성시 번호 밀림 오류 확인 및 수정
//리펙토링 / 클래스를 파일로 분리 / 패키지 분리 - 완료
//특정 게시물을 찾는 메서드를 구현, 중복제거 - 완료 
//게시물 리스트에 검색 기능 추가 - 완료
//회원가입 기능 - 완료
//
//검색기능 오류!!!
//1. 검색결과가 있음에도 검색결과가 존재하지 않습니다가 함께 작동
//2. 검색결과가 존재하지 않을 때에 번호 | 조회 | 제목 이 함께 작동
//
//회원가입 추가!!!
//1. 회원가입시 비밀번호 확인 절차 필요
//2. 아이디 중복검사