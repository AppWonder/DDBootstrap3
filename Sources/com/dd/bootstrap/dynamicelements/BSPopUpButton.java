package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSComponent;
import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOPopUpButton;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXResponseRewriter;

public class BSPopUpButton extends WOPopUpButton {

	public BSPopUpButton(String aName,
			NSDictionary<String, WOAssociation> someAssociations,
			WOElement template) {
		super(aName, someAssociations, template);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		//ERXResponseRewriter.addStylesheetResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "css/chosen.min.css");
		//ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/chosen.jquery.min.js");
		super.appendToResponse(response, context);
	}
}
