<%@ include file="/WEB-INF/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw=="
	crossorigin="anonymous">
<style type="text/css">
[class*="col"] {
	margin-bottom: 20px;
	margin-left: -50px;
}

img {
	width: 100%;
}

.block {
	display: block;
}

.well {
	background-color: #CCC;
	padding: 20px;
}

[class*="col-"], footer {

	border-radius: 6px;
	line-height: 40px;
}

.full button span {
	background-color: limegreen;
	border-radius: 32px;
	color: black;
}

.partially button span {
	background-color: green;
	border-radius: 32px;
	color: black;
}

#eventForm .form-control-feedback {
	top: 0;
	right: -15px;
}

label, input {
	display: inline-block;
}

input.text {
	margin-bottom: 12px;
	width: 95%;
	padding: .4em;
}

fieldset {
	padding: 0;
	border: 0;
	margin-top: 25px;
}

h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 350px;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 50%;
}

div#users-contain table td, div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}
</style>
<title>Download fichier</title>
</head>
<body>
	<jsp:include page="/css/style.css" />
	<header>
		<H2>Export du fichier de sous titre</H2>
	</header>

	<section id="container-fluid">

		<div class="row col-md-12 col-lg-12">
			<div class="col-md-3 col-lg-3">
				<nav class="navUpload">
					<%@ include file="menu.jsp"%>
				</nav>
			</div>
			<div class="col-md-8 col-lg-8">
				<fieldset>
					<legend>
						<strong>&nbsp;&nbsp;Export du fichier&nbsp;&nbsp;</strong>
					</legend>
					<c:if test="${ !empty errorDb }">
						<P class="echec">
							<c:out value="${ errorDb }" />
						</P>
					</c:if>
					<c:if test="${ !empty errorIO }">
						<P class="echec">
							<c:out value="${ errorIO }" />
						</P>
					</c:if>
					<form method="get"
						action="downloadFileServlet?fileName=${fileNameTrad}"
						enctype="multipart/form-data">
						<P>
							<%-- <button type="submit" style="align: center;" class="btnDownload"
								value="Downloader le fichier ${fileNameTrad}" ></button> --%>
							<button type="submit" style="align: center;"
								class="btn btn-lg btn-primary">Downloader le fichier
								${fileNameTrad}</button>
						</P>
					</form>
					<br /> <br /> <br />
				</fieldset>
			</div>
			<div class="col-md-1 col-lg-1">&nbsp;</div>
		</div>
	</section>

</body>
</html>
