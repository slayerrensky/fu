<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of movies</title>
</head>
<body>

${MovieInformations}
<a href="javascript:history.back()">back</a>
<form action="/ex332/Recommender">
  <p>Rating:</p>
  <p>
    ${radio}
  </p>
</form>


</body>
</html>