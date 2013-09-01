<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!--form-->
<label for="dateCreate">Vytvořeno:</label><sf:input path="dateCreate" id="dateCreate" readonly="true" class="readonly"/><br/> <!--readOnly-->
<div class="fillingPart">
    <label for="dateDeadline">Termín:</label><sf:input path="dateDeadline" id="dateDeadline" readonly="true"/><br/>
    <label for="projectContent">Obsah:</label><br/>
    <sf:textarea path="content" id="projectContent" cssClass="projectContent" /><br/>
    <label for="note">Poznámka:</label><sf:textarea path="note"/><br/>
</div>

<label for="budget">Rozpočet:</label><sf:input path="budget" id="budget" readonly="true" class="readonly"/><br/> <!--readOnly-->

<div class="fillingPart">
<sec:authorize ifAllGranted="ROLE_CUSTOMER, ROLE_MANAGER">
    <input type="hidden" name="formAction" value="place"/>
    <input type="submit" value="Potvrdit" title="Potvrdit detaily projektu"/>
</sec:authorize>
    
<sec:authorize access="!hasRole('ROLE_CUSTOMER') || !hasRole('ROLE_MANAGER')">
    <p>Čeká se na managera ze strany zákazníka.</p>
</sec:authorize>    
</div>
<!--/form-->

<script>
$(function() {
$( "#dateDeadline" ).datepicker({
    dateFormat: "mm-dd-yy",
    minDate:2
    });
});
</script>