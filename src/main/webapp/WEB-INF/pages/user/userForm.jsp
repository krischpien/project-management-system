<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>


<div id="content">
    <s:url value="" var="actionUrl"/>
    
    <sf:form id="userForm" method="post" commandName="user" action="${actionUrl}">
        
        <div id="custom-select">
        <sf:select path="roles" required="required" multiple="false">
            <sf:options items="${mainRoles}" itemValue="id" itemLabel="description"/>
            <!--<option value="2">Zákazník</option><option value="3">Dodavatel</option>-->
        </sf:select>
        </div>
        <br class="clear"/>
        <sf:hidden path="id"/>
        
        <label for="name">Jméno:</label><sf:input path="name" onchange="checkUserName()" /><span class="info" id="nameInfo"><sf:errors path="name" cssClass="errorMessage"/></span><br/>
        <label for="email">Email: </label><sf:input path="email" onchange="checkUserEmail()"/><span class="info" id="emailInfo"><sf:errors path="email" cssClass="errorMessage"/></span><br/>
        <label for="password">Heslo: </label><sf:password path="password" required="required"/><span class="info" class="passwordInfo"><sf:errors path="password" cssClass="errorMessage"/></span><br/>
        <!--<label for="passwordAgain">Heslo znovu: </label><input id="passwordAgain" name="passwordAgain" type="password"/><span class="passwordAgainInfo"></span><br/>-->
        <label for="roles"><h3>Role: </h3></label><br/>
        <div id="assignableRoles">
        <sf:checkboxes path="roles" items="${assignableRoles}" itemValue="id" itemLabel="description"/>
        </div>
        <br/>

        <input type="submit" value="uložit"/><br/>
    </sf:form>
        <p id="info"></p>
</div>
    
    <script>
        $("#assignableRoles").buttonset();
        
        function checkUserName(){
            var username = $("#name").val();
            $.ajax({
            type: "GET",
            url: "<s:url value="/ajax/checkUserName/"/>"+username,
            success: function(response){
            // vse ok:
                $('#nameInfo').html(response);
                
            },
            error: function(e){
                $('#info').html(e);
            }
        });
        }
        
        function checkUserEmail(){
            var email = $("#email").val();
            $.ajax({
            type: "GET",
            url: "<s:url value="/ajax/checkUserEmail/"/>",
            data:"email="+email,
            success: function(response){
            // vse ok:
                $('#emailInfo').html(response);
                
            },
            error: function(e){
                $('#info').html(e);
            }
        });
        }
        
        
        
        
    </script>    
    
    
    <style>
        label.error{
        margin:0 10px;
	width: auto;
	display: inline;
        }
        span.info{
            margin:0 10px;
        }
    </style>