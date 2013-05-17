<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


<h1>Administrace systému</h1>
<div id="services">
<h2>Nastavení služeb:</h2>
<div class="service">
    <h3>Notifikace mailem:
    <c:choose>
        <c:when test="${mailActive}"><span class="on">zapnuto</span></c:when>
        <c:otherwise><span class="off">vypnuto</span></c:otherwise>
    </c:choose> </h3>
    <a href="<s:url value="/admin/service/mail/on"/>">zapnout</a> | <a href="<s:url value="/admin/service/mail/off"/>">vypnout</a>
</div>


<div class="service">
<h3>Upozorňování na nesplacené zálohy:
    <c:choose>
        <c:when test="${cronActive}"><span class="on">zapnuto</span></c:when>
        <c:otherwise><span class="off">vypnuto</span></c:otherwise>
    </c:choose> 
        </h3>
    <a href="<s:url value="/admin/service/audit/on"/>">zapnout</a> | <a href="<s:url value="/admin/service/audit/off"/>">vypnout</a>
</div>

</div>

<div id="loggingEvents">
    <h2>Události systému</h2>
<p>Strana: 
        <c:forEach begin="0" end="${pageCount}" var="i">
            <c:set value="${i * 50}" var="offset"/>
            
            <a href="/pms/admin/index?logOffset=${offset}&logLimit=50">${i + 1}</a>
        </c:forEach>
</p>
    <c:if test="${!empty loggingEvents}">
        
        <table id="logTable">
            <thead>
                <tr>
                    <th class="level">LEVEL</th>
                    <th class="logger">Logger</th>
                    <th class="message">Zpráva</th>
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
        <input type="submit" value="Vyčistit log"/>
    </form>