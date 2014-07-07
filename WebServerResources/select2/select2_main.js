jQuery(document).ready(function() {
	jQuery('select').each(function() {
		if (this.options.length > 5) {
			jQuery(this).select2({
				width: '100%'
			});
		}
	});
});