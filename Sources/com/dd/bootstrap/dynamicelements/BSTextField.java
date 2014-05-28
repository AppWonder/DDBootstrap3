package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSComponent;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODynamicElement;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.appserver.ERXResponseRewriter;
import er.extensions.components._private.ERXWOTextField;

public class BSTextField extends ERXWOTextField {

	public BSTextField(String tagname, NSDictionary nsdictionary,
			WOElement woelement) {
		super(tagname, nsdictionary, woelement);
		appendCSS(_associations, this);
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "css/bootstrap.min.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/jquery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/bootstrap.min.js");
		
		super.appendToResponse(response, context);
	}
	
	public static void appendCSS(NSMutableDictionary<String, WOAssociation> associations, WODynamicElement element){
		if(element instanceof BSTextField){
			associations.setObjectForKey(WOAssociation.associationWithValue("form-control"), "class");
		}
	}

}
