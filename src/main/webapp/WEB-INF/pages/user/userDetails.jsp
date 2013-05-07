<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>%>
<!DOCTYPE html>
<s:url value="/admin/user/edit/edit.do?uid=${user.id}" var="editUserUrl"/>
<h1>Detaily uživatele <b>${user.name}</b></h1>
<p>email: ${user.email}</p>
<a href="${editUserUrl}">editace</a>

