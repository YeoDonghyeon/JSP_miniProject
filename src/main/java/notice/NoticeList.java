package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import DTO.NoticeInfo;
import service.NoticeService;
import util.DatabaseManager;

@WebServlet("/notice/list")
public class NoticeList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// DB에서 불러올 공지사항 페이지 번호
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		NoticeService service = new NoticeService();
		
		// 등록된 공지사항 개수
		int amount = service.getNoticeAmount();
		
		
		// DB에 불러온 공지사항 정보 목록
		

		

		// ﻿공지사항 목록 서비스는 공지사항 목록에 저장된 공지사항이 없을 경우 상태 코드를 204로 설정
		if (amount == 0) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			// ﻿공지사항 목록이 있을 경우 적절할 json을 구성해 목록을 전달
//				페이지 번호에 맞는 공지사항을 조회해서 JSON데이터로 구성해 전달하도록 향상된 for문을 수정하세요

			JSONArray noticeInfoList = service.getNoticeInfoList(pageNumber);
			
			response.setContentType("application/json;charset=utf-8");

			PrintWriter output = response.getWriter();

//				하나의 공지사항 정보 -> {"title": "공지사항제목", "contents": "공지사항 내용"}
//				공지사항 정보 목록 -> [공지사항정보1, 공지사항정보2]
			

			JSONObject json = new JSONObject();
			json.put("amount", amount);
			json.put("list", noticeInfoList);

			output.print(json);

			output.close();
		}

	}

}
