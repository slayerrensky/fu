<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of movies</title>
</head>
<body>

<h1>List of movies they are recommended for User: ${userid}</h1>
<a href="javascript:history.back()">back</a>
<a href="/ex332/Recommender?movies">Movielist</a>
<a href="/ex332/Recommender?switchUser=6">switch to user 6</a>
<table>
${list}
</table>

</body>
</html>