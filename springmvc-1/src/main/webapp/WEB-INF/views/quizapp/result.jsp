<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Result Page</h1>
	<p>Correct Answer Count : ${correctAnswerCount}/10</p>
	<form method="get" action="${pageContext.request.contextPath}/quizapp">
		<input type="submit" name="nextquiz" value="Start Next Quiz" />
	</form>
</body>
</html>