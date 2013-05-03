<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/css/pms-login.css"/>
        <title>ProjectMS :: Přihlášení</title>
    </head>
    <body>
    <h1>Přihlášení do aplikace <span>Project Management System</span></h1>
     
     <s:url var="authProcUrl" value="/login_check"/>
     
<form method="POST" action="${authProcUrl}">
        <p>Jméno:</p><input type="text" name="username" placeholder="Přihlašovací jméno" required="required"/>
        <p>Heslo:</p> <input type="password" name="password" placeholder="Heslo" required="required"/>
        <div class="clearer"></div>
        <input id="submit" type="submit" value="Přihlásit" name="submit"/>
</form>
        
        <c:if test="${param.logerr != null}">
            <p class="err">Chyba: Byly zadány chybné přihlašovací údaje.</p>
        </c:if>

        <c:if test="${param.logout != null}">
            <p class="msg">Byli jste úspěšně odhlášeni.</p>
        </c:if>

        
        
    </body>
</html>
