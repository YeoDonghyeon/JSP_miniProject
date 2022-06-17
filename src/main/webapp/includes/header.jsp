<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/functions.js"></script>

<header>
	<div id="login_area">
		<form action="#" method="POST">
			<input type="text" name="id" placeholder="아이디" id="id">
			<input type="password" name="pw" placeholder="비밀번호" id="pw">
			<input type="submit" value="로그인" id="login_btn">
		</form>
	</div>
	<div id="join_area">
		<button type="button" id="join_btn">회원가입</button>
	</div>
</header>

<script>
	// 페이지가 다 로드된다음 스크립트를 동작하게 해야함
	// header.jsp 안에 있는 스크립트 코드는 Jquery가 추가되기 전에 동작하는데
	// 페이지 로드가 완료된 후에 동작하도록 해야함
	// 순수 JavaScript만 사용해서 구현하면 Jquery를 추가하는 코드를 닫는 body 바로 위에 넣을 수 있음
	// 순수 JavaScript만 사용하면 구혀ㅕㄴ하기가 어려우므로
	// Jquery가 제공하는 페이지 로드가 완료된 후에 동작하도록 하는 코드를 사용
	// $(function() {페이지 로드가 완료된 후 동작할 코드});
	
	console.log(1);
	
	$(function() {
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


		// 회원 가입 버튼을 클릭했을 때
		$("#join_btn").on("click", function() {
			// location.href 를 사용해서
			location.href = "/w/member/join.jsp";
			// 회원 가입 페이지로 이동하도록 구현하세요
			
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
	});

	
</script>