<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<head>
<title>Customer Registration Form</title>
<style>
	.error {color:red}
</style>
</head>
<form:form action="processForm" modelAttribute="customer">
	<br/>
	<b>Please fill the form : * is mandatory</b>
	<br/>
	First Name:<form:input path="firstName"/>
	<br/>
	Last Name (*):<form:input path="lastName"/>
	<form:errors path="lastName" cssClass="error"/>
	<br/>
	<br/>
	Free passes: <form:input path="freePasses"/>
	<form:errors path="freePasses" cssClass="error"/>
	<br/>
	<br/>
	Enter French postal code: <form:input path="postalCode"/>
	<form:errors path="postalCode" cssClass="error"/>
	<br/>
	<br/>
	Enter <i>Course code:</i> <form:input path="courseCode"/>
	<form:errors path="courseCode" cssClass="error"/>
	<br/>
	<br/>
	<input type="Submit" value="Submit Customer" />
	<br/>
</form:form>
</body>
</html>