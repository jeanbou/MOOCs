var counter = 0;
var greetingsArray = ["Happy Birthday!","Crazy birthday!","Very fancy birthday!"];

$( function() {
	var $list, $newItemForm;
	$list = $('ul');
	$newItemForm = $('#list_section');

	$newItemForm.on('click', function(e) {
		e.preventDefault();		
		
		$list.append('<li class=\"item'+counter+'\">' + greetingsArray[counter] + '</li>');
		
		if (counter == 2) {
			counter = 0;
		}
		else {
			counter = counter + 1;
		}
		$('input:text').val('');
	});

	//$list.on('click', 'li', function() {
	//	var $this = $(this);
	//	$this.remove();
	//});

});