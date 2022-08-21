<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<title>Student Form</title>
<body>
<head>Registration Form</head>
<form:form action="processForm" modelAttribute="student">
	First Name <form:input path="firstName"/>
	<br/>
	Last Name <form:input path="lastName"/>
	<br/>
	<br/>
	Select you country 
	<form:select path="country">
	 	<!-- form:options items="${student.countryOptions}"/ -->
	 	<form:options items="${countryOptions}"/>
	</form:select>
	
	<br/>
	<br/>
	 Favorite languages:
	 
	<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"/>
	<br/>
	<br/>
	Favorite OS:
	 
	Linux <form:checkbox path="operatingSystems" value="Linux"/>
	Win 10 <form:checkbox path="operatingSystems" value="Win 10"/>
	<br/>
	<br/>
	<input type="Submit" value="Submit" />
	<br/>
</form:form>
</body>
</html>