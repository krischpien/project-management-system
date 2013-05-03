<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="author" content="<tiles:insertAttribute name="author"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/pms.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/pms-theme/jquery-ui-1.9.2.custom.min.css"/>"/>
        <title>${initParam.appName} :: <tiles:insertAttribute name="title"/></title>
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-1.8.3.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-ui-1.9.2.custom.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/i18n/jquery.ui.datepicker-cs.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/pms.js'/>"></script>
        <link rel="icon" type="image/png" href="<s:url value='/resources/img/pms-favico.png'/>">
    </head>
    <tiles:insertAttribute name="header"/>
    
    <div id="content">
        <tiles:insertAttribute name="content"/>
    </div>
    
    <tiles:insertAttribute name="footer"/>
    
</html>
