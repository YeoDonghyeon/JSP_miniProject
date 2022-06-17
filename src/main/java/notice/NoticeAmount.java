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

import DAO.NoticeTblDao;
import service.NoticeService;
import util.DatabaseManager;

@WebServlet("/notice/amount")
public class NoticeAmount extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoticeTblDao dao = new NoticeTblDao();
		
		int noticeAmount = dao.getNoticeAmount();
		
		PrintWriter output = response.getWriter();
		
		output.print(noticeAmount);
		
		output.close();
	}


}
