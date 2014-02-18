package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import er.extensions.appserver.ERXResponseRewriter;
import er.extensions.components.ERXStatelessComponent;

/**
 * Basic state-less component. Injects the needed JS / CSS files for all components.
 * All Bootstrap components should derive from this class, otherwise you need to
 * inject Bootstrap files on your own. 
 * 
 * @author robin
 *
 */
public class BSComponent extends ERXStatelessComponent {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FRAMEWORK_NAME = "DDBootstrap3";

	public BSComponent(WOContext context) {
		super(context);
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/bootstrap.min.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/jquery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/bootstrap.min.js");
		
		super.appendToResponse(response, context);
	}

}
