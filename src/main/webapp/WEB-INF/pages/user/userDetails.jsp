<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<s:url value="/admin/user/edit/edit.do?uid=${user.id}" var="editUserUrl"/>
<h1>Detaily uživatele <b>${user.name}</b></h1>
<p>Email: ${user.email}</p>
<p>Role:</p>
<p>
    <c:forEach items="${user.roles}" var="role" varStatus="loop">
            <em>${role.description}${loop.last? '':','}</em>
    </c:forEach>
</p>
<p>Naposledy přihlášen: 
    <c:if test="${user.lastLogin !=null}">
        <b><fmt:formatDate value="${user.lastLogin}" pattern="dd. MM. yyyy (HH:mm)"/></b> z ip adresy <b>${user.lastIp}</b>
    </c:if>
        <c:if test="${user.lastLogin ==null}">
            <b>doposud nepřihlášen.</b>
        </c:if>
</p>
<a href="${editUserUrl}">editace</a>

