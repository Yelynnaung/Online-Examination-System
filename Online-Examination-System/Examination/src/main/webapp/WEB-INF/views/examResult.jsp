<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Exam Result</title>
</head>
<body>
<div align="center">
	<u><h1>Online Examination System</h1></u>
	<h3><a href="/adminHome">Home</a> / <a href="/exam/examList/">Exam Management</a> / Exam Result</h3>	
	
	<c:if test="${empty examResultList }">
		<span>________________________________________________________</span><br><br>
		<h3>No Records</h3>
	</c:if>
	
	<c:if test="${not empty examResultList }">
			<b>${examResultList[0].examName } exam for </b> (${examResultList[0].examDate } : ${examResultList[0].examHour }H : ${examResultList[0].examMin }M)<br/><br/>
		<form:form>
			<table border="1">
				<tr>
					<th>Id</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Student Name</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Pass Mark</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Result Mark</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Pass Status</th>
				</tr>
				<c:forEach var="examResult" items="${examResultList }" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td align="center">${examResult.userName }</td>
						<td align="center">${examResult.passMark } </td>
						<td align="center">${examResult.marks } </td>
						<td align="center">${examResult.passStatus }</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</c:if>
</div>

</body>
</html>