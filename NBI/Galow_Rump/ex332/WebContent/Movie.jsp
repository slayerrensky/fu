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

<form action="/ex332/Recommender">
  <p>Rating:</p>
  <p>
    <input type="radio" name="rating" value="1">1<br>
    <input type="radio" name="rating" value="2">2<br>
    <input type="radio" name="rating" value="3">3<br>
    <input type="radio" name="rating" value="4">4<br>
    <input type="radio" name="rating" value="5">5<br>
    <input type="hidden" name="movie" value="">
    <input type="submit" value="senden">
  </p>
</form>


</body>
</html>