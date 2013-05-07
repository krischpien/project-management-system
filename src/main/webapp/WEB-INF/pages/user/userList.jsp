<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<h1>Výpis uživatelů</h1>
<ul>
    <c:forEach items="${userList}" var="user">
        <li>
            <a href="<s:url value='/admin/user/details/${user.name}'/>">
                ${user.name}
            </a>
        </li>
    </c:forEach>
</ul>
