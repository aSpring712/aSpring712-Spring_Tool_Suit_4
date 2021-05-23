<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>HOME</h1>

<sec:authorize access="isAnonymous()">
<p><a href="/login">로그인 페이지로</a></p>
<p><a href="/insert">회원가입</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated">
<p><a href="/logout">로그아웃</a></p>
<p><a href="/user/update">회원변경 폼으로</a></p>
</sec:authorize>

</body>
</html>