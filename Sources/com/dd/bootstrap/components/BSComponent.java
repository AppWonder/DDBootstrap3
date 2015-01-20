package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import er.extensions.appserver.ERXResponseRewriter;
import er.extensions.components.ERXComponent;

/**
 * Basic state-less component. Injects the needed JS / CSS files for all components.
 * All Bootstrap components should derive from this class, otherwise you need to
 * inject Bootstrap files on your own. 
 * 
 * @author robin
 *
 */
public class BSComponent extends ERXComponent {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FRAMEWORK_NAME = "DDBootstrap3";
	
	public static void InjectCSSAndJS(WOResponse response, WOContext context) {
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/bootstrap.min.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/bootstrap.custom.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/font-awesome.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/docs.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/bootstrap-social.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "select2/select2.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "select2/select2-bootstrap.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/btn-file.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/switchery.min.css");
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, FRAMEWORK_NAME, "css/bootstrap-colorpicker.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/jquery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/bootstrap.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "select2/select2.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "select2/select2_main.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/docs.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/switchery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/bootstrap-colorpicker.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, FRAMEWORK_NAME, "js/ddbootstrap3.js");
	}
	
	@Override
	public boolean synchronizesVariablesWithBindings() {
		return false;
	}

	public BSComponent(WOContext context) {
		super(context);
	}
	
	protected void injectCustomHeadData(WOResponse response, WOContext context) {
		
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		BSComponent.InjectCSSAndJS(response, context);
		injectCustomHeadData(response, context);
	}

}
