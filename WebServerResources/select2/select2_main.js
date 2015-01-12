jQuery(document).ready(function() {

	function format(state) {
		if (state && state.element && state.element.length) {
			var select = state.element[0].parentNode;
			var formatType = select.getAttribute('data-format-type');
			if (formatType == 'image') {
				console.log(state);
				var formatVar = select.getAttribute('data-format-var');
				var urls = window[formatVar];
				return formatImage(state, urls);
			}
		}
		return state.text;
	}
	
	function formatImage(state, urls) {
		if (state.id == 'WONoSelectionString') {
			return state.text;
		}
		var index = parseInt(state.id);
		return '<img src="' + urls[index] + '" width="50" /> ' + state.text;
	}

	jQuery('select').each(function() {
		if (this.options.length > 5 || this.getAttribute('multiple') != null) {
			jQuery(this).select2({
				width: '100%',
				formatResult: format,
				formatSelection: format,
				escapeMarkup: function(m) { return m; }
			});
		}
	});
});