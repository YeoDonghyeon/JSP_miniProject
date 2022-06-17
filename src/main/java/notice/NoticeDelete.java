package notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 클라이언트가 전달한 삭제할 공지사항 idx를 꺼낸다.
// 공지사항을 삭제한다.
// 공지사항 목록 페이지로 이동을 지시한다.

import DAO.NoticeTblDao;
import util.DatabaseManager;

@WebServlet("/notice/delete")
public class NoticeDelete extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeTblDao dao = new NoticeTblDao();
		dao.deleteByIdx(idx);
		
		response.sendRedirect("/w/notice/list.jsp");
	}


}
