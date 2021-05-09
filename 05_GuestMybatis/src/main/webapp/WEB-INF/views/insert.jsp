<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(function(){ // document ready
	loadData("", ""); // 1
	$("#submit").click(sendIt)// 2  -> 2개 처리 -> 클릭 시 ajax 부분은 sendIt 함수로 만들어서 호출
	
	$("#btnSearch").click(function(){ // 버튼 클릭 시 -> field, word값 전달해서 검색
		loadData($("#field").val(), $("#word").val());
	}) // btnSearch click
}) // document

function sendIt() {
	var postString = { // JSON 형태로 넘기기
			"name" : $("#name").val(),
			"content" : $("#content").val(),
			"grade" : $("input:radio[name=grade]:checked").val()
	}
	$.ajax({
		type : "post",
		url : "gInsert",
		contentType : "application/json;charset=utf-8", // json 형태로 보냄 (기본 : application/x-www-form-urlencoded 형태로 전달)
		data : JSON.stringify(postString), // JSON.stringify() -> json 형태의 문자로 바꿔줌
		success : function(resp) {
			alert(resp)
			loadData("","");
		},
		beforeSend : showRequest, // 가기 전에 처리해야할 것
		error : function(e) {
			alert("실패 : " + e)
		}
	})//ajax
}

/* function sendIt() {
	var name = $("#name").val();
	var content = $("#content").val();
	var grade = $("input:radio[name=grade]:checked").val() //[]:속성을 의미
	var postString = "name=" + name + "&content=" + content + "&grade=" + grade;
	$.ajax({
	   type : "post",
	   url : "gInsert",
	   data : postString,
	   success : function(resp){
	      alert(resp)
	      loadData("","");
	   },
	   beforeSend : showRequest, //gInsert가기 전에 유효성 검사
	   error : function(e){
	      alert("실패 :" + e)
	   }
	})//ajax
}*/

function loadData(field, word) {
	$.getJSON("gList",
			{"field" : field, "word" : word}, // 값 2가지를 가지고
			function(resp){ // gList를 불러서 그 결과값을 처리
				var str="";
				$("#cntDiv").html("개수 : " + resp.count);
				$.each(resp.list, function(key, val) { // 결과값이 두개(coutn, list)
					str += "<a href='javascript:fview(" + val.num + ")'>" + val.name + "</a> " /* a 태그 : 페이지 이동 -> 함수를 부를 때 javascript:를 써주어야 됨 */
					str += val.content + " "
					str += val.grade + " "
					str += "<a href='javascript:fdelete(" + val.num + ")'>삭제 <br>"
				})
				$("#result").html(str);
			})
}
// 삭제
function fdelete(num) {
/* 	$.getJSON("gDelete?num="+num, function(resp) { // queryString 방식 */
	/* $.getJSON("gDelete/"+num, function(resp) { // REST 방식? 
		
	}) */
	$.ajax({
		type : "DELETE", // DeleteMapping 해주고
		url : "gDelete/"+num, // REST 방식 사용
		success : function(resp) {
			if(resp=="success") {
				alert("삭제성공");
				loadData(); // 결과 화면 다시 뿌리기
			}
		},
		error:function(e) {
			alert("삭제 실패 : " + e);
		}
	})
} // fdelete

// 상세보기:
function fview(num) {
	/* alert(num); */
	$.getJSON("gView", {"num":num}, function(resp){
		var str = "";
		str += "이름 : " + resp.name + "<br>"
		str += "내용 : " + resp.content + "<br>"
		str += "평가 : " + resp.grade + "<br>"
		str += "작성일 : " + resp.created + "<br>"
		$("#views").html(str);
	})
}

//유효성 검사
function showRequest() {
   //이름
   if(!$("#name").val()){
      alert("글쓴이를 입력하세요");
      $("#name").focus();
      return false;
   }
   //내용
   if(!$("#content").val()){
      alert("내용을 입력하세요");
      $("#content").focus();
      return false;
   }
   //평가
   if($("input:radio[name=grade]:checked").length==0){
      alert("평가하세요");
      return false;
   }
   return true; //true가 있어야 리턴 가능
}
</script>
</head>
<body>

	<form method ="post" action="gInsert"> <!-- Controller로 감 -->
		<table align ="center">
			<tr>
				<td align="center">글쓴이</td> 
				<td>
				<input type="text" id="name" name="name">
				</td>
			</tr>
			<tr>
				<td align="center">내  용</td>
				<td> 
				<input type="text" size="80" id="content" name="content">
				</td>
			</tr>
			<tr>
				<td align="center">평  가</td>
				<td>
				<input type="radio" name="grade" value="excellent" checked> 아주잘함(excellent) 
				<input type="radio" name="grade" value="good"> 잘함(good) 
				<input type="radio" name="grade" value="normal"> 보통(normal)
				<input type="radio" name="grade" value="fail"> 노력(fail)
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<!-- type="button" id 속성 추가할 것 -->
				<input type="button" value="입력" id="submit"> <!-- jquery 비동기 쓸거기 때문에 submit이 아닌 button으로 -->
				</td>
			</tr>
		</table>
	</form>
<br/>

<div align="right">
	<select name="field" id="field">
		<option value="name">이름</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="찾기" id="btnSearch">
</div>

<hr>

<div id="cntDiv"></div> <!-- 개수 -->
<div id="result"></div> <!-- 목록 -->
<hr>
<div id="views"></div> <!-- 상세보기 -->
</body>
</html>