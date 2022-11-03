<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 쓰기</title>
</head>
<body>
	<h2>자유게시판 댓글 쓰기</h2>
	<hr>
	<table width="500" border="1" cellpadding="0" cellspacing="0">	
		<form action="reply">
			<input type="hidden" value="${content.bid }" name="bid">
			<input type="hidden" value="${content.bgroup }" name="bgroup">
			<input type="hidden" value="${content.bstep }" name="bstep">
			<input type="hidden" value="${content.bindent }" name="bindent">
			<input type="hidden" value="[댓글]${content.btitle }" name="btitle">
			
			<tr>
				<th>글 번호</th>
				<td>${content.bid }</td>
			</tr>
			<tr>
				<th>댓글작성자</th>
				<td><input type="text" name="bname" size="60"></td>
			</tr>

			<tr>
				<th>글제목</th>
				<td>[댓글]${content.btitle }</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td height="100" valign="top"> ${content.bcontent } 
			</tr>	
			 
			<tr>
				<th>댓글내용</th>
				<td>
				<textarea rows="10" cols="45" name="bcontent">[댓글]${indent.bcontent }</textarea>
				</td>
			</tr>
			
			
			<tr>
				<td colspan="2" align="right" height="30">
					<input type="submit" value="댓글">
					<input type="button" value="취소" onclick="javascript:window.location='list'">
					<input type="button" value="목록" onclick="javascript:window.location='list'">
				</td>
			</tr>
		</form>
	</table>
</body>
</html>