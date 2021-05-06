<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>List(${count })</h3>

<c:forEach items="${guestlist }" var="guest"> <!-- Comtroller에서 받아온 이름 사용 -->
번호 : ${guest.num } <br/>   
이름 : ${guest.name } <br/>
내용 : ${guest.content } <br/>
평가 : ${guest.grade } <br/>
날짜 : ${guest.created } <br/> 
ip주소 : ${guest.ipaddr } <br/>
<br/>
</c:forEach>

</body>
</html>