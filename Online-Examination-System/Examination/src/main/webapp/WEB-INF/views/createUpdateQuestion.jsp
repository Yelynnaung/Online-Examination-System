<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
	<c:if test="${question.id == 0}">
		<title>Create Question</title>
	</c:if>
	<c:if test="${question.id > 0}">
		<title>Update Question</title>
	</c:if>
	
	<script type="text/javascript">
		function back(){
			window.location.href = "/examQuestion/"+${examId};
			
		}
	</script>

</head>
<body>
<div align="center">	
	<u><h1>Online Examination System</h1></u>
	<c:if test="${question.id == 0}">
		<h3><a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / <a href="/examQuestion/${examId}">Questions</a> / Create New Question</h3>
		<h1>Create New Question</h1>
	</c:if>
	<c:if test="${question.id > 0}">
	<h3><a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / <a href="/examQuestion/${examId}">Questions</a> / Edit Question</h3>
		<h1>Edit Question</h1>
	</c:if>
	<form:form action="/examQuestion/save" method="POST" modelAttribute="question">
		<input type="hidden" name="examId" value="${examId}"/>
		<form:hidden path="id" />
		<table>
			<tr>
				<td>Question</td>
				<td><form:input path="question_description" /></td>
			</tr>
			
			<tr>
				<td>Answer 1</td>
				<td><form:input path="answer1" /></td>
			</tr>
			<tr>
				<td>Answer 2</td>
				<td><form:input path="answer2" /></td>
			</tr>
			<tr>
				<td>Answer 3</td>
				<td><form:input path="answer3" /></td>
			</tr>
			<tr>
				<td>Answer 4</td>
				<td><form:input path="answer4" /></td>
			</tr>
			<tr>
				<td>Correct Answer</td>
				<td><form:input path="correct_answer" /></td>
			</tr>
			<tr>
				<td>Pay Mark</td>
				<td><form:input path="pay_mark" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="Cancel" onclick="back();"/>
				<input type="submit" value="Save"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>