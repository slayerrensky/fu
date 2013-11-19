<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crawler Info</title>
</head>
<body>

<ul>
	<li><a href="http://localhost:8080/fu.netzsys.crawler_lucene/TheCrawler?toCrawl=http://www.udacity.com/cs101x/index.html">Crawl Page: http://www.udacity.com/cs101x/index.html</a> </li>
	<li><a href="http://localhost:8080/fu.netzsys.crawler_lucene/TheCrawler?query=page">Search for "page"</a></li>
	<li><a href="http://localhost:8080/fu.netzsys.crawler_lucene/TheCrawler?query=crawl">Search for "crawl"</a></li>
</ul>

<h1>Crawler Status</h1>
<p>Parameter: ${desti}</p>
<form action="TheCrawler">
  <table border="0" cellpadding="0" cellspacing="4">
    <tr>
      <td align="right">Suche:</td>
      <td><input name="query" type="text" size="120" maxlength="10000"></td>
    </tr>
    <input type="radio" name="searchtype" value="html" checked="checked"> HTML<br>
    <input type="radio" name="searchtype" value="img"> Img<br>
  	<input type="submit" name="search" value="search">
  	<input type="submit" name="search" value="crawl">
  </table>
</form>
<p>Link list: ${destination}</p>

${linkliste}

</body>
</html>