<div class="form-group">
	<form method="get" action="upload" enctype="multipart/form-data">
		<button type="submit" class="btn btn-lg btn-primary">Nouvelle traduction</button>
	</form>
	<br />
	<form method="get" action="retrieveSubtitleFile" enctype="multipart/form-data">
		<button type="submit" class="btn btn-lg btn-primary">Continuer traduction</button>
	</form>
	<br />
	<form method="get" action="retrieveSubtitleFile">
		<input type="hidden" name="finished" value="Yo" id="finished"/>
		<button type="submit" class="btn btn-lg btn-primary">Exporter traduction</button>
	</form>
</div>