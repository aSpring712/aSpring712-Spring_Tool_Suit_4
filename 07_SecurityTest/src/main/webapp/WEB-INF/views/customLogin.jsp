<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/myapp07/login"> <!-- security가 알아서 하는 것 -> controller에서 처리할 것 없음 -->
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> <!-- security-context.xml에서 csrf false 시 -->
		ID : <input type="text" name="username"><br>
		PWD : <input type="password" name="password"><br>
		<input type="submit" value="로그인 전송"> <!-- button으로 해도 됨 -->
	</form>
</body>
</html>