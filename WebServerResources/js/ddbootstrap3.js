var DDBoostrap3 = {};
(function() {
	
	DDBoostrap3.OpenCollapsible = function(collapsibleId) {
		$('#' + collapsibleId).addClass('in');
	};
	
	DDBoostrap3.CloseCollapsible = function(collapsibleId) {
		$('#' + collapsibleId).removeClass('in');
	};
	
	DDBoostrap3.ToggleCollapsible = function(collapsibleId) {
		$('#' + collapsibleId).toggleClass('in');
	};
	
})();

$(function() {
	if (document.location.hash) {
		var $el = $('a[name='+document.location.hash.substr(1)+']');
		if ($el.length) {
			$(document.location.hash).addClass('in');
			$(window).scrollTop($el.position().top);
		}
	}
	
	$('input.color-picker').colorpicker().on('changeColor', function() {
		var ig = $(this).next('.input-group-addon');
		var cb = $('span.color-box', ig);
		cb.css('background-color', this.value);
	});
	
	$('input.color-picker').each(function() {
		var ig = $(this).next('.input-group-addon');
		var cb = $('span.color-box', ig);
		cb.css('background-color', this.value);
	});
	
	var checkboxes = Array.prototype.slice.call(document.querySelectorAll('input[type="checkbox"]'));

	checkboxes.forEach(function(ele) {
  		var switchery = new Switchery(ele);
	});
});