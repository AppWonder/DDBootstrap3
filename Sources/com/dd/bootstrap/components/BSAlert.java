package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

/**
 * Wrapper class for Bootstrap's Alert component 
 * http://getbootstrap.com/components/#alerts
 * 
 * @binding type String optional. Values: success, info, warning (default), danger
 * @binding dismissable boolean optional
 * 
 * @author robin
 *
 */
public class BSAlert extends BSComponent {
    
	private static final long serialVersionUID = 1L;

	public BSAlert(WOContext context) {
        super(context);
    }
	
	public String type() {
		String type = stringValueForBinding("type", "alert-warning");
		if (!type.startsWith("alert-")) {
			type = "alert-" + type;
		}
		return type;
	}
	
	public boolean dismissable() {
		return booleanValueForBinding("dismissable", false);
	}
	
	public String cssClass() {
		return "alert " + type();
	}
}