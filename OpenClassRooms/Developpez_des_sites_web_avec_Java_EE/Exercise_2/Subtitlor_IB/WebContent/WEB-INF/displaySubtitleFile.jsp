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
<title>Editer les sous-titres</title>
</head>
<body>
	<jsp:include page="/css/style.css" />
	<header>
		<H2 align="center" style="text-align: center;">Traduction de
			fichier de sous titre</H2>
	</header>


	<section id="container-fluid">

		<div class="row">
			<div class="col-md-12 col-lg-12">
				<div class="col-md-3 col-lg-3">
					<nav class="navUpload">
						<%@ include file="menu.jsp"%>
					</nav>
				</div>
				<div class="col-md-8 col-lg-8">
					<fieldset class="fieldsetDisplaySubtitle">
						<legend>
							<strong>&nbsp;&nbsp;Continuer une traduction&nbsp;&nbsp;</strong>
						</legend>
						<c:if test="${ !empty erreur}">
							<c:out value="${ erreur}" />
						</c:if>
						<c:choose>
							<c:when test="${!empty errorDB }">
								<H3 class="errorDB">
									<c:out value="${ errorDB }" />
								</H3>
							</c:when>
							<c:when test="${!empty erreurPasTradFinies }">
								<H3>
									<c:out value="${ erreurPasTradFinies }" />
								</H3>
							</c:when>
							<c:when test="${!empty erreurDisplay }">
								<P class="echec">
									<c:out value="${ erreurDisplay }" />
								</P>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						<form method="post" name="form" id="form" action="recordSubtitle">
							<div id="listFichier">
								<ul>
									<c:forEach items="${ listFiles }" var="line" varStatus="status">
										<c:if test="${line.isTranslationFinished()}">
											<li><div class="btnEnligne">
													<P>
														<c:out value="${line.myString()}" />
														&nbsp;&nbsp;<span>Traduction finie&nbsp;&nbsp;</span>
														<button class="btn btn-md btn-primary pull-right">
															<a
																href="displaySubtitleFile?name=${line.myString()}&amp;finished=${line.isTranslationFinished()}">Modifier</a>
														</button>
														&nbsp; &nbsp;
														<button class="btn btn-md btn-primary pull-right">
															<a
																href="exporterFile?name=${line.myString()}&amp;finished=${line.isTranslationFinished()}">Exporter</a>
														</button>
													</P></li>
										</c:if>
										<c:if test="${!line.isTranslationFinished()}">
											<li><div class="btnEnligne">
													<P>
														<c:out value="${line.myString()}" />
														&nbsp;&nbsp;<span>Traduction partielle&nbsp;&nbsp;</span><a
															class="btn btn-md btn-primary pull-right"
															href="displaySubtitleFile?name=${line.myString()}&amp;finished=${line.isTranslationFinished()}">Modifier</a>&nbsp;&nbsp;
													</P>
												</div></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</form>
					</fieldset>
				</div>
				<div class="col-md-1 col-lg-1">&nbsp;</div>
			</div>	
		</div>
	</section>
</body>
</html>
