<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h2>회원 정보 수정</h2>
<form> <!-- 수정 : form에서는 put mapping 불가능 -->
	<!-- form action : readyonly는 값을 가지고 감, disabled는 값을 가지고가지 않아서 hidden으로 값 넘겨줘야 함 -->
	아이디 : <input type="text" name="id" id="id" value="${member.id }" disabled="disabled"><br/>
	Name : <input type="text" name="name" id="name" value="${member.name }"><br/>
	Email : <input type="text" name="email" id="email" value="${member.email }"><br/>
	Addr : <input type="text" name="addr" id="addr" value="${member.addr }"><br/>
	<button type="button" id="btnUpdate">글수정</button>
</form>

</body>

<script>
$("#btnUpdate").click(function(){
	var dataParam = {
			"id" : $("#id").val(),
			"name" : $("#name").val(),
			"email" : $("#email").val(),
			"addr" : $("#addr").val()
	}
	$.ajax({
		type : "put",
		url : "/update",
		data : JSON.stringify(dataParam), // json 형태의 문자열 형태로 가져감
		contentType : "application/json;charset=utf-8", // 유형을 정확하게 알려주어야 함
	}) // ajax
	.done(function(resp) {
		alert(resp);
		location.href="/${member.id}";
	})
	.fail(function(e){
		alert("수정실패" + e);
	})
}) // btnUpdate
</script>

</html>

