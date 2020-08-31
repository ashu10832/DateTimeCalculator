<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Date Time Calculator</title>
</head>
<body>

<h1> Enter the phrase below</h1>

<form action="translate">
<input type="text" name="phrase" placeholder="phrase">
<button type="submit">Convert</button>
</form>

	<%  if(request.getAttribute("result") != null) { %>  
		<h2> Result:  <%= request.getAttribute("result") %> </h2>
	 <% } %>
	 
</body>
</html>