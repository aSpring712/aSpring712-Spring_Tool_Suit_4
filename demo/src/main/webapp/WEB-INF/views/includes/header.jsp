<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>게시판 만들기</title>
</head>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>MyBoard</h1>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-3">
		<ul class="navbar-nav">

			<li class="nav-item active">
				<a class="nav-link" href="/">HOME</a>
			</li>
			<li class="nav-item active">
				<a class="nav-link" href="/list">게시판</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/login">로그인</a>
			</li>	
			<li class="nav-item">
				<a class="nav-link" href="/member/join">회원가입</a>
			</li>	
			<li class="nav-item">
				<a class="nav-link" href="logout">로그아웃</a>
			</li>

		</ul>
	</nav>