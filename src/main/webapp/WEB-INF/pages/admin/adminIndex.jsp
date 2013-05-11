<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<p>Cron aktivní: ${cronActive} :: <a href="<s:url value="/admin/cron/on"/>">on</a> || <a href="<s:url value="/admin/cron/off"/>">off</a></p>