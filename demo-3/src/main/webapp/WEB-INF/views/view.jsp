<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>


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
    
    <c:if test="${sessionScope.sMember.id == board.writer }">
	    <div class="form-group text-right">
	    	<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button> 
	    	<button type="button" class="btn btn-warning btn-sm" id="btnDelete">삭제</button>
	    </div>
    </c:if>
    
    <br/><br/>
    
    <div align="center">
    	<textarea rows="3" cols="50" id="msg"></textarea> 
    	<input type="button" value="댓글쓰기" class="btn btn-secondary btn-sm" id="commentBtn">
    </div>
    <hr/>
    <div id="replyResult"></div>
</div>

<script>
	var init = function() {
		$.ajax({
			type : "get",
			url : "/reply/commentList",
			data : {"bnum" : $("#num").val()}// 숫자라서 stringify없이
		})
		.done(function(resp) {
			/* alert(resp.length); */
			var str = "";
			$.each(resp, function(key, val){
				str += val.userid + " "
				str += val.content + " "
				str += val.regdate + " "
				if("${sessionScope.sMember.id}" == val.userid) {
					str += "<a href='javascript:fdel(" + val.cnum + ")'>삭제</a><br>"					
				} else {
					str += "<br/>"
				}
				
			})
			$("#replyResult").html(str); // 값 뿌리기
		})
		.fail(function(e) {
			alert("실패");
		})
	}

	// 댓글쓰기
	$("#commentBtn").click(function() {
		if($("#msg").val() == "") {
			alert("댓글을 적으세요");
			return;
		}
		data = { // json 형태로 전달
			"content" : $("#msg").val(),
			"bnum" : $("#num").val()
		}
		$.ajax({
			type : "post",
			url : "/reply/commentInsert", // 댓글 관련은 전부 reply를 타고 넘어가도록
			contentType : "application/json;charset=utf-8", // 형태로 넘김
			data : JSON.stringify(data)
		}).done(function() {
			alert("댓글 추가 성공");
			$("#msg").val("");
			init();
		}).fail(function() {
			alert("댓글 추가 실패")
		}) // ajax
	}) // commentBtn 

	// 수정 폼으로 이동
	$("#btnUpdate").click(function() {
		if (!confirm('정말 수정할까요?')) // '취소' 클릭 시
			return false; // 넘기지 않음
		location.href = "/update/${board.num}"; // callback 함수 아님
	})

	// 삭제
	$("#btnDelete").click(function() {
		if (!confirm('정말 삭제할까요?')) // '취소' 클릭 시
			return false; // 넘기지 않음
		$.ajax({ // '확인' 클릭 시 비동기로 처리 후
			type : "delete", // delete Mapping 
			url : "/delete/${board.num}", // delete/num으로 이동 -> 현재 /로 시작하는 절대경로 사용함(프로젝트를 타고 감)
			success : function(resp) {
				/* alert(resp) */
				if (resp = "success") { // 삭제 성공 시 
					alert("삭제성공"); // 성공 메세지 띄워주고
					location.href = "/list"; // list로 보냄 -> 그냥 list라고 적으면 view/list로 가짐! 상위(myapp06)로 이동하고 list로 가야하는데 절대경로로 써주면 편함 
				}
			} // success
		}) // ajax
	}) // btnDelete
	
	// 댓글 삭제
	function fdel(cnum) {
		$.ajax({
			type : "delete",
			url : "/reply/del/" + cnum
		})
		.done(function(resp){
			alert(resp + "번 글 삭제 완료");
			init();
		})
		.fail(function(e){
			alert("댓글 삭제 실패")
		})
	}
	
	init()
</script>

</body>
</html>