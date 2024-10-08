<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix=c --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>qna 목록</h1>
<br>
<table border="1" cellpadding="5">
	<tr>
		<th>ID </th>
		<th>Subject </th>
		<th>Content </th>
		<th>CreateDate</th>
	</tr>

	<%-- <c:forEach var="q" items="${qlist }>
		<tr>
			<td>${q.id}</td>
			<td>${q.subject}</td>
			<td>${q.content}</td>
			<td>${q.createDate  }</td>
		</tr>
	</c:forEach>
 --%>
</table>

<br>
<a href="/insertForm"> 등록화면 </a>
<a href="/"> 초기화면 </a>

</body>
</html>