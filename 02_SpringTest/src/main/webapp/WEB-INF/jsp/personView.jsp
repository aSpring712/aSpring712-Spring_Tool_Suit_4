<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>상세보기</h3>
이름 : ${person.name }<br/>
아이디 : ${person.id }<br/>
성별 : ${person.gender }<br/>
직업 : ${person.job }<br/>
<input type="button" value="수정폼" 
onclick="location.href='personUpdate.go?id=${person.id}'"> <!-- 수정 form으로 넘기기 -->
<input type="button" value="삭제"
onclick="location.href='personDelete.go?id=${person.id}'">
</body>
</html>