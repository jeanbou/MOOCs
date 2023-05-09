<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<title>Student Form</title>
<body>
<head>Confirmation Page</head>
	<br/>
	First Name Confirmed ${student.firstName}
	<br/>
	Last Name ${student.lastName}
	<br/>
	Country of student ${student.country}
	<br/>
	Favorite Programming Language ${student.favoriteLanguage}
	<br/>
		Favorite OS 
		<ul> 
			<c:forEach var="tmp" items="${student.operatingSystems}">
				<li> ${tmp} </li>
	  		</c:forEach>
	  </ul>
	<br/>
</body>
</html>