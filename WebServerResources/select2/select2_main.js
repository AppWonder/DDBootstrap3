jQuery(document).ready(function() {
	jQuery('select').each(function() {
		if (this.options.length > 5 || this.getAttribute('multiple') != null) {
			jQuery(this).select2({
				width: '100%'
			});
		}
	});
});