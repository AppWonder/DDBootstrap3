package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOCheckBox;
import com.webobjects.foundation.NSDictionary;

public class BSEditorRowCheckBox extends WOCheckBox {

	WOAssociation _label;
	
	public BSEditorRowCheckBox(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		
		_label = _associations.removeObjectForKey("label");
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		response.appendContentString("<div class=\"form-group\"><label class=\"col-sm-2 control-label\">");
		if (_label != null && _label.valueInComponent(context.component()) != null) {
			response.appendContentHTMLString((String) _label.valueInComponent(context.component()));
		}
		response.appendContentString("</label><div class=\"col-sm-10\"><div class=\"checkbox\"><label>");
		super.appendToResponse(response, context);
		response.appendContentString("</label></div></div></div>");
	}

}
