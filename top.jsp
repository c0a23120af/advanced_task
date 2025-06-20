<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ファイルアップロード</title>
</head>
<body>
	<%--適宜，パスを変更してください--%>
<form method="POST" enctype="multipart/form-data" action="/2025n/top/topServlet">
</form>

<!-- メニュー画面へのボタン追加 -->
<form action="/2025n/menu.jsp" method="get" style="margin-top:20px;">
    <input type="submit" value="メニュー画面へ" />
</form>

<!-- ユーザー登録画面へのボタン追加 -->
<form action="/2025n/register.jsp" method="get" style="margin-top:10px;">
    <input type="submit" value="ユーザー登録画面へ" />
</form>
</body>
</html>