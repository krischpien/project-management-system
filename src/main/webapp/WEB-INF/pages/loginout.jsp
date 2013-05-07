<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-1.9.1.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/jquery-ui-1.10.3.custom.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/resources/js/pms.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/loginForm.css"/>
        <link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/jquery-ui/redmond/jquery-ui.css"/>"/>
        <title>ProjectMS :: Přihlášení</title>
    </head>
    <body>
       
<s:url var="authProcUrl" value="/loginCheck"/>

    <div id="loginArea">
        <h1>Přihlášení do aplikace <span>Project Management System</span></h1>
        <form method="POST" action="${authProcUrl}">
            <label for="username">Jméno:</label><input type="text" name="username" placeholder="Přihlašovací jméno" required="required"/><br/>
            <label for="password">Heslo: </label><input type="password" name="password" placeholder="Heslo" required="required"/><br/>
            <input id="submit" type="submit" value="Přihlásit" name="submit"/>
        </form>

    
    </div>

            
<!--DIALOG MESSAGE-->        
<div id="dialog-message" title="Zpráva">
        <c:if test="${param.logerr != null}">
            <p class="err"><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>Chyba: Byly zadány chybné přihlašovací údaje.</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p class="msg"><span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 50px 0;"></span> Byli jste úspěšně odhlášeni.</p>
        </c:if>
</div>
<c:if test="${param.logerr != null || param.logout != null}">
<script>
        $( "#dialog-message" ).dialog({
        modal: true,
    //    autoOpen:false,
        buttons: {
        Ok: function() {
        $( this ).dialog( "close" );
            }
        }
    });
</script>             
</c:if>
<!--/DIALOG MESSAGE-->        
</body>
</html>
