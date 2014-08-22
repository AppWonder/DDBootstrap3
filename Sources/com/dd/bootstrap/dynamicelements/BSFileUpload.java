package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.appserver._private.WOFileUpload;
import com.webobjects.foundation.NSDictionary;

public class BSFileUpload extends WOFileUpload {
	
	WOAssociation _buttonValue;

	public BSFileUpload(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
		
		_buttonValue = _associations.removeObjectForKey("buttonValue");
	}
	
	@Override
	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		BSDynamicElement.InjectCSSAndJS(aResponse, aContext);
		String buttonValue = stringValueInContext(_buttonValue, aContext);
		
		aResponse.appendContentString("<span class=\"btn btn-default btn-file\"> ");
		if (buttonValue != null && buttonValue.length() != 0) {
			aResponse.appendContentHTMLString(buttonValue);
		} else {
			aResponse.appendContentString("Browse ...");
		}
		super.appendToResponse(aResponse, aContext);
		aResponse.appendContentString("</span>");
	}
	
	
	
}
