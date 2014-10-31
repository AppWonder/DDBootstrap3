package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOText;
import com.webobjects.foundation.NSDictionary;

/**
 * BSEditorRowTextarea
 * 
 * Additional bindings:
 * 
 * @binding label
 * @binding required
 * @binding helpText
 * 
 * @author robin
 *
 */
public class BSEditorRowTextarea extends WOText {

	private WOAssociation _label;		// String
	private WOAssociation _required;	// boolean
	private WOAssociation _helpText;	// String
	private WOAssociation _myClass;
	
	public BSEditorRowTextarea(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		
		_myClass = (WOAssociation) someAssociations.remove("class");
		_class = null;
		
		BSDynamicElementsHelper.AppendCSS(_associations, this);
		
		_associations.setObjectForKey(WOAssociation.associationWithValue("3"), "rows");
		
		_label = _associations.removeObjectForKey("label");
		_required = _associations.removeObjectForKey("required");
		_helpText = _associations.removeObjectForKey("helpText");
	}
	
	@Override
	public void appendAttributesToResponse(WOResponse response, WOContext context) {
		super.appendAttributesToResponse(response, context);
		
		StringBuilder sb = new StringBuilder();
		sb.append("form-control");
		if (_myClass != null) {
			sb.append(" ").append((String) _myClass.valueInComponent(context.component()));
		}
		response._appendTagAttributeAndValue("class", sb.toString(), false);
		System.out.println(sb.toString());
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
		super.appendToResponse(response, context);
		if (_helpText != null && _helpText.valueInComponent(context.component()) != null) {
			response.appendContentString("<span class=\"help-block\">");
			response.appendContentString((String) _helpText.valueInComponent(context.component()));
			response.appendContentString("</span>");
		}
		response.appendContentString("</div></div>");
	}
	
}
