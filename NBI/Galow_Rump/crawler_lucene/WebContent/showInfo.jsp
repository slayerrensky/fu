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
<p>Link list: ${destination}</p>


</body>
</html>