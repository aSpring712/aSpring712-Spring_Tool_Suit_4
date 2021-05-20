<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp" %>

<div class="container">
  <h3>${board.writer }님의 글</h3>
    <div class="form-group">
      <label for="number">글번호:</label>
      <input type="text" class="form-control" id="num" name="num" value="${board.num }" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="title">제목:</label>
      <input type="text" class="form-control" id="title" name="title" value="${board.title }" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="writer">글쓴이:</label>
      <input type="text" class="form-control" id="writer" name="writer" value="${board.writer }" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="content">내용:</label>
      <textarea class="form-control" rows="5" id="content" name="content" readonly="readonly">${board.content }</textarea>
    </div>
    
    <%-- <c:if test="${sessionScope.sMember.id == board.writer }"> --%>
	    <div class="form-group text-right">
	    	<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button> 
	    	<button type="button" class="btn btn-warning btn-sm" id="btnDelete">삭제</button>
	    </div>
    <%-- </c:if> --%>
    
    <br/><br/>
    
    <div align="center">
    	<textarea rows="3" cols="50" id="msg"></textarea> 
    	<input type="button" value="댓글쓰기" class="btn btn-secondary btn-sm" id="commentBtn">
    </div>
    <hr/>
    <div id="replyResult"></div>
</div>

<script>
$("#btnUpdate").click(function(){
	location.href="/update/${board.num}";
})
$("#btnDelete").click(function(){
	if(!confirm('정말 삭제할까요?'))
		return false;
	$.ajax({
		type : "delete",
		url : "/delete/${board.num}",
		success : function(resp) {
			if(resp = "success") {
				alert("삭제 성공");
				location.href = "/list";
			}
		}
	})
})
</script>

<%@ include file="includes/footer.jsp" %>  