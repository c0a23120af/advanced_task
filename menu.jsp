<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<%--適宜，パスを変更してください--%>
<form method="POST" enctype="multipart/form-data" action="/2025n/top/topServlet">
</form>

<!-- クイズへのボタン追加 -->
<form action="/2025n/question/QuestionsServlet" method="get" style="margin-top:20px;">
    <input type="submit" value="クイズへ" />
</form>

<!-- 用語辞書へのボタン追加 -->
<form action="/2025n/register.jsp" method="get" style="margin-top:10px;">
    <input type="submit" value="用語辞書へ" />
</form>
</body>
</html>