package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import DTO.MemberInfo;
import service.MemberService;

@WebServlet("/member/login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 보내는 아이디, 비밀번호를 꺼내서
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberInfo loginMemberInfo = new MemberInfo(id, pw);
		
		MemberService service = new MemberService();
		
		MemberInfo selectedMemberInfo = service.selectMemberInfoByIdAndPw(loginMemberInfo);
		if(selectedMemberInfo != null) {
			// 아이디와 비밀번호를 정확히 입력해서 로그인에 성공했다면
			HttpSession session = request.getSession();
			session.setAttribute("loginUserId", id);
			session.setAttribute("loginUserName", selectedMemberInfo.getName());
			session.setMaxInactiveInterval(3600);
			
			// 로그인 성공일 때는 200 상태 코드를 설정
//			response.setStatus(HttpServletResponse.SC_OK);
			
			response.setContentType("application/json;charset=utf-8");
			
			PrintWriter output = response.getWriter();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("loginUserName", selectedMemberInfo.getName());
			jsonObject.put("loginUserId", id);
			
			output.print(jsonObject);
			
			output.close();
		} else {
			// 아이디와 비밀번호를 잘못 입력해서 로그인에 실패했다면
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
