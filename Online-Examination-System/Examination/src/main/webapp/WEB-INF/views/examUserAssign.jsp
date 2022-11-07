<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Manage Examinee</title>
<script type="text/javascript">
	function add() {
		var selectedAddUserIds = document.getElementsByName('selectedAddUserIds');
		var checked = false;
		selectedAddUserIds.forEach((user) => {
            if (user.checked) {
            	checked = true;            	
            }
        });
		if(checked){
			document.getElementById('addForm').submit();
		}else{
			alert("Please check to ADD !");
		}		
	}
	
	function remove() {
		var selectedRemoveUsers = document.getElementsByName('selectedRemoveUserIds');
		var checked = false;
		selectedRemoveUsers.forEach((user) => {
			 if (user.checked) {
	            	checked = true;            	
	     	}
        });
		if(checked){
			document.getElementById('removeForm').submit();
		}else{
			alert("Please check to REMOVE !");
		}
	}
</script>
</head>
<body>
	<div align="center">
	<u><h1>Online Examination System</h1></u>
		<c:if test="${not empty message }">
			<div
				style="background-color: green; color: white; width: 300px; padding: 10px;">${message }</div>
		</c:if>
		<c:remove var="message" scope="session" />

		<div>
			<h3>
				<a href="/adminHome">Home</a> / <a href="/exam/examList">Exam Management</a> / Assign Examinees
			</h3>
		</div>

		<h1>
			Assign Examinees For " ${exam.examName } " Exam
		</h1>
		<input type="hidden" value="${user.id }" id="id">
		<table>
			<tr>
				<th align="left" style="padding: 5px 100px 5px 5px;">Exam Name</th>
				<td><i>${exam.examName }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Exam Date</th>
				<td><i>${exam.examDate }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Total Examinees</th>
				<td><i>${exam.users.size() }</i></td>
			</tr>
		</table>
		<br>

		<u><h3>Already Added</h3></u>
		<c:if test="${empty addedUsers }">
			<i>No Records</i>
		</c:if>
		<c:if test="${not empty addedUsers }">
			<form:form id="removeForm" action="/exam/removeExaminees"
				method="POST">
				<input type="hidden" name="examId" value="${exam.id }" />
				<table border="1">
					<tr>
						<th>No</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Username</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Email</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Phone</th>
						<th style="padding: 5px 10px 5px 10px;"><input type="button" value="Remove" onclick="remove();"></th>
					</tr>
					<c:forEach var="user" items="${addedUsers }" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${user.username }</td>
							<td>${user.email }</td>
							<td>${user.phone }</td>
							<td align="center"><input type="checkbox" name="selectedRemoveUserIds" value="${user.id }"></td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</c:if>

		<br/><span>________________________________________________________</span><br>
		<u><h3>New Examinee</h3></u>
		<c:if test="${empty userList }">
			<i>No Records</i>
		</c:if>
		<c:if test="${not empty userList }">
			<form:form id="addForm" action="/exam/addExaminees" method="POST">
				<input type="hidden" name="examId" value="${exam.id }" />
				<table border="1">
					<tr>
						<th>No</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Username</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Email</th>
						<th style="padding: 5px 10px 5px 10px;" align="center">Phone</th>
						<th style="padding: 5px 10px 5px 10px;"><input type="button" value="Add" onclick="add();"></th>
					</tr>
					<c:forEach var="user" items="${userList }" varStatus="status">
						<tr>
							<td>${status.index+1 }</td>
							<td>${user.username }</td>
							<td>${user.email }</td>
							<td>${user.phone }</td>
							<td align="center"><input type="checkbox" name="selectedAddUserIds" value="${user.id }"></td>
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</c:if>

	</div>
</body>
</html>