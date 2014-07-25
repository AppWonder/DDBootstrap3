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
});