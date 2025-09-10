<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property='title' /></title>
    <sitemesh:write property='head' />
</head>
<body>
    <header>
        <h1>HEADER CHUNG CỦA WEBSITE</h1>
    </header>

    <main>
        <sitemesh:write property='body' />
    </main>

    <footer>
        <p>FOOTER CHUNG CỦA WEBSITE</p>
    </footer>
</body>
</html>	