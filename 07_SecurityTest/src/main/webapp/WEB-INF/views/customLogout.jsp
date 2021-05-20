<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/myapp07/customLogout" method="post"> <!-- post 방식에서는 전부 csrf -->
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> <!-- security-context.xml에서 csrf false 시 -->
	<button>로그아웃</button>
</form>
</body>
</html>