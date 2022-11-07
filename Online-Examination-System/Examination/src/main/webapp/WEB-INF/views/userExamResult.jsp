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
		<h3><a href="/user/">Home</a> / Exam Result</h3>
		<h2>Hello ${loginUser.username }</h2>
		<c:if test="${examResult.size() == 0 }">
			You have no exam result yet !
		</c:if>
		
		<c:if test="${examResult.size() > 0 }">
			<h3>You have finished <b>${examResult.size()}</b> exam.</h3>
			<u>Result Table</u><br/>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Exam Name</th>
					<th>Exam DateTime</th>
					<th>Duration Time</th>
					<th>Pass Mark</th>
					<th>Your Mark</th>
					<th>Status</th>
				</tr>
				<c:forEach var="examResult" items="${examResult }" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${examResult.examName }</td>
						<td align="center">${examResult.examDate }&nbsp;&nbsp; ${examResult.examHour }H : ${examResult.examMin }M </td>
						<td align="center">${examResult.durationTime } M</td>
						<td align="center">${examResult.passMark }</td>
						<td align="center">${examResult.marks }</td>
						<td align="center">${examResult.passStatus }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

	</div>
</body>
</html>