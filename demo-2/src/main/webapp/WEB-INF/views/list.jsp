<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${lists }" var="member">
	ID : ${member.id } <br/>
	이름 : ${member.name } <br/>
	주소 : ${member.addr } <br/>
	<a href="${member.id }">상세보기</a> <br/> <br/>
</c:forEach>

</body>
</html>