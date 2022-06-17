/**
 * 
 */
function getParameter(name) {
	let parameters = location.search.substr(1); // 물음표까지 꺼내지니 물음표를 잘라줌
	parameters = parameters.split("&");

	for(let i = 0; i < parameters.length; i++) {
		let parameter = parameters[i];
		parameter = parameter.split("=");

		let paramName = parameter[0];
		let paramValue = parameter[1];

		if(name == paramName) {
			return paramValue;
		}
	}
	
	return undefined;
}


function setHeader(loginUserInfo) {
	// 1. 아이디 비밀번호 입력한을 지운다.
	$("#id").remove();
	$("#pw").remove();

	// 2. 아이디 비밀번호 입력란이 있던 자리에 로그인 한 사용자의 이름을 출력한다.
	// 아이디가 login_area인 태그 안에 form 태그 안에 자식 태그로 span 태그를 추가하세요.
	$("#login_area form").prepend("<span>" + loginUserInfo.loginUserName + "님 환영합니다!</span>");

	// 3. 로그인 버튼을 지운다.
	// 4. 로그인 버튼이 있던 자리에 로그아웃 버튼을 출력한다.
	$("#login_btn").remove();
	$("#login_area form").append("<button type=\"button\" id=\"logout\">로그아웃</button>");

	$("#logout").on("click", function() {
		location.href = "/w/member/logout";
	});

	// 5. 회원가입 버튼을 지운다.
	$("#join_btn").remove();

	// 6. 관리자로 로그인 했다면
	if (loginUserInfo.loginUserId == "admin") {
		// 회원가입 버튼이 있던 자리에
		$("#join_area").prepend("<button type=\"button\" id=\"notice_write\">공지사항 작성</button>")
		// 공지사항 작성 버튼을 추가한다.	
		$("#join_area").on("click", function() {
			location.href = "/w/notice/form.jsp";
		});
	}
}