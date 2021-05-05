<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<form action="personUpdate.go" method="post">
<h2>개인 정보 수정</h2>
이름 : <input type="text" name="name" value="${person.name }"><br/>
아이디 : <input type="text" name="id" value="${person.id }" readonly="readonly"><br/>
패스워드 : <input type="password" name="password"><br/>
성별 : 
<label for="man">남</label>
	<input type="radio" name="gender" id="man" value="남"> 
<label for="woman">여</label>
	<input type="radio" name="gender" id="woman" value="여"> <br/>
직업 : 
<select name="job" id="job">
	<option value="회사원">회사원</option>
	<option value="학생">학생</option>
	<option value="기타">기타</option>
</select>

<script>
/* jquery 사용 */
// 성별
if("${person.gender }" == "남") {
	$("input:radio[value='남']").prop("checked", true); // name이 gender인것을 찾아서 value가 남 인것을 찾아도 됨
} else {
	$("input:radio[value='여']").prop("checked", true);
}
// 직업 -> job의 option 길이만큼 반복문을 돌려야 함
$("#job option").each(function() { // job에 포함되어있는(하위의) option
	if($(this).val() == "${person.job }") { // 하나 하나를 가리키는 것 -> this, value는 "회사원", "학생", "기타" .. 이런 값들
		$(this).prop("selected", true);
	}
}) 
</script>

<br/><br/>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
</body>
</html>