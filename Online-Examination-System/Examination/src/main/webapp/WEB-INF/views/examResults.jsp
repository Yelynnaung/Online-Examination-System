<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Exam Result</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
	  $("#examDate").datepicker();
	});
</script>
</head>
<body>
<div align="center">
	<u><h1>Online Examination System</h1></u>
	<h3><a href="/adminHome">Home</a> / <a href="/exam/examList/">Exam Management</a> / Exam Result</h3>	
	<form:form  action="/examResult/search" method="POST" modelAttribute="exam">
			<form:hidden path="id" />
			<table>
				<tr>
					<td><form:input path="examName" id="examName" placeholder="Search by exam name..." /></td>
					<td><form:input path="examDate" id="examDate" placeholder="Search by exam date..."/></td>
					<td><input type="submit" value="Search"></td>
				</tr>
			</table>
		</form:form>
	<c:if test="${empty examResultList }">
		<span>________________________________________________________</span><br><br>
		<h3>No Records</h3>
	</c:if>
	
	<c:if test="${not empty examResultList }">
		
	<span>________________________________________________________</span><br>
		<form:form>
			<table border="1">
				<tr>
					<th>Id</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Examination Name</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Examination DateTime</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Pass Mark</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Result Mark</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Student Name</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Pass Status</th>
				</tr>
				<c:forEach var="examResult" items="${examResultList }" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td align="center">${examResult.examName }</td>
						<td align="center">${examResult.examDate } &nbsp;&nbsp;   ${examResult.examHour }H : ${examResult.examMin }M </td>
						<td align="center">${examResult.passMark } </td>
						<td align="center">${examResult.marks } </td>
						<td align="center">${examResult.userName }</td>
						<td align="center">${examResult.passStatus }</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</c:if>
</div>

</body>
</html>