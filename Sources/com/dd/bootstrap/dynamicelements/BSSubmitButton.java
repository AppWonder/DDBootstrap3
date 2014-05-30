package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXSubmitButton;

public class BSSubmitButton extends ERXSubmitButton {

	public BSSubmitButton(String name, NSDictionary<String, WOAssociation> nsdictionary, WOElement element) {
		super(name, nsdictionary, element);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		super.appendToResponse(response, context);
	}

}
