package DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import DTO.MemberInfo;
import util.DatabaseManager;

public class MemberTblDao {

	public boolean join(MemberInfo memberInfo) {
		// 해체하는 순서의 기준으로 사용되는 규칙
		Connection conn = null;
		PreparedStatement selectPstmt = null;
		PreparedStatement insertPstmt = null;
		ResultSet rs = null;

		// 회원 정보를 저장한다. ( 회원 정보 테이블에 회원 정보를 INSERT 한다. )
		try {
			conn = DatabaseManager.getConnection();

			// 가입하려는 아이디가 이미 사용중인지 아닌지 판별하는 부분
			// 가입 하려는 아이디로 회원 정보를 조회
			String sql = "SELECT * FROM membertbl WHERE memberID = ?";

			selectPstmt = DatabaseManager.getPstmt(conn, sql);
			selectPstmt.setString(1, memberInfo.getId());

			rs = selectPstmt.executeQuery();

			// 조회 결과가 있다면 이미 사용중인 아이디이므로 409 상태 코드를 설정하고 회원가입이 이뤄지면 안됨 ( 흐름이 여기서 끝나야함 )
			if (rs.next()) {
				// 이미 사용 중인 아이디로 가입을 시도했다면
				return false;
			}
			// 조회 결과가 없다면 사용중인 아이디가 아니므로 회원 가입이 진행되도록 해야함

			// 가입하려는 아이디가 이미 사용중인지 아닌지 판별하는 부분

			// 회원가입을 하는 부분
			// 실행할 쿼리 준비
			// 회원 정보 테이블에 회원 정보를 INSERT 한다.
			sql = "INSERT INTO membertbl values(?, ?, ?)"; // ?, ?, ?는 인덱스 파라미터

			// 쿼리를 실행하고 결과를 가져올 객체(PreparedStatement) 생성
			insertPstmt = DatabaseManager.getPstmt(conn, sql);
			insertPstmt.setString(1, memberInfo.getId());
			insertPstmt.setString(2, memberInfo.getPw());
			insertPstmt.setString(3, memberInfo.getName());

			// 쿼리를 실행하고 결과를 가져옴
			int count = insertPstmt.executeUpdate();
			// 회원 가입을 하는 부분

			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(selectPstmt);
			DatabaseManager.closePstmt(insertPstmt);
			DatabaseManager.closeConn(conn);
		}
	}
	
	// 조회한 회원정보를 리턴할 수 있도록
	public MemberInfo selectMemberInfoByIdAndPw(MemberInfo memberInfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberInfo selectedMemberInfo = null;
		
		try {
			// JDBC 드라이버 로딩
			conn = DatabaseManager.getConnection();
			
			// 실행할 쿼리를 작성
			String sql = "SELECT * FROM membertbl WHERE memberID = ? AND memberPW = ?";
			
			// 쿼리를 실행하고 결과를 가져올 PreparedStatement객체 생성
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, memberInfo.getId());
			pstmt.setString(2, memberInfo.getPw());
			
			// 쿼리를 실행하고 결과를 가져옴
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberID = rs.getString("memberID");
				String memberPW = rs.getString("memberPW");
				String memberName = rs.getString("memberName");
				
				selectedMemberInfo = new MemberInfo(memberID, memberPW, memberName);
			}
			
			// 포인터가 이동할 수 있는지 아닌지를 알 수 있게 next는 boolean타입임
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// DB와 관련된 자원 해제
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return selectedMemberInfo;
	}
	
}
