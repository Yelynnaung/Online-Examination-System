<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Exam Questions</title>
<script type="text/javascript">
function createQuestion(){
	window.location.href = "/examQuestion/create/"+${exam.id};
}
</script>
</head>
<body>
<div align="center">
<u><h1>Online Examination System</h1></u>
<h3><a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / Questions</h3>
<h1>" ${exam.examName } " Questions</h1>
<input type="button" value="Add New Question" onclick="createQuestion();"/><br/><br/>
<span>________________________________________________________</span><br><br>
<c:if test="${questions.size() == 0 }"> 
	<h3>No Question Yet</h3>
</c:if>
 <c:if test="${questions.size() > 0 }"> 
	 <table border="1">
	 <tr>
	 	<th>ID</th>
	 	<th>Questions</th>
	 	<th>Answers</th>
	 	<th>Correct Answer</th>
	 	<th>Pay Mark</th>
	 	<th>Action</th>
	 </tr>
	 <c:forEach var="question" items="${questions}"  varStatus="status">
	 <tr>
	 	<td>${status.index+1 }</td>
	 	<td>${question.question_description }</td>
	 	<td >
	 		<table>
	 			<c:forEach var="answer" items="${question.answers }">
	 				<tr>
	 					<td>${answer.answer_description }</td>
	 				</tr>
	 			</c:forEach>
	 		</table>
	 	</td>
	 	<td>${question.correct_answer }</td>
	 	<td>${question.pay_mark }</td>
	 	<td><a href="/examQuestion/edit/${exam.id}/${question.id }">Edit</a>
	 	     <a href="/examQuestion/delete/${exam.id}/${question.id }" onclick="return confirm('Are you sure?');">Delete</a></td>
	 </tr>
	 </c:forEach>
	 </table>
</c:if>	
</div>
</body>
</html>