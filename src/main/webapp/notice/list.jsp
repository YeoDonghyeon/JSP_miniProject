<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/footer.css">
<link rel="stylesheet" href="../css/notice_list.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	
	<div id="wrapper">
        <h2>공지사항</h2>

        <div id="notice_wrapper">
            <div id="title_info_wrapper">
                <span class="order">번호</span>
                <span class="title">제목</span>
            </div>
            <div id="list">
                
            </div>
            <div class="pagination">
            </div>
        </div>
	</div>
	
	
	<%@ include file="../includes/footer.jsp" %>
	
	<script>
		$.ajax({
			url: "/w/notice/amount",
			type: "GET",
			success: function(noticeAmount) {
				// 한 페이지에 5개씩 공지사항을 보여줄 예정
				let lastPageNumber = Math.ceil(noticeAmount / 5);
				
				for(let pageNumber=1; pageNumber<=lastPageNumber; pageNumber++) {
					$(".pagination").append("<span>" + pageNumber + "</span>");	
				}
				
				// pagination 안에 페이지 번호를 클릭했을 때
				$(".pagination span").on("click", function() {
					// 현재 클릭한 페이지 번호를 가져옴
					let $span = $(this);
					
					let clickedPageNumber = $span.text();
					
					location.href = "/w/notice/list.html?pageNumber="+clickedPageNumber;
					
				});
				
				
			},
			error: function() {
				console.log("오류발생");
			}
		});
	
		// GET 파라미터를 꺼낸다.
		let pageNumber = 1;
		
		if(location.search.length > 0) {
			let parameter = location.search.substr(1);
			parameter = parameter.split("=");
			
			pageNumber = parameter[1];
		}
		
		$.ajax({
			url: "/w/notice/list",
			type: "GET",
			data: "pageNumber="+pageNumber,
			success: function(noticeInfo) {
				console.log(noticeInfo)
				
				if(noticeInfo == undefined) {
					
					let tag = "<div class=\"contents\"><span class=\"order\"></span><a href=\"#\"><span class=\"title\">작성된 공지사항이 없습니다.</span></a></div>";
					
					$("#list").append(tag);
				} else {
					let noticeInfoList = noticeInfo.list;
					
					// let startNumber = (pageNumber - 1) * 5; // 현재페이지의 번호를 개선해야함
					let startNumber = noticeInfo.amount - ((pageNumber - 1) * 5);
					
					// JSON에 담겨있는 공지사항 정보들을 샘플에 맞게 출력
					for(let i=0; i<noticeInfoList.length; i++) {
						let nthNoticeInfo = noticeInfoList[i];
						let order = startNumber -i;
						let idx = nthNoticeInfo.noticeID;
						let title = nthNoticeInfo.title;
						
						let tag = "<div class=\"contents\"><span class=\"order\">" + (startNumber - i) + "</span><a href=\"/w/notice/detail.jsp?idx=" + idx + "\"><span class=\"title\">" + title + "</span></a></div>";
						
						$("#list").append(tag);
					}
					
				}
			},
			error: function() {
				console.log("오류가 발생함");
			}
		})
		
	</script>
	
</body>
</html>