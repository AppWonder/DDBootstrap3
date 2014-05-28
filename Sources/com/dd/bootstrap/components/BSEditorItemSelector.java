package com.dd.bootstrap.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSKeyValueCodingAdditions;

public class BSEditorItemSelector extends BSComponent {
    public BSEditorItemSelector(WOContext context) {
        super(context);
    }

	public WOActionResults selectCurrentItem() {
		setValueForBinding(valueForBinding("item"), "selection");
		return null;
	}

	public String displayString() {
		if(valueForBinding("item")==null){
			return "";
		}
		if(valueForBinding("item") instanceof NSKeyValueCodingAdditions){
			return ((NSKeyValueCodingAdditions)valueForBinding("item")).valueForKeyPath((String)valueForBinding("keyPath")).toString();
		}
		return valueForBinding("item").toString();	
	}
	
	public String displayStringForSelection(){
		if(valueForBinding("selection")==null){
			return null;
		}
		System.out.println(valueForBinding("selection"));
		if(valueForBinding("selection") instanceof NSKeyValueCodingAdditions){
			Object displayedValue = ((NSKeyValueCodingAdditions)valueForBinding("selection")).valueForKeyPath((String)valueForBinding("keyPath"));
			if(displayedValue!=null){
				return displayedValue.toString();
			}
			return null;
		}
		return valueForBinding("selection").toString();
	}
}