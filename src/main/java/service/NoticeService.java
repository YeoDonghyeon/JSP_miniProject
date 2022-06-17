package service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.NoticeTblDao;
import DTO.NoticeInfo;

public class NoticeService {

	public JSONObject selectNoticeByIdx(int idx) {
		NoticeTblDao dao = new NoticeTblDao();
		
		NoticeInfo selectedNoticeInfo = dao.selectNoticeByIdx(idx);
		
		JSONObject json = new JSONObject();
		json.put("title", selectedNoticeInfo.getTitle());
		json.put("contents", selectedNoticeInfo.getContents());
		
		return json;
	}
	
	public JSONArray getNoticeInfoList(int pageNumber) {
		pageNumber = (pageNumber-1) * 5;
		
		NoticeTblDao dao = new NoticeTblDao();
		
		List<NoticeInfo> noticeInfoList = dao.getNoticeInfoList(pageNumber);
		
		JSONArray jsonArray = new JSONArray();
		
		for (NoticeInfo nth : noticeInfoList) {
			// 클라이언트에게 전달할 n번째 공지사항의 정보로 구성하고
			JSONObject noticeInfo = new JSONObject();

			noticeInfo.put("noticeID", nth.getNoticeId());
			noticeInfo.put("title", nth.getTitle());
			noticeInfo.put("contents", nth.getContents());
			// 공지사항 정보 목록에 배열의 형태로 저장한다.
			jsonArray.put(noticeInfo);
		}
		
		return jsonArray;
	}
	
	public int getNoticeAmount() {
		NoticeTblDao dao = new NoticeTblDao();
		
		return dao.getNoticeAmount();
	}
	
}
