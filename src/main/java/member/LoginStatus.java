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

@WebServlet("/login/status")
public class LoginStatus extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 상태 정보를 꺼낸다.
		HttpSession session = request.getSession();
		
		String loginUserid = (String) session.getAttribute("loginUserId");
		String loginUserName = (String) session.getAttribute("loginUserName");
		
		if(loginUserName == null) {
			// 로그인을 하지 않은 상태(이 페이지에 처음 접속을 했거나 로그아웃을 하는 등의 상황) 라면
			// 204 상태 코드만 응답
			response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 콘텐츠가 없는상태
		} else {
			// 로그인을 한 상태라면
			// 꺼낸 상태 정보를 클라이언트에게 전달한다.
			response.setContentType("application/json;charset=utf-8");
			
			PrintWriter output = response.getWriter();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("loginUserName", loginUserName);
			jsonObject.put("loginUserId", loginUserid);
			
			
			output.print(jsonObject);
			
			output.close();
		}
			
	}


}
