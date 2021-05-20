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
  <h3>${board.writer }님의 글 수정</h3>
  <input type="hidden" name="num" id="num" value="${board.num }">
    <div class="form-group">
      <label for="title">제목:</label>
      <input type="text" class="form-control" id="title" name="title" value="${board.title }">
    </div>
    <div class="form-group">
      <label for="writer">글쓴이:</label>
      <input type="text" class="form-control" id="writer" name="writer" value="${board.writer }" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="content">내용:</label>
      <textarea class="form-control" rows="5" id="content" name="content">${board.content }</textarea>
    </div>
    <div class="form-group text-right">
    	<button type="button" class="btn btn-primary btn-sm" id="btnModify">수정하기</button>
    </div>
</div>

</body>

<script>
// 수정하기
$("#btnModify").click(function() {
	var data = { // submit해도 되지만 json 형태로 넘기기
		"num" : $("#num").val(),
		"title" : $("#title").val(),
		"content" : $("#content").val()
	} // 3개의 값을 가지고
	$.ajax({
		type:"put", // HTTP Method -> put : 수정, get : 조회, delete : 삭제, post : 등록(매 호출마다 새로운 데이터가 추가됨, 나머지는 여러번 수행해도 결과가 같다 -> 멱등성)
					// put -> 변경되지 않는 데이터는 모두 default 값으로 처리하므로 변경되지 않는 데이터도 모두 전달해야 함
					// patch -> 변겅하고자 하는 부분만 반영, 나머지는 기존 데이터 유지됨
		url:"/update", // update에 put 방식으로 전달
		contentType:"application/json;charset=utf-8", // 받아오는 방식은 json 형태 -> 아래 data에 대한 유형
		data:JSON.stringify(data), // data 값 넘기기 -> 실제적으로 달고갈 값
		success:function(resp) {
			if(resp=="success") {
				alert("수정완료") // 수정 완료 후
				location.href="/list"; // 전체보기로 이동
			}
		} // success
	}) // ajax
}) // btnModify
</script>

</html>