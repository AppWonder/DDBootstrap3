package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

/**
 * Wrapper around Labels http://getbootstrap.com/components/#labels
 * 
 * @binding type String for supported label types.
 * 
 * @author robin
 *
 */
public class BSLabel extends BSComponent {
    
	private static final long serialVersionUID = 1L;

	public BSLabel(WOContext context) {
        super(context);
    }
	
	public String type() {
		String type = stringValueForBinding("type", "label-default");
		if (!type.startsWith("label-")) {
			type = "label-" + type;
		}
		return type;
	}
	
	public String cssClass() {
		return "label " + type();
	}
}