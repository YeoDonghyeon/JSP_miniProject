<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/notice_detail.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	
    <div id="wrapper">
        <h2>공지사항</h2>

		<div id="title_wrapper">
			<span>제목</span>
		</div>
		
		<div id="contents_wrapper">
			<p>내용</p>
		</div>
		
		<div id="file_wrapper">
			<img src="../images/img.png" alt=""><a href="#">첨부파일</a>
		</div>

        <div id="btn_wrapper">
            <button type="button" id="goToList">목록으로</button>
        </div>
	</div>
	
	<%@ include file="../includes/footer.jsp" %>
	
	
	
	<script>
		// 목록으로 버튼을 클릭했을 때 목록으로 이동하는 버튼을 구현해라
		$("#btn_wrapper #goToList").on("click", function() {
			location.href = "/w/notice/list.html"
		})
		
		// idx 파라미터의 값을 꺼낸다.
		let parameter = location.search.substr(1);
		parameter = parameter.split("=");
		
		let idx = parameter[1];
		
		$.ajax({
			url: "/w/login/status",
			type: "GET",
			success: function(loginUserInfo) {
				if(loginUserInfo != undefined && loginUserInfo.loginUserId == "admin") {
					$("#btn_wrapper").append("<button type=\"button\" id=\"goToDelete\">공지사항 삭제</button>");
					$("#btn_wrapper").append("<button type=\"button\" id=\"goToUpdate\">공지사항 수정</button>");
					
					// ﻿2. 공지사항 상세 페이지에서 공지사항 수정 버튼을 클릭했을 때 공지사항 작성 페이지로 이동하는데 idx 파라미터를 가지고 이동하도록 처리
					$("#goToUpdate").on("click", function() {
						location.href = "/w/notice/form.jsp?idx="+idx; 
					});
					
					$("#goToDelete").on("click", function() {
						 location.href = "/w/notice/delete?idx="+idx;
					});
					
				}
			},
			error: function() {
				
			}
		});
	
	
		
		// idx 파라미터의 값을 사용해서
		
		// 공지사항 상세 정보를 요청한다.
		$.ajax({
			url: "/w/notice/detail",
			type: "GET",
			data: "idx="+idx,
			success: function(noticeInfo) {
				$("#title_wrapper span").text(noticeInfo.title);
				$("#contents_wrapper p").text(noticeInfo.contents);
			},
			error: function() {
				console.log("에러 발생");
			}	
		});
		
		// 공지사항 상세 정보를 전달 받았을 때
		// 상세 정보를 화면에 출력한다.
	</script>
</body>
</html>