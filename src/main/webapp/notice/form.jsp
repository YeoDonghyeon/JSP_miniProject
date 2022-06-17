<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/notice_form.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	
	<div id="wrapper">
        <h2>공지사항</h2>

		<form action="/w/notice/write" method="POST">
			<div id="title_wrapper">
				<label>제목&nbsp;&nbsp;&nbsp;&nbsp;: <input type="text" name="title" id="title"></label>
			</div>
			
			<div id="contents_wrapper">
				<label>내용<br><textarea name="contents" cols="100" rows="10" id="contents"></textarea> </label>
			</div>
			
			<div>
				<label>첨부파일 : <input type="file" name="file"></label>
			</div>

	        <div id="btn_wrapper">
	            <button type="submit">공지사항 작성</button>
	        </div>
        </form>
	</div>
	
	
	<script>
		//
		if(location.search.length > 0) {
			let idx = getParameter("idx");
			
			// 공지사항 수정 페이지가 되도록 할 것
			$("#btn_wrapper button").text("공지사항 수정");
			$("form").append("<input type=\"hidden\" name=\"idx\" value=\"" + idx + "\">");
			$("form").attr("action", "/w/notice/update");
			
			// idx 파라미터를 사용해서 수정할 공지사항의 정보를 불러온다.
			$.ajax({
				url: "/w/notice/detail",
				type: "GET",
				data: "idx="+idx,
				success: function(noticeInfo) {
					$("#title").val(noticeInfo.title);
					$("#contents").val(noticeInfo.contents);
				},
				error: function() {
					
				}
			});
		}
	
	
		$.ajax({
			url: "/w/login/status",
			type: "GET",
			success: function(loginUserInfo) {
				if(loginUserInfo != undefined) {
					// 헤더가 로그인한 상태로 보여지게 처리
					
					setHeader(loginUserInfo);
					
				}
			},
			error: function() {
				
			}
		});
		
		$("#login_btn").on("click", function(event) {
			event.preventDefault();
			
			let id = $("#id").val();
			let pw = $("#pw").val();
			
			$.ajax({
				url: "/w/member/login",
				type: "post",
				data: "id="+id+"&pw="+pw,
				success: function(loginUserInfo) {
					
					setHeader(loginUserInfo);
					
				},
				error: function() {
					alert("아이디 또는 비밀번호를 확인하세요.");
				}
			});
		});
	</script>
	
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>