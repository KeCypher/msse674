<%@ page session="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter Your Registration Details</h3>
        <form:form method="POST" 
           action="/icaria-flight-system/register" modelAttribute="registration">
             <table>
                <tr>
                    <td><form:label path="username">Username</form:label></td>
                    <td><form:input path="username"/></td>
                </tr>
                <tr>
                    <td><form:label path="firstname">First Name</form:label></td>
                    <td><form:input path="firstname"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastname">Last Name</form:label></td>
                    <td><form:input path="lastname"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="password2">Password COnfirmation</form:label></td>
                    <td><form:input path="password2"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
</form:form>
    </body>
</html>