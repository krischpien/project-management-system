<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<h1>User Index</h1>
<a class="actionButton" href="<s:url value="/admin/user/edit/newUser.do"/>">Vytvoř uživatele</a>
<a class="actionButton" href="<s:url value="/admin/user/list"/>">Výpis uživatelů</a>