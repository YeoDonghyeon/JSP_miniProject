package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import service.NoticeService;

@WebServlet("/main/controller")
public class MainController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메인 페이지에 필요한 데이터를 불러온다.
		// 1. 최근에 등록된 공지사항 5개
		
		// 2. 
		NoticeService service = new NoticeService();
		
		JSONArray noticeInfoList = service.getNoticeInfoList(1);
		
		
		// 불러온 데이터를 요청 정보 영역에 저장한다.
		// 불러온 데이터를 요청 정보 영역에 저장할 때 "noticeInfoList" 이름에 담아서 저장하기
		request.setAttribute("noticeInfoList", noticeInfoList);
		
		// 메인 페이지로 포워딩한다.
		RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
	    rd.forward(request, response);
		
	}

}
