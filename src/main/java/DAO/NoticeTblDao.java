package DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.NoticeInfo;
import util.DatabaseManager;

public class NoticeTblDao {

	public int getNoticeAmount() {
		// 등록된 공지사항의 개수를 클라이언트에게 전달(NoticeInfoList의 길이가 공지사항의 개수)
		int noticeAmount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "SELECT COUNT(*) AS amount FROM noticetbl";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			
			rs = pstmt.executeQuery();
			rs.next();
				
			noticeAmount = rs.getInt("amount");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return noticeAmount;
	}
	
	public boolean deleteByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "DELETE FROM noticetbl WHERE noticeID = ?";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setInt(1, idx);
			
			
			pstmt.executeUpdate();
			
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return result;
	}
	
	public NoticeInfo selectNoticeByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		NoticeInfo noticeInfo = null;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "SELECT * FROM noticetbl WHERE noticeID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			
			noticeInfo = new NoticeInfo(title, contents);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return noticeInfo;
	}
	
	public boolean writeNoticeInfo(NoticeInfo noticeinfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "INSERT INTO noticetbl(title, contents) VALUES(?, ?)";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, noticeinfo.getTitle());
			pstmt.setString(2, noticeinfo.getContents());
			
			pstmt.executeUpdate();
			
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return result;
	}
	
	public List<NoticeInfo> getNoticeInfoList(int start) {
		List<NoticeInfo> noticeInfoList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 등록된 공지사항의 개수를 공지사항 정보 테이블에서 가져와 amount변수에 저장
		try {
			conn = DatabaseManager.getConnection();

			String sql = "SELECT * FROM noticetbl ORDER BY noticeID DESC LIMIT ?, 5";

			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setInt(1, (start - 1) * 5);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int noticeId = rs.getInt("noticeId");
				String title = rs.getString("title");
				String contents = rs.getString("title");

				NoticeInfo nth = new NoticeInfo(noticeId, title, contents);

				noticeInfoList.add(nth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		return noticeInfoList;
	}
	
	public boolean updateNoticeInfo(NoticeInfo noticeInfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		boolean result = false;
		
		try {
			conn = DatabaseManager.getConnection();
			
			String sql = "UPDATE noticetbl SET title = ?, contents = ? WHERE noticeID = ?";
			
			pstmt = DatabaseManager.getPstmt(conn, sql);
			pstmt.setString(1, noticeInfo.getTitle());
			pstmt.setString(2, noticeInfo.getContents());
			pstmt.setInt(3, noticeInfo.getNoticeId());
			
			pstmt.executeUpdate();
			
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseManager.closePstmt(pstmt);
			DatabaseManager.closeConn(conn);
		}
		
		return result;
	}
	
	
}
