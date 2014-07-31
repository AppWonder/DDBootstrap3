package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOPopUpButton;
import com.webobjects.foundation.NSDictionary;

public class BSPopUpButton extends WOPopUpButton {

	public BSPopUpButton(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		super.appendToResponse(response, context);
	}
}
