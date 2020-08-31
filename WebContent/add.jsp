<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date Time Calculator</title>
</head>
<body>

	<form action="add">
		<h1>Add to date</h1>
		<input name="date" type="text" placeholder="date (DD-MM-YYYY)"> 
		<input name="duration" type="text" placeholder="duration">
		<br> 
		<input type="radio" name="operation" value="day"> Day 
		<input type="radio" name="operation" value="week"> Week 
		<input type="radio" name="operation" value="month"> Month 
		<input type="radio" name="operation" value="year"> Year 
			<br>
		<button type="submit">Add</button>
	</form>
	
	<%  if(request.getAttribute("result") != null) { %>  
		<h2> Result:  <%= request.getAttribute("result") %> </h2>
	 <% } %>
	
	

</body>
</html>