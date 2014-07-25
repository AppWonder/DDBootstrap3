package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOPopUpButton;
import com.webobjects.foundation.NSDictionary;

public class BSEditorRowPopUpButton extends WOPopUpButton {

	private WOAssociation _label;
	private WOAssociation _required;
	
	public BSEditorRowPopUpButton(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		
		BSDynamicElementsHelper.AppendCSS(_associations, this);
		
		_label = _associations.removeObjectForKey("label");
		_required = _associations.removeObjectForKey("required");
	}

	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		boolean hasChildElements = (childrenElements() != null && childrenElements().count() != 0);
		
		response.appendContentString("<div class=\"form-group\"><label class=\"col-sm-2 control-label\">");
		if (_label != null && _label.valueInComponent(context.component()) != null) {
			response.appendContentHTMLString((String) _label.valueInComponent(context.component()));
			if (_required != null && (Boolean) _required.valueInComponent(context.component())) {
				response.appendContentString("*");
			}
		}
		
		response.appendContentString("</label><div class=\"col-sm-10\">");
		
		if (hasChildElements) {
			response.appendContentString("<div class=\"input-group\">");
		}
		
		super.appendToResponse(response, context);
		
		// component content, i guess
		if (hasChildElements) {
			response.appendContentString("<span class=\"input-group-addon\">");
			context.appendZeroElementIDComponent();
			for (WOElement element : childrenElements()) {
				element.appendToResponse(response, context);
				context.incrementLastElementIDComponent();
			}
			context.deleteLastElementIDComponent();
			response.appendContentString("</span>");
		}
		
		if (hasChildElements) {
			response.appendContentString("</div>");
		}
		
		response.appendContentString("</div></div>");
	}
	
	
}
