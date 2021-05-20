<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/resources/includes/header.jsp" %>

<div class="container">
  <h3>게시글 목록(${count })</h3>
  
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
      
      <c:forEach items="${boards}" var="board" varStatus="st">
      	<tr>
      		<td>${board.num }</td>
      		<td><a href="view/${board.num }">${board.title }(${board.replyCnt })</a></td> <!-- QueryStream 방식(?)이 아니라 REST 방식으로! -->
      		<td>${board.writer }</td>
      		<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd hh:mm"/></td>
      		<td>${board.hitcount }</td>
      	</tr>
      </c:forEach>
      
    </tbody>
  </table>
  

	<!-- <div class='row' align='right'>
		<div class="col-lg-12">
			<form id='searchForm' action="/myapp07/list" method='get'>
				<select name='field'>
					<option value="writer">작성자</option>
					<option value="content">내용</option>
				</select> 
				
				<input type='text' name='word'>
				<button class='btn btn-info'>Search</button> button type="button"이 아니면 submit 기능이 있음! action으로 넘어감
			</form>
		</div>
	</div> -->

</div>

<%@ include file="/resources/includes/footer.jsp" %>