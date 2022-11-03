<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 수정</title>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr>
		<table width="500" border="1" cellpadding="0" cellspacing="0">
			<form action="modify">
				<input type="hidden" value="${content.bid }" name="bid">
				<tr>
					<th width="100">글 번호</th>
					<td>${content.bid }</td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td>${content.bhit }</td>
				</tr>
				
				<tr>
					<th>글쓴이</th>
					<td><input type="text" name="bname" value="${content.bname }" size="60"></td>
				</tr>
				
				<tr>
					<th>글제목</th>
					<td><input type="text" name="btitle" value="${content.btitle }" size="60"></td>
				</tr>
				
				<tr>
					<th valign="top">내 용</th>
					<td height="100" valign="top">
						<textarea rows="10" cols="45" size="100" name="bcontent">${content.bcontent }</textarea>
					</td>
				</tr>
				
				<tr>
					<th>등록일</th>
					<td>${content.bdate }</td>
				</tr>
				<tr height="30">
					<td colspan="2" align="right">
						<input type="submit" value="수정완료">
						<input type="reset" value="취소" onclick="javascript:window.location='modify_view'">
						<input type="button" value="목록" onclick="javascript:window.location='list'">
					</td>
				</tr>
			</form>
		</table>

</body>
</html>