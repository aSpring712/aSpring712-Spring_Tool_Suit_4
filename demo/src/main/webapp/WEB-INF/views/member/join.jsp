<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<div class="container">

  <h2>회원가입 하기</h2>
  <form action="/myapp06/member/join" method="post">
    <div class="form-group">
      <label for="id">아이디:</label>
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" id="id" placeholder="Enter userid" name="id">
				</div>
				<div class="col">
					<button type="button" class="btn btn-primary form-inline" id="idCheckBtn">중복확인</button>
					<span id="idcheck"></span>
				</div>
			</div>
		</div>
    <div class="form-group">
      <label for="pass">비밀번호:</label>
      <input type="password" class="form-control" id="pass" name="pass" placeholder="Enter Password">
    </div>
    <div class="form-group">
      <label for="pass_check">비밀번호 확인:</label>
      <input type="password" class="form-control" id="pass_check" name="pass_check" placeholder="Enter Password for check">
    </div>
    <div class="form-group">
      <label for="name">이름:</label>
      <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name">
    </div>
    <div class="form-group">
      <label for="addr">주소:</label>
      <input type="text" class="form-control" id="addr" name="addr" placeholder="Enter Address">
    </div>
    <button type="button" class="btn btn-secondary" id="btnJoin">회원가입</button>
  </form>
</div>

<script>
// 유효성 검사
$("#btnJoin").click(function(){
	if($("#id").val() == "") {
		alert("id를 입력하세요.")
		$("#id").focus();
		return false;
	}
	if($("#pass").val() == "") {
		alert("비밀번호를 입력하세요.")
		$("#pass").focus();
		return false;
	}
	if($("#pass_check").val() != $("#pass").val()) {
		alert("비밀번호가 일치하지 않습니다.")
		$("#pass_check").val('');
		$("#pass_check").focus();
		return false;
	}
	if($("#name").val() == "") {
		alert("이름을 입력해주세요.");
		$("#name").focus();
		return false;
	}
	if($("#addr").val() == "") {
		alert("주소를 입력하세요.")
		$("#addr").focus();
		return false;
	} // 다 맞다면
	var data = { // data 전달
			"id" : $("#id").val(),
			"name" : $("#name").val(),
			"pass" : $("#pass").val(),
			"addr" : $("#addr").val()
	}
	$.ajax({
		type : "post",
		url : "/myapp06/member/join",
		contentType : "application/json;charset=utf-8",
		data : JSON.stringify(data)
	})
	.done(function(resp) {
		if(resp=="success") {
			alert("회원가입을 축하합니다.");
			location.href="login";
		} else if(resp=="fail") {
			alert("아이디 중복확인하세요.");
			$("#id").val("");
		}
	})
	.fail(function(e) {
		alert("회원가입 실패");		
	})
})

// id 중복 검사
$("#idCheckBtn").click(function(){
	if($("#id").val() == "") { // id를 입력하지 않은 경우
		alert("id를 입력하세요.");
		$("#id").focus();
		return false;
	} // if
	$.ajax({ // id 전달해서 중복 검사하기
		type : "get",
		url : "/myapp06/member/idCheck",
		data : {"id" : $("#id").val()}
	})
	.done(function(resp){
		if(resp == 0) {
			$("#idcheck").html("<b style='color:blue'>사용가능한 아이디입니다.</b>");
		} else {
			$("#idcheck").html("<b style='color:red'>사용 불가능한 아이디입니다.</b>");
			$("#id").val("");
			$("#id").focus();
		}
	})
})
</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>