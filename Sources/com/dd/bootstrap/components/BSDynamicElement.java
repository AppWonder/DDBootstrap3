package com.dd.bootstrap.components;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.components.ERXDynamicElement;

public class BSDynamicElement extends ERXDynamicElement {

	public BSDynamicElement(String name, NSDictionary<String, WOAssociation> associations, NSMutableArray<WOElement> children) {
		super(name, associations, children);
	}

	public void appendToResponse(WOResponse response, WOContext context) {
		BSComponent.InjectCSSAndJS(response, context);
		super.appendToResponse(response, context);
	}
	
}
