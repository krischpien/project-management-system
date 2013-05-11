<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div id="footer" class="pageEdge">
    <p>&copy; Jan Krist 2012 @ <a href="http://www.vsmie.cz">www.vsmie.cz</a></p>
    <p>Vedoucí: Ing. Jiří Franc</p>
</div>


<!--DIALOG MESSAGE-->        
<div id="dialog-message" title="Zpráva" style="display: none">
    <p class="msg"><span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 50px 0;"></span> ${message}</p>
    <p><sf:errors path="*"/></p>
</div>

<c:if test="${message != null}">
<script>
        $( "#dialog-message" ).dialog({
        modal: true,
//        autoOpen:false,
        buttons: {
        Ok: function() {
        $( this ).dialog( "close" );
            }
        }
    });
</script>             
</c:if>
<!--/DIALOG MESSAGE-->        
