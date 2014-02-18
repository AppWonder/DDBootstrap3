package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

/**
 * Wrapper around Bootstrap's Badge component
 * http://getbootstrap.com/components/#badges
 * 
 * @binding count int required
 * 
 * @author robin
 *
 */
public class BSBadge extends BSComponent {
    
	private static final long serialVersionUID = 1L;

	public BSBadge(WOContext context) {
        super(context);
    }
	
	public int count() {
		return intValueForBinding("count", 0);
	}
	
	public String cssClass() {
		return "badge";
	}
}