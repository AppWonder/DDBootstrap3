package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSComponent;
import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOCheckBox;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXResponseRewriter;

public class BSCheckBox extends WOCheckBox {
	
	private WOAssociation label;
	
	public BSCheckBox(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		label = _associations.removeObjectForKey("label");
		
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		ERXResponseRewriter.addStylesheetResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "css/bootstrap.min.css");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/jquery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/bootstrap.min.js");
		response.appendContentString("<div class=\"checkbox\"><label>");
		super.appendToResponse(response, context);
		if(label!=null&&label.valueInComponent(context.component())!=null){
			response.appendContentHTMLString((String)label.valueInComponent(context.component()));
		}
		response.appendContentString("</label></div>");
	}
	
	

}
