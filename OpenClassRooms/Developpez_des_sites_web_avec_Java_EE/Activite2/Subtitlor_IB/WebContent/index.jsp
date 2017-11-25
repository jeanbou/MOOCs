<%@ include file="/WEB-INF/taglibs.jsp"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%> --%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw=="
	crossorigin="anonymous">
<title>Bienvenue</title>
</head>
<jsp:include page="/css/style.css" />
<body>

	<header>
		<H2>Traduction de fichier de sous titre</H2>
	</header>

	<section id="container-fluid">

		<div class="row">
			<div class="col-md-12 col-lg-12">
				<div class="col-md-3 col-lg-3 text-center">
					<nav>
						<%@ include file="/WEB-INF/menu.jsp"%>
					</nav>
				</div>
				<div class="col-md-7 col-lg-7">
					<fieldset class="fieldsetIndex">
						<legend>
							<strong>les fichiers de sous titre .SRT</strong>
						</legend>
						<P class="padding">Un fichier SRT stocke les sous-titres
							incrustés dans une vidéo dans un fichier texte séparé. L'extension de ces
							fichiers est ".srt".</P>
						<article class="articleIndex">
							<div class="col-md-12 col-lg-12" border="1">
								<div class="col-md-5 col-lg-5">
									<img id="imgIndex"
											class="img-rounded image-responsive" href="upload"
											src="http://www.homecine-compare.com/images/lexicon/159/lex-hc-srt.png"
											alt="Illustration du contenu d'un fichier SRT" border="0"
											usemap="#panneaux" title="Contenu d'un fichier SRT" />
								</div>
								<div class="col-md-7 col-lg-7 " >
									<form method="get" action="upload"
											enctype="multipart/form-data">
											<div class="text-center vertical-center" >
												<button type="submit" class="btn btn-lg btn-primary">Nouvelle
													traduction</button>
											</div>
										</form>
								</div>
							</div>
							
							
						</article>
						<br />
						<P class="padding">Les fichiers SRT sont notamment utilisés
							pour stocker les sous-titres présents sur les DVD. </P>
					</fieldset>
				</div>
				<div class="col-md-2 col-lg-2">&nbsp;</div>
			</div>
		</div>
	</section>

</body>
</html>
