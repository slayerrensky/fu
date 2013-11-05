<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User selected</title>
</head>
<body>
	<h1>UserID: ${userid}</h1>
	<h3>age: ${age}; sex: ${sex}; zipcode: ${zipcode}; job: ${occupations}</h3>
	<h3>My rated movies:</h3>
	 <a href="javascript:history.back()">back</a>
	 <a href="/ex332/Recommender?movies">Movielist</a>
	 <a href="/ex332/Recommender?predict">Movies i could also like</a>
	<table>
		${list}
	</table>
</body>
</html>