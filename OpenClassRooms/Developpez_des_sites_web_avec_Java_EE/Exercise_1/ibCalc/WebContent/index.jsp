<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>IB's calculator</title>
	</head>
<body>
    <p id="res"><b>Welcome, here you will see the result of the calculation in French</b><br/>Résultat de opération : <b>${ !empty result ? result : 'No result for instance' }</b></p>
	<p id="intro"><b>This is the basic calculator created by IB as the study project</b><br/>
		<ul>
			<li>Please input the values in the fields</li>
			<li>Choose the opperation</li>
			<li>Press button "Envoyer"</li>
		</ul>
		<i>* No classic zero mess-up please when you use operation divide<br/>** Absence of data in field considered as 0 value<br/>*** Use . as a float value separator</i>
	<br/><br/>
	</p>
	
    <p id="calc">	
	<form>
		<input type="text" name="num_field1" id="num_field1"/>
		<select name="operation" id="operation" value="1">
			<option value="1">+</option>
			<option value="2">-</option>
			<option value="3">*</option>
			<option value="4">/</option>
		</select>
		<input type="text" name="num_field2" id="num_field2"/>
		<input type="submit" name="submit" id="submit" value="Envoyer"/> 
	</form>
	</p>	
</body>
</html>