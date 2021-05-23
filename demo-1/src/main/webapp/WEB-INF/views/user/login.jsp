<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/loginPro" method="post"> <!-- 반드시 포스트 방식, action 이름(기본 : login) 변경 가능 -->
ID : <input type="text" name="username"> <br/> <!-- username, password로 꼭 적어주어야 함 -->
PW : <input type="password" name="password"> <br/>
<input type="submit" value="로그인">
</form>
</body>
</html>