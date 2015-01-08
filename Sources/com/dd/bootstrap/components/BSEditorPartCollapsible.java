package com.dd.bootstrap.components;

import org.apache.commons.lang.StringUtils;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

public class BSEditorPartCollapsible extends BSComponent {
    
	public BSEditorPartCollapsible(WOContext context) {
        super(context);
    }
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
	}
	
	public String collapsibleId() {
		return valueForStringBinding("collapsibleId", "collapsibleId_NULL_");
	}

	public String iconCode() {
		String iconName = stringValueForBinding("icon", null);
		if (StringUtils.isNotEmpty(iconName)) {
			StringBuilder sb = new StringBuilder();
			sb.append("<i class=\"").append(iconName).append("\"></i>");
			return sb.toString();
		}
		return null;
	}
	
}