<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<head>
<title>Examination</title>
<script type="text/javascript">
	function gotoExam(id) {
		window.location.href = "/examQuestion/questionList/"+id;
	} 
	function checkDate(id, examDate, hours, mins, durationTime) {
		var currentDate = new Date();
		currentDate.setHours(0, 0, 0, 0);

		var date = new Date();
		var currentHours = date.getHours();
		var currentMins = date.getMinutes();

		var examDate = new Date(examDate);

		if (currentDate < examDate) {
			document.getElementById(id).value = "Wait";
			document.getElementById(id).disabled = true;
		} else if (currentDate > examDate) {
			document.getElementById(id).value = "Expired";
			document.getElementById(id).disabled = true;
		} else {
				var start = (hours*60)+mins;  //11:50 => 710
				var end =   start + durationTime; //12:10 => 730
				var current = (currentHours*60)+currentMins; // 11:49 => 709
				if(current < start){
					document.getElementById(id).value = "Wait";
					document.getElementById(id).disabled = true;
				}else if(current >= end){
					document.getElementById(id).value = "Expired";
					document.getElementById(id).disabled = true;
				}else{
					document.getElementById(id).value = "Answer Now";
					document.getElementById(id).disabled = false;
				}			
			}
		window.setTimeout("checkDate("+id+",'"+examDate+"',"+hours+","+mins+","+durationTime+");", 1000);
		}	
</script>
</head>
<body>
<div align="center">
		<u><h1>Online Examination System</h1></u>
		<h3><a href="/user/">Home</a> / Exam List</h3>
		<h2>Hello ${loginUser.username }</h2>
		<c:if test="${loginUser.exams.size() == 0 }">
			You have no exam yet !
		</c:if>
		<c:if test="${examList.size() > 0 }">
			You have <b>${examList.size()}</b> exam.<br />
			<br />
			<table border="1">
				<tr>
					<th>ID</th>
					<th style="padding: 5px 10px 5px 10px;">Examination Name</th>
					<th style="padding: 5px 10px 5px 10px;">Examination DateTime</th>
					<th style="padding: 5px 10px 5px 10px;" align="center">Duration
						Time</th>
					<th style="padding: 5px 10px 5px 10px;">Action</th>
				</tr>
				<c:forEach var="exam" items="${examList }" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td>${exam.examName }</td>
						<td>${exam.examDate }&nbsp;&nbsp;${exam.examHour }H: ${exam.examMinute }M</td>
						<td>${exam.durationTime }Mins</td>
						<td><input type="button" value="" id="${exam.id}"
							onclick="gotoExam(${exam.id});" /></td>
						<script>
							var res = checkDate('${exam.id}',
									'${exam.examDate}', ${exam.examHour},
									${exam.examMinute},
									${exam.durationTime});
						</script>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>