package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXWOTextField;

public class BSEditorRowColorPicker extends ERXWOTextField {
	
	private WOAssociation _label;		// String
	private WOAssociation _required;	// boolean
	private WOAssociation _helpText;	// String
	private WOAssociation _myClass;

	@SuppressWarnings("rawtypes")
	public BSEditorRowColorPicker(String tagname, NSDictionary nsdictionary, WOElement woelement) {
		super(tagname, nsdictionary, woelement);
		
		_myClass = (WOAssociation) nsdictionary.remove("class");
		_class = null;
		
		//BSDynamicElementsHelper.AppendCSS(_associations, this);
		
		_label = _associations.removeObjectForKey("label");
		_required = _associations.removeObjectForKey("required");
		_helpText = _associations.removeObjectForKey("helpText");
	}
	
	@Override
	public void appendAttributesToResponse(WOResponse response, WOContext context) {
		super.appendAttributesToResponse(response, context);
		
		StringBuilder sb = new StringBuilder();
		sb.append("form-control color-picker");
		if (_myClass != null) {
			sb.append(" ").append((String) _myClass.valueInComponent(context.component()));
		}
		response._appendTagAttributeAndValue("class", sb.toString(), false);
	}
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		
		response.appendContentString("<div class=\"form-group\"><label class=\"col-sm-2 control-label\">");
		if (_label != null && _label.valueInComponent(context.component()) != null) {
			response.appendContentHTMLString((String) _label.valueInComponent(context.component()));
			if (_required != null && (Boolean) _required.valueInComponent(context.component())) {
				response.appendContentString("*");
			}
		}
		
		response.appendContentString("</label><div class=\"col-sm-10\">");
		
		response.appendContentString("<div class=\"input-group\">");
		
		super.appendToResponse(response, context);
		
		response.appendContentString("<span class=\"input-group-addon\"><span class=\"color-box\"></span></span></div>");
		
		if (_helpText != null && _helpText.valueInComponent(context.component()) != null) {
			response.appendContentString("<span class=\"help-block\">");
			response.appendContentString((String) _helpText.valueInComponent(context.component()));
			response.appendContentString("</span>");
		}
		response.appendContentString("</div></div>");
	}

}
