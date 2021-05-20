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
<h3>Admin</h3>
<p>principal : <sec:authentication property="principal"/></p> <!-- 인증된 객체를 가리킴 -->
<p>Member : <sec:authentication property="principal.member"/></p>
<p>사용자이름 : <sec:authentication property="principal.member.username"/></p>
<p>사용자ID : <sec:authentication property="principal.member.userid"/></p>
<p>사용자ID : <sec:authentication property="principal.username"/></p> <!-- userid와 동일 -->
<p>권한 : <sec:authentication property="principal.member.authList"/></p>
<a href="/myapp07/board/insert">게시글쓰기</a>

</body>
</html>