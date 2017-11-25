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
<title>edition</title>
</head>
<body>
	<jsp:include page="/css/style.css" />
	<header>
		<H2>Traduction de fichier de sous titre</H2>
	</header>

	<section id="container-fluid">

		<div class="row col-md-12 col-lg-12">
			<div class="col-md-3 col-lg-3">
				<nav>
					<%@ include file="menu.jsp"%>
				</nav>
			</div>
			<div class="col-md-8 col-lg-8">
				<fieldset>
					<legend>
						<strong>&nbsp;&nbsp;Traduire un fichier&nbsp;&nbsp;</strong>
					</legend>
					<c:if test="${ !subtitlesHandler.isTraductionFinished() }">
						<c:if test="${ empty sessionScope.passageParRetrieve }">
							<form method="post" name="form" id="form" action="recordSubtitle">
								<input type="submit" class="inputButtonFixedRight"
									style="top: 30%;" value="Sauvegarder" />
							</form>
						</c:if>
					</c:if>
					<c:if test="${ subtitlesHandler.isTraductionFinished() }">
						<form method="post" name="form2" id="form2" action="exporter">
							<input type="submit" class="inputButtonFixedRight"
								style="top: 20%;" value="Exporter Fichier" />
						</form>
					</c:if>
					<form method="post" name="form3" id="form3" action="modifSubtitle">
						<input type="submit" class="inputButtonFixedRight"
							style="top: 30%;" value="Sauvegarder" />

						<c:choose>
							<c:when test="${!empty errorDB }">
								<H3 class="errorDB">
									<c:out value="${ errorDB }" />
								</H3>
							</c:when>
							<c:when test="${subtitlesHandler.isTraductionFinished()}">
								<H3>
									Traduction terminée pour le fichier <span style="color: black;"><c:out
											value="${ sessionScope.nomFichier }" /></span>
								</H3>
							</c:when>
							<c:when test="${!subtitlesHandler.isTraductionFinished()}">
								<H3>
									Traduction partielle pour le fichier <span
										style="color: black;"><c:out
											value="${ sessionScope.nomFichier }" /></span>
								</H3>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						<br />
						<table id="tabRecord"
							class="table table-bordered table-striped table-condensed">
							<tr>
								<th>Fichier 1 a traduire</th>
								<th>Fichier 2 traduit</th>
							</tr>
							<c:forEach items="${ doubleSubtitles }" var="line"
								varStatus="status">
								<tr>
									<c:if
										test="${line.customizedstring1().isVide() == false && line.customizedstring1().isVide() == false}">
										<td style="text-align: center;"><c:out
												value="${ line.customizedstring1().myString() }" /></td>
										<c:choose>
											<c:when
												test="${line.customizedstring2().isNumeric() == true}">
												<td style="text-align: center;"><c:out
														value="${ line.customizedstring2().myString() }" /></td>
											</c:when>
											<c:when
												test="${line.customizedstring2().isTimeValues() == true}">
												<td style="text-align: center;"><c:out
														value="${ line.customizedstring2().myString() }" /></td>
											</c:when>
											<c:when
												test="${line.customizedstring2().isTranslated() == true}">
												<td style="text-align: center;"><c:out
														value="${ line.customizedstring2().myString() }" /></td>
											</c:when>
											<c:when
												test="${line.customizedstring2().isnotTranslated() == true }">
												<td><input class="translatedLine2" type="text"
													name="line${ status.index }" id="line${ status.index }"
													value="" /></td>
											</c:when>
											<c:when test="${line.customizedstring2().isVide() == false }">
												<td><input class="translatedLine" type="text"
													name="line${ status.index }" id="line${ status.index }"
													value="${ line.customizedstring2().myString() }" /></td>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</form>
				</fieldset>
			</div>
			<div class="col-md-1 col-lg-1">&nbsp;</div>
		</div>
	</section>
</body>
</html>
