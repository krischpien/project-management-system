<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:importAttribute name="activePage" scope="request" ignore="true"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="author" content="<tiles:insertAttribute name="author"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/pms.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/jquery-ui/redmond/jquery-ui.css"/>"/>
        <title>${initParam.appName} ${activePage}:: <tiles:insertAttribute name="title"/></title>
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-1.9.1.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/pms.js'/>"></script>
        <link rel="icon" type="image/png" href="<s:url value='/resources/img/pms-ico.png'/>">
    </head>
    <tiles:insertAttribute name="header"/>
    
    <div id="content">
        <tiles:insertAttribute name="content"/>
    </div>
    
    <tiles:insertAttribute name="footer"/>
    
</html>
