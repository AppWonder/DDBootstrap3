package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.utils.ClientValidationSupport;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXWOForm;

public class BSEditorForm extends ERXWOForm {
	
	public BSEditorForm(String name, NSDictionary<String, WOAssociation> associations, WOElement element) {
		super(name, associations, element);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}
	
	
	public boolean hasValidation(WOContext aContext){
		for(WOElement element : childrenElements()){
			if(element instanceof BSEditorRowTextField && ((BSEditorRowTextField)element).requiresValidation(aContext)){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void appendAttributesToResponse(WOResponse response,
			WOContext context) {
		super.appendAttributesToResponse(response, context);
		if(hasValidation(context)){
			ClientValidationSupport.appendJavaScript(response, context);
			response.appendContentString(" "+ClientValidationSupport.PARSLEY_NAME_SPACE+"validate=\"true\"");
		}
	}

}
