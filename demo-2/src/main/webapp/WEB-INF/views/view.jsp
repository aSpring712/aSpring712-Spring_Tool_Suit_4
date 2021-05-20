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

	ID : ${member.id } <br/>
	이름 : ${member.name } <br/>
	이메일 : ${member.email } <br/>
	주소 : ${member.addr } <br/>
	메모 : ${member.memo } <br/>
	<br/>
	<a href="delete/${member.id }">삭제(GetMapping)</a> <!-- get 방식 / DeleteMapping 쓰려면 ajax으로 돌려야 함 -->
	<button type="button" id="deleteBtn">삭제(DeleteMapping)</button>
	<a href="update/${member.id }">수정하기</a>
	
	<br/>
	<script>
	$("#deleteBtn").click(function(){
		$.ajax({
			type : "delete",
			url : "delete/"+${member.id}
		})
		.done(function(resp){
			if(resp == "success") {
				alert("삭제성공");
				location.href="/list";
			}
		})
		.fail(function(e){
			alert("삭제실패");
		})
	})
	</script>
</body>
</html>