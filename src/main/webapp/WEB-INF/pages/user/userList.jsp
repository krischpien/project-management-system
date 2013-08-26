<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:url value='/admin/user/details' var="userDetailsUrl"/>


<h1><fmt:message key="users.header"/></h1>
<ul class="simpleList">
    <li><span class="inline-icon ui-icon ui-icon-person red"></span> <a title="<fmt:message key="users.admin"/>">admin</a></li>
    <c:forEach items="${userList}" var="user" begin="1">
        <li><span class="inline-icon ui-icon ui-icon-person"></span>
            <a href="${userDetailsUrl}/${user.name}" title="<fmt:message key="users.detail"/> ${user.name}">
                ${user.name}
            </a>
                <form action="<s:url value="/admin/user/edit/deleteUser.do"/>" method="POST" class="miniActionForm" title="<fmt:message key="users.delete"/> ${user.name}">
                    <input type="hidden" value="${user.id}" name="deletedUserId"/>
                    <button type="submit"><span class="ui-icon ui-icon-trash"></span></button>
                </form>
                
                <form method="GET" action="<s:url value='/admin/user/edit/edit.do'/>" class="miniActionForm" title="<fmt:message key="users.edit"/> ${user.name}">
                    <input type="hidden" name="uid" value="${user.id}"/>
                    <button type="submit"><span class="ui-icon ui-icon-pencil"></span></button>
                </form>
        </li>
    </c:forEach>
</ul>

<a class="actionButton" href="<s:url value="/admin/user/edit/newUser.do"/>"><fmt:message key="users.createuser"/></a>

<script>
    $("button").button();
</script>