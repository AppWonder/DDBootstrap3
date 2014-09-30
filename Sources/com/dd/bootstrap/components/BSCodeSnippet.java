package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import er.extensions.appserver.ERXResponseRewriter;

public class BSCodeSnippet extends BSComponent {
    
	public BSCodeSnippet(WOContext context) {
        super(context);
    }
	
	@Override
	protected void injectCustomHeadData(WOResponse response, WOContext context) {
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "prettify/prettify.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "prettify/run_prettify.js");
	}
	
	public String cssClass() {
		StringBuilder sb = new StringBuilder();
		sb.append("prettyprint");
		
		if (booleanValueForBinding("showLineNumbers", false)) {
			sb.append(" linenums");
		}
		
		return sb.toString();
	}
}