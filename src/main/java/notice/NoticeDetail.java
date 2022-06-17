package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.NoticeService;
import util.DatabaseManager;

// 1. 클라이언트가 전달한 idx 파라미터 값을 꺼낸다
// 2. 공지사항 정보 목록에서 idx 파라미터에 맞는 공지사항 정보를 꺼낸다.
// 3. 공지사항 정보를 클라이언트에게 전달한다.
@WebServlet("/notice/detail")
public class NoticeDetail extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트가 전달한 idx 파라미터 값을 꺼낸다
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. 공지사항 정보 목록에서 idx 파라미터에 맞는 공지사항 정보를 꺼낸다.
		NoticeService service = new NoticeService();
		
		JSONObject noticeInfo = service.selectNoticeByIdx(idx);
		
		// 3. 공지사항 정보를 클라이언트에게 전달한다.
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter output = response.getWriter();
		
		output.print(noticeInfo);
		
		output.close();
		
		
		
		
	
	}


}
