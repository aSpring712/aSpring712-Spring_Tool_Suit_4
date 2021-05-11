<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/header.jsp" %>

<div class="container">
  <h3>게시판(${count })</h3>
  
  <div class="form-group text-right">
		<a href="insert"><button type="button" class="btn btn-secondary btn-sm">글쓰기</button></a>
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
      
      <c:forEach items="${lists}" var="board">
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
  
  <ul class="pagination">
       <!-- 이전 -->
       <c:if test="${p.startPage > p.blockPage}">
        <li class="page-item"><a class="page-link" href="list?pageNum=${p.startPage-p.blockPage}&field=${param.field}&word=${param.word}">Previous</a></li>
      </c:if>  
       <!-- 페이지 번호 -->
      <c:forEach begin="${p.startPage}"  end="${p.endPage}" var="i">
       <!--  현재 페이지가 이니면 -->
      <c:if test="${p.currentPage!=i }">
         <li class="page-item"><a class="page-link" href="list?pageNum=${i}&field=${param.field}&word=${param.word}">${i}</a></li>
      </c:if>
       <!--  현재 페이지 -->
      <c:if test="${p.currentPage == i }">
         <li class="page-item active"><a class="page-link" href="">${i}</a></li>
      </c:if>
        
      </c:forEach>
        <!-- 다음 -->
      <c:if test="${p.endPage < p.totPage}">
        <li class="page-item"><a class="page-link" href="list?pageNum=${p.endPage+1}&field=${param.field}&word=${param.word}">Next</a></li>
      </c:if>
    </ul>

	<div class='row' align='right'>
		<div class="col-lg-12">
			<form id='searchForm' action="/myapp06/list" method='get'>
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

<%@ include file="includes/footer.jsp" %>