package com.dd.bootstrap.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;

public class BSSignIn extends BSComponent {
	private BSSignInDelegate delegate;	
	
	public BSSignIn(WOContext context) {
        super(context);
    }
	
	
	public BSSignInDelegate delegate(){
		if(delegate==null){
			delegate = new BSSignInDelegate();
		}
		
		return delegate;
	}
	
	public String actionUrl(){
		if(delegate().loginUrl()!=null){
			return delegate().loginUrl();
		}
		if(delegate().directActionName()!=null){
			String actionClass = "";
			if(delegate().actionClass()!=null){
				actionClass = delegate().actionClass()+"/";
			}
			return context().directActionURLForActionNamed(actionClass+delegate().directActionName(), null);
		}
		return context().componentActionURL();
	}
	
	@Override
	public WOActionResults invokeAction(WORequest request, WOContext context) {		
		return delegate().signInAction(context, request.stringFormValueForKey("username"), request.stringFormValueForKey("password"), "remember-me".equals(request.formValueForKey("remember_me")) );
	}
}