<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz</title>
<style>
</style>
</head>
<body>
	<h3>Quiz</h3>
	<form method="get" action="${pageContext.request.contextPath}/quizapp">
		<p>${value+1}.${result.question}</p>
			<ol>
				<li>
				  <input type="radio" name="answerValue" value="${result.correct_answer}">
				  "${result.correct_answer}"
				</li>
				<c:forEach var="answer" items="${result.incorrect_answers}">
					<li>
					  <input type="radio" name="answerValue" value="${answer}">
					  "${answer}"
					</li>
				</c:forEach>
			</ol>
			  <input type = "submit" name = "next" value="Next"/>
			  <input type = "submit" name = "checkanswer" value="Check Answer"/>
	</form>
</body>
</html>