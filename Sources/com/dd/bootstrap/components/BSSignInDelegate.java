package com.dd.bootstrap.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;

import er.extensions.appserver.ERXResponse;

public class BSSignInDelegate {
	private String username;
	private String errorMessage;
	
	public String usernameLabel(){
		return "Username";
	}
	
	public String passwordLabel(){
		return "Password";
	}
	
	public String errorMessage(){
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public boolean showRememberMe(){
		return true;
	}
	
	
	public WOActionResults signInAction(WOContext context, String username, String password, boolean rememberMe){
		this.username = username;
		return new ERXResponse("You signed in with "+username+"#"+password+" ("+rememberMe+")");
	}
	
	public String loginUrl(){
		return null;
	}
	
	public String directActionName(){
		return null;
	}
	
	public String actionClass(){
		return null;
	}
	
	public String presetUsername(){
		return username;
	}
	
	public String presetPassword(){
		return null;
	}
	
	public String pageTitle(){
		return "Sign In";
	}
	
	public String headline(){
		return "Please sign in";
	}
	
	public String buttonText(){
		return "Sign in";
	}
	
}
