<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글목록</title>
</head>
<body>
	<h2>자유게시판</h2>
	<hr>
	
	총 게시글 수: ${boardCount } 개	
	
	<table border="1" cellspacing="0" cellpadding="0" width="1000">
		<tr bgcolor="pink" height="40">
			<th>번호</th>
			<th>글쓴이</th>
			<th width="500">글제목</th>
			<th>게시일</th>
			<th>조회수</th>
		</tr>
				
		<c:forEach items="${list }" var="bdto">   <!-- BListCommand에 객체명을 list라고 작성하고 이것이 html과 연결시켜준다./ var="변수명"-->
		<tr height="30" align="center">
			<td>${bdto.bid }</td>
			<td>${bdto.bname }</td>
			<td align="left">
				<c:forEach begin="1" end="${bdto.bindent }">&nbsp;&nbsp;&nbsp;</c:forEach>  <!-- 반복문 넣기 -->
				<a href="content_view?bid=${bdto.bid }">${bdto.btitle }</a>
			</td> 
			<td>${bdto.bdate }</td>
			<td>${bdto.bhit }</td>
		</tr>
		</c:forEach>
		
		<tr height="30">
			<td colspan="5" align="right"><input type="button" value="글쓰기" onclick="javascript:window.location='write_form'"></td>
		</tr>	
	</table>
	
</body>
</html>