<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h1><fmt:message key="system.header"/></h1>
<div id="services">
<h2><fmt:message key="system.services"/></h2>
<div class="service">
    <h3><fmt:message key="system.service.mail"/>:
    <c:choose>
        <c:when test="${mailActive}"><span class="on"><fmt:message key="system.service.state.enabled"/></span></c:when>
        <c:otherwise><span class="off"><fmt:message key="system.service.state.disabled"/></span></c:otherwise>
    </c:choose> </h3>
    <a href="<s:url value="/admin/service/mail/on"/>"><fmt:message key="system.service.action.on"/></a> | <a href="<s:url value="/admin/service/mail/off"/>"><fmt:message key="system.service.action.off"/></a>
</div>


<div class="service">
<h3><fmt:message key="system.service.audit"/>:
    <c:choose>
        <c:when test="${cronActive}"><span class="on"><fmt:message key="system.service.state.enabled"/></span></c:when>
        <c:otherwise><span class="off"><fmt:message key="system.service.state.disabled"/></span></c:otherwise>
    </c:choose> 
        </h3>
    <a href="<s:url value="/admin/service/audit/on"/>"><fmt:message key="system.service.action.on"/></a> | <a href="<s:url value="/admin/service/audit/off"/>"><fmt:message key="system.service.action.off"/></a>
</div>

</div>

<div id="loggingEvents">
    <h2><fmt:message key="system.events"/></h2>
<p><fmt:message key="system.events.paginator"/>:
        <c:forEach begin="0" end="${pageCount}" var="i">
            <c:set value="${i * 50}" var="offset"/>
            
            <a href="/pms/admin/index?logOffset=${offset}&amp;logLimit=50">${i + 1}</a>
        </c:forEach>
</p>
    <c:if test="${!empty loggingEvents}">
        
        <table id="logTable">
            <thead>
                <tr>
                    <th class="level">LEVEL</th>
                    <th class="logger">Logger</th>
                    <th class="message">Message</th>
                </tr>
            </thead>
        <c:forEach items="${loggingEvents}" var="loggingEvent">
            <tr>
                <td>${loggingEvent.levelString}</td>
                <td>${loggingEvent.loggerName}</td>
                <td>${loggingEvent.formattedMessage}</td>
            </tr>
                
        </c:forEach>
        </table>
    </c:if>
</div>

    <form method="post" action="<s:url value="/admin/clearLog.do"/>">
        <input type="submit" value="<fmt:message key="system.events.clear"/>"/>
    </form>