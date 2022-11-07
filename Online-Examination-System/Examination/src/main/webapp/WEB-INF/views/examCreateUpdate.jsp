<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
	  $("#datepicker").datepicker();
	});
	
	function goExamList(){
		window.location.href = '/exam/examList';
	}
</script>
<c:if test="${exam.id == 0 }">
	<title>Create New Examination</title>
</c:if>
<c:if test="${exam.id > 0 }">
	<title>Edit Examination</title>
</c:if>

</head>
<body>
	<div align="center">
	<u><h1>Online Examination System</h1></u>
		<c:if test="${not empty message }">
			<div
				style="background-color: green; color: white; width: 300px; padding: 10px;">${message }</div>
		</c:if>
		<c:remove var="message" scope="session" />

		<c:if test="${exam.id == 0 }">
			<div>
				<h3><a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / Create Exam <br></h3>
			</div>
			<h1>Create New Examination</h1>
		</c:if>
		<c:if test="${exam.id > 0 }">
			<div>
				<h3><a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / Edit Exam <br></h3>
			</div>
			<h1>Edit Examination</h1>
		</c:if>
		
		<form:form action="/exam/save" method="POST" modelAttribute="exam">			
			<form:hidden path="id" />
			<table>
				<tr>
					<td align="left" style="padding: 5px 70px 5px 5px;">Examination Name</td>
					<td><form:input path="examName" /></td>
				</tr>
				<tr>
					<td style="padding: 5px 70px 5px 5px;">Examination Date</td>
					<td><form:input path="examDate" id="datepicker"/></td>
				</tr>
				<tr>
					<td style="padding: 5px 70px 5px 5px;">Start Hour</td>
					<td><form:input path="examHour" />*(24 Hours)</td>
				</tr>
				<tr>
					<td style="padding: 5px 70px 5px 5px;">Start Minute</td>
					<td><form:input path="examMinute" />*(60 Mins)</td>
				</tr>
				<tr>
					<td style="padding: 5px 70px 5px 5px;">Duration Time</td>
					<td><form:input path="durationTime" />*(Total Mins)</td>
				</tr>
				<tr>
					<td style="padding: 5px 70px 5px 5px;">Pass Mark</td>
					<td><form:input path="passMark" /></td>
				</tr>
				<tr>
					<td align="right"  style="padding-top: 10px;"> 
						<input type="button" value="Cancel" onclick="goExamList();"/>
					</td>
					<td style="padding-top: 10px;"><input type="submit" value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>