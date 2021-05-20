<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr>

<sec:authorize access="isAnonymous()"> <!-- session이 없어도 : 누구나 접근 가능 -->
<a href="customLogin">로그인 페이지로 가기</a><br>
<a href="/myapp07/member/insert">회원가입</a><br>
</sec:authorize>

<sec:authorize access="isAuthenticated()"> <!-- 권한 있는 사람만 가능 -->
<a href="/myapp07/board/insert">글쓰기</a><br>
<a href="customLogout">로그아웃</a><br>
</sec:authorize>
</body>
</html>
