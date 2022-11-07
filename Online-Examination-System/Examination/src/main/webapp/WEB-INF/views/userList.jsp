<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>User List</title>
<script>
	function createUser(){
		window.location.href = '/user/create';
	}
	function deleteCheck() {
		var selectedAddUserIds = document.getElementsByName('selectedUserId');
		var checked = false;
		selectedAddUserIds.forEach((user) => {
            if (user.checked) {
            	checked = true;            	
            }
        });
		if(checked){
			document.getElementById('userForm').submit();
		}else{
			alert("Please check to DELETE !");
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

	<h3><a href="/adminHome">Home</a> / User Management</h3>
	<input type="button" value="Add New User" onclick="createUser();"><br/><br/>	
	<form:form  action="/user/search" method="POST" modelAttribute="user">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:input path="username" id="username" placeholder="Search by user name..."/></td>
				<td><form:input path="email" id="email" placeholder="Search by email address..."/></td>
				<td><input type="submit" value="Search"></td>
			</tr>
		</table>
	</form:form><br/>
	
	<c:if test="${empty userList }">
		<span>________________________________________________________</span><br><br>
		<h3>No Records</h3>
	</c:if>
	<c:if test="${not empty userList }">			
	<span>________________________________________________________</span><br><br>
	<h3>User List</h3>
	<form:form action="/user/delete" method="POST" id="userForm">
		<table border="1">
			<tr>		
				<th>No</th>
				<th style="padding: 5px 10px 5px 10px;" align="center">Username</th>
				<th style="padding: 5px 10px 5px 10px;" align="center">Email</th>
				<th style="padding: 5px 10px 5px 10px;" align="center">Phone</th>	
				<th style="padding: 5px 10px 5px 10px;" align="center">About User</th>
				<th style="padding: 5px 10px 5px 10px;"><input type="button" value="Delete" onclick="deleteCheck();"></th>
			</tr>
			<c:forEach var="user" items="${userList }" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${user.username }</td>
				<td>${user.email }</td>
				<td>${user.phone }</td>
				<td><a href="/user/detail/${user.id }">Detail...</a></td>
				<td align="center"><input type="checkbox" name="selectedUserId" value="${user.id }"></td>
			</tr>
			</c:forEach>
		</table>
	</form:form>
	</c:if>
</div>
</body>
</html>