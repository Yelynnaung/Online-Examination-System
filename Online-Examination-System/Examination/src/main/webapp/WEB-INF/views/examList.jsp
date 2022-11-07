<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Examination List</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
	  $("#examDate").datepicker();
	});
	function viewResult(examId,examDate,hour,min){
		var d = new Date(examDate);
		window.location.href = "/examResult/viewResult/"+examId+"/"+d+"/"+hour+"/"+min;
	}
	function createExam(){
		window.location.href = "/exam/create";
	}
	function checkDate(id,examDate,hours,mins,durationTime){

		var currentDate = new Date(); 
		currentDate.setHours(0,0,0,0);
		
		var date = new Date();
		var currentHours = date.getHours();
		var currentMins = date.getMinutes();
		
		var examDate=new Date(examDate);
		
		if(currentDate < examDate){
			document.getElementById(id).innerHTML = "Active";
			document.getElementById("result_"+id).style.visibility = 'hidden';
		}else if(currentDate > examDate){
			document.getElementById(id).innerHTML = "Finished";
			document.getElementById("result_"+id).style.visibility = 'visible';
		}else{
			var start = (hours*60)+mins;  //11:50 => 710
			var end =   start + durationTime; //12:10 => 730
			var current = (currentHours*60)+currentMins; // 11:49 => 709
			if(current < end){
				document.getElementById(id).innerHTML = "Active";	
				document.getElementById("result_"+id).style.visibility = 'hidden';
			}else{
				document.getElementById(id).innerHTML = "Finished";
				document.getElementById("result_"+id).style.visibility = 'visible';
			}			
		}
		window.setTimeout("checkDate("+id+",'"+examDate+"',"+hours+","+mins+","+durationTime+");", 1000);
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

	<h3><a href="/adminHome">Home</a> / Exam Management</h3>
	<h3>Exam List | <a href="/examResult/">Exam Result</a><br/></h3>
	<input type="button" value="Add New Exam" onclick="createExam();"><br/><br/>	
	<form:form  action="/exam/search" method="POST" modelAttribute="exam">
			<form:hidden path="id" />
			<table>
				<tr>
					<td><form:input path="examName" id="examName" placeholder="Search by exam name..." /></td>
					<td><form:input path="examDate" id="examDate" placeholder="Search by exam date..."/></td>
					<td><input type="submit" value="Search"></td>
				</tr>
			</table>
		</form:form><br/>
	
	<c:if test="${empty examList }">
		<span>________________________________________________________</span><br><br>
		<h3>No Records</h3>
	</c:if>
	<c:if test="${not empty examList }">		
		<form:form>
			<table border="1">
				<tr>
					<th>Id</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Examination Name</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Examination DateTime</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Duration Time</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Pass Mark</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Total Examination</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Manage Examinee</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Total Question</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Manage Questions</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Status</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Result</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Action</th>
				</tr>
				<c:forEach var="exam" items="${examList }" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${exam.examName }</td>
						<td>${exam.examDate } &nbsp;&nbsp;   ${exam.examHour }H : ${exam.examMinute }M </td>
						<td>${exam.durationTime } Mins</td>
						<td>${exam.passMark } Marks</td>
						<td align="center">${exam.users.size() }</td>
						<td align="center"><a href="/exam/addUsersForExam/${exam.id }">Add or Remove</a></td>
						<td align="center">${exam.questions.size() }</td>						
						<td align="center"><a href="/examQuestion/${exam.id }">Add or Remove</a></td>						
			            <td><span id="${exam.id }"></span></td>
			            <td><input type="button" id="result_${exam.id }" value="View Result" onclick="viewResult(${exam.id},'${exam.examDate}',${exam.examHour },${exam.examMinute })"/></td>
			            <td><a href="/exam/edit/${exam.id }">Edit</a> 
						| <a
							href="/exam/delete/${exam.id }"
							onclick="return confirm('Are you sure to delete?');">Delete</a> 
						</td>						
						<script>
						var res = checkDate('${exam.id}','${exam.examDate}',${exam.examHour},${exam.examMinute},${exam.durationTime});
						</script>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</c:if>
</div>
</body>
</html>