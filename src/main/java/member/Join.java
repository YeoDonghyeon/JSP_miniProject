package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.MemberInfo;
import service.MemberService;
import util.DatabaseManager;

@WebServlet("/member/join")
public class Join extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클라이언트가 보내는 회원 정보(아이디, 비밀번호, 이름)을 꺼낸다.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");

		// 클라이언트가 보낸 데이터들을 하나의 정보로 합친다.
		MemberInfo newMemberInfo = new MemberInfo(id, pw, name);
		
		MemberService service = new MemberService();
		int stuatsCode = service.join(newMemberInfo);
		
		response.setStatus(stuatsCode);


	}

}
