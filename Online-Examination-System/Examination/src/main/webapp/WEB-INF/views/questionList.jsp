<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
<meta charset="ISO-8859-1">
<title>${exam.examName } Exam</title>

<script>
var hour = ${hour};  // set the hours
var min = ${min};   // set the minutes
var sec = ${sec};   // set the seconds

function countDown() {
	if(hour == 0 && min == 0 && sec == 0){
		alert("Time out ! Your answer will be submit automatically .");
		document.getElementById("examForm").submit();
	}else{
		document.getElementById("theTime").innerHTML =  "<b>Time left :</b>   ( "+
															(hour <= 9 ? "0" + hour : hour) + "h : "+ 
															(min <= 9 ? "0" + min : min) + "m : " + 
															(sec <= 9 ? "0" + sec : sec) + "s )";
					
		if (--sec < 0) {
			if(min >= 1){
				sec = 59;
		  	    min = min - 1;
			}	  	    
	    }
	  	if (min == 0) {
	  		if(hour >= 1){
		  	    min = 59;
		  	    hour = hour - 1;
	  		}
	    }
		window.setTimeout("countDown();", 1000);	  	
	}
}
</script>	

</head>
<body>
<div style="padding-left: 20px">
<u><h1>Online Examination System</h1></u>
	<p id="theTime"/>
	<h3>${exam.examName } Exam</h3>
	<form:form action="/examQuestion/submitAnswer" method="POST" id = "examForm">
	<input type="hidden" name="examId" value="${exam.id}">
	<ol>
		<c:forEach var="question" items="${questions}">
			<li>
				<input type="hidden" name="questionId" value="${question.id }">
				${question.question_description }
				<ol>
					<c:forEach var="answer" items="${question.answers }">
						<c:if test="${!empty answer.answer_description}">
						<li>
							<input type="radio" name="answerForQuestionId_${question.id }" value="${answer.answer_description}"> ${answer.answer_description}
						</li>
						</c:if> 
					</c:forEach>
				</ol>
			</li>
		</c:forEach>
	</ol>
	<input type="submit" value="Summit Answer">
	</form:form>
	<script>countDown();</script>
</div>
</body>
</html>