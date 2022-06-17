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

import DAO.NoticeTblDao;
import DTO.NoticeInfo;
import util.DatabaseManager;

@WebServlet("/notice/update")
public class NoticeUpdate extends HttpServlet {
       
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		NoticeInfo noticeInfo = new NoticeInfo(idx, title, contents);
		
		// 공지사항 정보 목록에서 idx에 맞는 공지사항 정보를
		// 클라이언트가 전달한 새로운 공지사항 정보로 바꾼다.
		
		NoticeTblDao dao = new NoticeTblDao();
		dao.updateNoticeInfo(noticeInfo);
		
		response.sendRedirect("/w/notice/detail.jsp?idx="+idx);
	}
	
	

}
