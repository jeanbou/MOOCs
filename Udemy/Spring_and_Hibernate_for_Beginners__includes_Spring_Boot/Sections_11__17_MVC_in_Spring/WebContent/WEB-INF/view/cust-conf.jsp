<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<title>Customer Confirmation Form Page</title>
<body>
<head>Customer Confirmation Page</head>
	<br/>
	First Name Confirmed ${customer.firstName}
	<br/>
	Last Name ${customer.lastName}
	<br/>
	Free Passes ${customer.freePasses}
	<br/>
	French postal code is ${customer.postalCode}
	<br/>
	Course code is ${customer.courseCode}
	<br/>
</body>
</html>