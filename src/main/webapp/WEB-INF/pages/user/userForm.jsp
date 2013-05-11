<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div id="content">
    <s:url value="" var="actionUrl"/>
    
    <sf:form id="userForm" method="post" commandName="user" action="${actionUrl}">
        
        <div id="custom-select">
        <sf:select path="roles" required="required" title="This field should not be left blank." multiple="false">
            <sf:options items="${mainRoles}" itemValue="id" itemLabel="description"/>
            <!--<option value="2">Zákazník</option><option value="3">Dodavatel</option>-->
        </sf:select>
        </div>
        <br class="clear"/>
        <sf:hidden path="id"/>
        <label for="name">Jméno:</label><sf:input path="name"/><br/>
        <label for="email">Email: </label><sf:input path="email"/><br/>
        <label for="password">Heslo: </label><sf:password path="password" required="required"/><span class="password info"></span><br/>
        <label for="passwordAgain">Heslo znovu: </label><input id="passwordAgain" name="passwordAgain" type="password"/><span class="passwordAgain info"></span><br/>
        
        <label for="roles">Role: </label><br/>
        <div id="assignableRoles">
        <sf:checkboxes path="roles" items="${assignableRoles}" itemValue="id" itemLabel="description"/>
        </div>
        <br/>

        <input type="submit" value="uložit"/><br/>
        <sf:errors path="*" cssClass="errorsBox" /><br/>
    </sf:form>
        <p id="info"></p>
</div>
    
    <script>
        $("#assignableRoles").buttonset();
        
        
        var formSubmit = $("form#userForm input[type=submit]");
//        disableForm();
        
        function disableForm(){
            formSubmit.attr("disabled", true);
            formSubmit.attr("title", "Nemáte správně vyplněna všechna potřebná pole")
        }
        
        function enableForm(){
            formSubmit.attr("disabled", false);
            formSubmit.attr("title", "")
        }
        
        
        $("input#password").change(function(){
            var pass = $(this).val();
            var errElement = $("span.password");
            if(pass.length <5){
                errElement.text("Příliš krátké heslo");
                disableForm();
            } 
            else{
                errElement.text("");
                enableForm();
            }
        })
        $("input#passwordAgain").change(function(){
            var errElement = $("span.passwordAgain");
            var pass = $("input#password").val();
            var passCheck = $(this).val();
            if(pass != passCheck){
                errElement.text("Hesla se neshodují");
                disableForm();
            }
            else{
                errElement.text("");
                enableForm();
            }
        })
        
    </script>    
    
    
    