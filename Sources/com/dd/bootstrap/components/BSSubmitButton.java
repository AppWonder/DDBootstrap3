package com.dd.bootstrap.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOInput;
import com.webobjects.foundation.NSDictionary;

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
public class BSSubmitButton extends WOInput {

	private WOAssociation type;
	private WOAssociation dimension;
	private WOAssociation block;
	
	public BSSubmitButton(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super("button", someAssociations, template);
		
		// someAssociations are _associations
		
		type = _associations.remove("type");
		dimension = _associations.remove("dimension");
		block = _associations.remove("block");
		
		
	}

	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		
		if (response == null || context == null) return;
		
		String elementName = elementName();
		
		// opening tag
		response.appendContentCharacter('<');
		response._appendContentAsciiString(elementName);
		appendAttributesToResponse(response, context);
		response.appendContentCharacter('>');
		
		// content
		appendChildrenToResponse(response, context);
		
		// closing tag
		response._appendContentAsciiString("</");
		response._appendContentAsciiString(elementName);
		response.appendContentCharacter('>');
	}
	
	@Override
	public void appendChildrenToResponse(WOResponse response, WOContext context) {
		if(hasChildrenElements()) {
            super.appendChildrenToResponse(response, context);
        }
	}
	
	@Override
	public void appendAttributesToResponse(WOResponse response, WOContext context) {
		// needed or not?
		appendConstantAttributesToResponse(response, context);
    	appendNonURLAttributesToResponse(response, context);
    	appendURLAttributesToResponse(response, context);
    	
    	// class, is always set here
    	response._appendTagAttributeAndValue("class", cssClass(context), false);
    	
	}
	
	@Override
	public WOActionResults invokeAction(WORequest request, WOContext context) {
		return super.invokeAction(request, context);
	}
	
	@Override
	public void takeValuesFromRequest(WORequest r, WOContext c) {
		// ignore
	}
	
	private String cssClass(WOContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("btn");
		
		// type class
		if (type != null) {
			String t = (String) type.valueInComponent(context.component());
			if (!t.startsWith("btn-")) {
				t = "btn-" + t;
			}
			sb.append(" ").append(t);
		} else {
			sb.append(" btn-default");
		}
		
		
		// dimension class
		if (dimension != null) {
			String dim = (String) dimension.valueInComponent(context.component());
			if (!dim.startsWith("btn-")) {
				dim = "btn-" + dim;
			}
			sb.append(" ").append(dim);
		}
		
		// block class
		if (block != null && block.booleanValueInComponent(context.component())) {
			sb.append(" ").append("btn-block");
		}
		
		return sb.toString();
	}
	
	public WOActionResults testAction() {
		System.out.println("test action called!");
		return null;
	}
	
}
