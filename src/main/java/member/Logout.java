package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 상태 정보를 삭제한다.
		HttpSession session = request.getSession();
		
//		session.removeAttribute("loginUserName"); // 세세하게 삭제할때
		session.invalidate();// 로그인 상태정보밖에 안들어있을때 
		
		response.sendRedirect("/w/main/");
		
	}

}
