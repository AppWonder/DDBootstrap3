package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

/**
 * Wrapper around Bootstrap's Button component
 * http://getbootstrap.com/css/#buttons
 * 
 * @binding type String optional. 
 * Changes the size of this element.
 * Possible values: default, primary, success, info, warning, danger,  link
 * 
 * @binding dimension String optional.
 * Changes the size of this element.
 * Possible values: lg (large), sm (small), xs (extra small)
 * 
 * @binding block boolean optional, default is false
 * Displays this element as a block level element which spans the full width of the parent
 * 
 * @author robin
 *
 */
public class BSButton extends BSComponent {

	private static final long serialVersionUID = 1L;

	public BSButton(WOContext context) {
        super(context);
    }
	
	public String type() {
		String type = stringValueForBinding("type", "btn-default");
		if (!type.startsWith("btn-")) {
			type = "btn-" + type;
		}
		return type;
	}
	
	public String dimension() {
		String dim = stringValueForBinding("dimension");
		if (dim != null && !dim.startsWith("btn-")) {
			dim = "btn-" + dim;
		}
		return dim;
	}
	
	public boolean block() {
		return booleanValueForBinding("block", false);
	}
	
	public String cssClass() {
		String type = type();
		String dim = dimension();
		boolean block = block();
		
		StringBuilder sb = new StringBuilder();
		sb.append("btn");
		
		// type class
		sb.append(" ").append(type);
		
		// dimension class
		if (dim != null) {
			sb.append(" ").append(dim);
		}
		
		// block class
		if (block) {
			sb.append(" ").append("btn-block");
		}
		
		return sb.toString();
	}
}