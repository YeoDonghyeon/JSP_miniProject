package service;

import DAO.MemberTblDao;
import DTO.MemberInfo;

public class MemberService {
	
	public int join(MemberInfo newMemberInfo) {
		
		MemberTblDao dao = new MemberTblDao();
		
		boolean success = dao.join(newMemberInfo);
		if(success) {
			// 회원가입 성공사인 (이번에는 상태코드로 받을것이다)
			return 200;
		} else {
			// 회원가입 실패사인
			return 409;
		}
		
	}
	
	public MemberInfo selectMemberInfoByIdAndPw(MemberInfo loginMemberInfo) {
		MemberTblDao dao = new MemberTblDao();
		
		MemberInfo memberInfo = dao.selectMemberInfoByIdAndPw(loginMemberInfo);
		
		if(memberInfo != null) {
			// 아이디와 비밀번호를 정확히 입력해서 로그인에 성공했다면
			return memberInfo;
		} else {
			// 아이디와 비밀번호를 잘못 입력해서 로그인에 실패했다면
			return null;
		}
	}
}
