<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/main_index.css">
</head>
<body>
	
	<%-- 2가지 include를 사용해서 header.jsp 를 include 하세요 --%>
	<%@ include file="../includes/header.jsp" %>
	
	<main>
		<div id="notice_title">
			<h2>공지사항</h2>
			<a href="#"> [ 더보기 ] </a>
		</div>
		
		<div id="notice_list">공지사항이 없습니다.</div>
		<div id="notice_list">
			<div class="contents">
				<a href="#">
					<span class="title">국무위원은 국정에 관하여 대통령을 보좌하며, 국무회의의 ...</span>
				</a>
			</div>
			<div class="contents">
				<a href="#">
					<span class="title">대통령후보자가 1인일 때에는 그 득표수가 선거권자 총수의 ...</span>
				</a>
			</div>
			<div class="contents">
				<a href="#">
					<span class="title">헌법재판소 재판관은 정당에 가입하거나 정치에 관여할 수 없다.</span>
				</a>
			</div>
		</div>
	</main>
	
	<%@ include file="../includes/footer.jsp" %>
</body>
</html>