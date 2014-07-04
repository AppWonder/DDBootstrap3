package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSComponent;
import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.ERXResponseRewriter;
import er.extensions.components._private.ERXWOBrowser;
import er.extensions.foundation.ERXPatcher;

public class BSBrowser extends ERXWOBrowser {

	public BSBrowser(String s, NSDictionary nsdictionary, WOElement woelement) {
		super(s, nsdictionary, woelement);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
		
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "css/chosen.min.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/chosen.jquery.min.js");
		super.appendToResponse(response, context);
	}

}
