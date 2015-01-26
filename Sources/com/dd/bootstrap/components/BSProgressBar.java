package com.dd.bootstrap.components;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.webobjects.appserver.WOContext;

public class BSProgressBar extends BSComponent {
    public BSProgressBar(WOContext context) {
        super(context);
    }

	public String styleString() {
		return "width: "+valueForBinding("progress")+"%";
	}

	public String classString() {
		StringBuilder sb = new StringBuilder();
		sb.append("progress-bar");
		if(StringUtils.isNotBlank(ObjectUtils.toString(valueForBinding("type")))){
			sb.append(" progress-bar-"+valueForBinding("type"));
		}
		if(Boolean.TRUE.equals(valueForBinding("striped"))){
			sb.append(" progress-bar-striped");
		}
		return sb.toString();
	}
}