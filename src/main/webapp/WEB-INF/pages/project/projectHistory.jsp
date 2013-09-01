<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1>Historie projektu</h1>
<c:forEach items="${projectHistory}" var="history">
    <p>
        <fmt:formatDate value="${history.dateChange}" pattern="yyyy. MM. dd. 'v' HH:mm"/>
        :  ${history.note}</p>
</c:forEach>