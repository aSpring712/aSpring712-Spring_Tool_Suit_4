<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container">
  <h3>목록(${count })</h3>
  
  <div class="form-group text-right">
		<button type="button" class="btn btn-secondary btn-sm" id="writeBtn">글쓰기</button>
  </div>
  
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      
      <c:forEach items="${lists.content }" var="board"> <!-- paging 시 lists에는 페이징 관련된 정보까지 가져옴 -> .content가 게시글을 담고있음  -->
      	<tr>
      		<td>${board.num }/${lists.number }</td> <!-- lists.number : 현재 페이지 번호. 0부터 시작 -->
      		<td><a href="view/${board.num }">${board.title }(${board.replyCnt })</a></td>
      		<td>${board.writer }</td>
      		<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd hh:mm"/></td>
      		<td>${board.hitcount }</td>
      	</tr>
      </c:forEach>
      
    </tbody>
  </table>
  <ul class="pagination pagination-sm">
  	<c:choose>
  		<c:when test="${lists.first }"></c:when>
  		<c:otherwise> <!-- 첫 번째 페이지가 아닐 때 "이전" 표시 -->
  			<li class="page-item"><a class="page-link" href="?page=${lists.number-1 }">이전</a></li>
		</c:otherwise>
  	</c:choose>
  	<c:choose>
  		<c:when test="${lists.last }"></c:when>
  		<c:otherwise>
			<li class="page-item"><a class="page-link" href="?page=${lists.number+1 }">다음</a></li>
		</c:otherwise>
  	</c:choose>
  </ul>

	<div class='row' align='right'>
		<div class="col-lg-12">
			<form id='searchForm' action="/list" method='get'>
				<select name='field'>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select> 
				
				<input type='text' name='word'>
				<button class='btn btn-info'>Search</button> <!-- button type="button"이 아니면 submit 기능이 있음! action으로 넘어감 -->
			</form>
		</div>
	</div>

</div>

<script>
$("#writeBtn").click(function(){
	location.href="/insert"
})
</script>

</body>
</html>