$(document).ready(function () {
  $.getJSON('json/data.js')
    .done( function(response) {
    var candyList = '<ul class="candies">';
    $.each(response, function(index, candy) {
      if (candy.quantity > 0) {
        candyList += '<li class="item available">';
      } else {
        candyList += '<li class="item sold-out">'
      }
      candyList += candy.name + '<br>' + "Brand: " + candy.brand;
      candyList += '<button class="btn btn-default">Buy</button>';
      candyList += '</li>';
    });
    candyList += "</ul>";
    $('#candyListing').html(candyList);
  })
  
})