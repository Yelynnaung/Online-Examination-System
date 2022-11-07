<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>User Detail</title>
<script>
	function goEdit(){
		var id= document.getElementById("id").value;
		window.location.href = "/user/edit/"+id;
	}
	function goDelete(){
		var confirmValue = confirm("Are you sure to delete?");
		if(confirmValue){
			var id= document.getElementById("id").value;
			window.location.href = "/user/delete/"+id;
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
		<h3><a href="/adminHome">Home</a> / <a href="/user/userList">User Management</a> / Detail</h3>
	</div>
	<h1>User Detail Information</h1>	
	
	    <img src="/src/images/${user.photo }" width="200" height="200"/>
		<input type="hidden" value="${user.id }" id="id">
 		<table>
			<tr>
				<th align="left" style="padding: 5px 100px 5px 5px;">Username</th>
				<td><i>${user.username }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Email</th>
				<td><i>${user.email }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Phone</th>
				<td><i>${user.phone }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Age</th>
				<td><i>${user.age }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Gender</th>
				<td><i>${user.gender }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">DOB</th>
				<td><i>${user.dob }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Role</th>
				<td><i>${user.role }</i></td>
			</tr>
			<tr>
				<th align="left" style="padding: 5px;">Address</th>
				<td><i>${user.address }</i></td>
			</tr>
			<tr>
				<td style="padding-top: 10px;" colspan="2"><input type="button" value="Edit User" onclick="goEdit();">
				<input type="button" value="Delete User" onclick="return goDelete();"></td>
			</tr>
		</table>
</div>
</body>
</html>