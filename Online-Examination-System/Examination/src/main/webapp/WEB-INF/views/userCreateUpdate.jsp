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
	
	function goUserList(){
		window.location.href = '/user/userList';
	}
	function goDetail(){
		var id = document.getElementById("id").value;
		window.location.href = '/user/detail/'+id;
	}
</script>
<c:if test="${user.id > 0 }">
		<title>Edit User</title>
	</c:if>
	<c:if test="${user.id == 0 }">			
		<title>Create User</title>
	</c:if>
</head>
<body>
<div align="center">
<u><h1>Online Examination System</h1></u>
	<c:if test="${not empty message }">
		<div
			style="background-color: green; color: white; padding: 10px; width: 400px;">
			<h3>${message}</h3>
		</div>
	</c:if>
	<c:remove var="message" scope="session" />

	
	<c:if test="${user.id > 0 }">
		<div>
			<h3><a href="/adminHome">Home</a> / <a href="/user/userList">User Management</a> / <a href="/user/detail/${user.id}">Detail</a> / Edit User <br></h3>
		</div>	
		<h1>Edit User Information</h1>
	</c:if>
	<c:if test="${user.id == 0 }">	
		<div>
			<h3><a href="/home">Home</a> / <a href="/user/userList">User Management</a> / Create User <br></h3>
		</div>		
		<h1>Create New User</h1>
	</c:if>
	
	<form:form action="/user/save" method="POST" modelAttribute="user" enctype="multipart/form-data">
		<form:hidden path="id" id="id"/>
		<table>
			<tr>
				<td align="left" style="padding: 5px 70px 5px 5px;">User Name</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Email</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Phone</td>
				<td><form:input path="phone" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Password</td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Confirm Password</td>
				<td><form:password path="confirmPassword" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Age</td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Gender</td>
				<td>
					<form:select path="gender">
						<form:option value="Male" label="Male"></form:option>
						<form:option value="Female" label="Female"></form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">DOB</td>
				<td><form:input id="datepicker" path="dob" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Address</td>
				<td><form:textarea path="address" /></td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Role</td>
				<td>
					<form:select path="role" class="form-control">
					   <form:options items="${roles}" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td style="padding: 5px 70px 5px 5px;">Photo</td>
				<td><input type="file" name="image" accept="image/png, image/jpeg" /> </td>
			</tr>
			<tr>
			<c:if test="${user.id > 0 }">
				<td align="right"  style="padding-top: 10px;"> 
					<input type="button" value="Cancel" onclick="goDetail();"/>
				</td>
			</c:if>
			<c:if test="${user.id == 0 }">
				<td align="right"  style="padding-top: 10px;"> 
					<input type="button" value="Cancel" onclick="goUserList();"/>
				</td>
			</c:if>
				<td  style="padding-top: 10px;"><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>