package com.dd.bootstrap.components;

import org.apache.commons.lang.StringUtils;

import com.dd.bootstrap.utils.Glyphicon;
import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSForwardException;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSSelector;

import er.extensions.foundation.ERXStringUtilities;

/**
 * BSNavigationBar
 * 
 * <b>Bindings:</b>
 * Property		Required		Type
 * title						String
 * list			yes 			NSArray<BSNavigationBar.Item>
 * item 		yes 			BSNavigationBar.Item
 * defaultItem 	yes	 			BSNavigationBar.Item
 * delegate 					BSNavigationBar.Delegate
 * 
 * @author robin
 *
 */
public class BSNavigationBar extends BSComponent {
	
	/**
	 * Class Item used in BSNavigationBar
	 * @author robin
	 *
	 */
	public static class Item {
		public String title;
		public Object tag;
		private Glyphicon glyphicon;
		public NSMutableArray<Item> childItems = new NSMutableArray<Item>();
		private Item() {
			
		}
		private Item(String title) { 
			this();
			this.title = title; 
			
		}
		public WOActionResults action(WOContext context) { if(selector!=null&&selectorTarget!=null){return actionBySelector(context);} return null; }
		public String href;
		public String hrefTarget;
		private NSSelector<? extends WOActionResults> selector;
		private Object selectorTarget;
		
		@SuppressWarnings("unchecked")
		public <T extends WOComponent> T pageWithName(Class<T> componentClass, WOContext context) {
			    return (T)WOApplication.application().pageWithName(componentClass.getName(), context);
		}
		
		public WOActionResults actionBySelector(WOContext context){
			try{
				if(selector.implementedByObject(selectorTarget)){
					return selector.invoke(selectorTarget);
				}
				NSSelector<WOActionResults> newSelector = new NSSelector<WOActionResults>(selector.name(),new Class[]{WOContext.class});
				if(newSelector.implementedByObject(selectorTarget)){
					return newSelector.invoke(selectorTarget, context); 
				}
				throw new NoSuchMethodException("Action of name "+selector.name()+" cannot be found in object of class "+selectorTarget.getClass().getName());
			}
			catch (Exception e) {
				throw new NSForwardException(e);
			}
		}
		
		public Glyphicon glyphicon(){
			return glyphicon;
		}
		
		public boolean hasChildItems() {
			return childItems != null && !childItems.isEmpty();
		}
		
		public boolean hasHref() {
			return StringUtils.isNotBlank(href);
		}
		
		public <T extends WOComponent> Item appendAsChild(String label, Class<T> page, Object tag, Glyphicon glyphicon){
			childItems.addObject(navigationItem(label, page, tag, glyphicon));
			return this;
		}
		
		public <T extends WOComponent> Item appendAsChild(String label, Class<T> page, Glyphicon glyphicon){
			childItems.addObject(navigationItem(label, page, page, glyphicon));
			return this;
		}
		
		public Item appendAsChild(String label, String href, String target, Object tag, Glyphicon glyphicon){
			childItems.addObject(navigationItem(label, href, target, tag, glyphicon));
			return this;
		}
		
		public Item appendAsChild(String label, Object tag, Glyphicon glyphicon){
			childItems.addObject(navigationItem(label, tag, glyphicon));
			return this;
		}
		
		public Item appendAsChild(String label, Object target, String actionName, Object tag, Glyphicon glyphicon){
			childItems.addObject(navigationItem(label, target, actionName, tag, glyphicon));
			return this;
		}
	}
	
	
	public static class Configuration{
		private Item defaultItem;
		private NSMutableArray<Item> items;
		public Configuration(Item defaultItem){
			this.defaultItem = defaultItem;
			items = new NSMutableArray<BSNavigationBar.Item>();
		}
		
		
		public Item defaultItem(){
			return defaultItem;
		}
		
		public void appendItem(Item anItem){
			items.addObject(anItem);
		}
		
		public NSArray<Item> items(){
			return items;
		}
		
		public <T extends WOComponent> Item appendItem(String label, Class<T> page, Object tag, Glyphicon glyphicon){
			Item item = navigationItem(label, page, tag, glyphicon);
			items.addObject(item);
			return item;
		}
		
		public <T extends WOComponent> Item appendItem(String label, Class<T> page, Glyphicon glyphicon){
			Item item = navigationItem(label, page, page, glyphicon);
			items.addObject(item);
			return item;
		}
		
		public Item appendItem(String label, String href, String target, Object tag, Glyphicon glyphicon){
			Item item = navigationItem(label, href, target, tag, glyphicon);
			items.addObject(item);
			return item;
		}
		
		public Item appendItem(String label, Object tag, Glyphicon glyphicon){
			Item item = navigationItem(label, tag, glyphicon);
			items.addObject(item);
			return item;
		}
		
		public Item appendItem(String label, Object target, String actionName, Object tag, Glyphicon glyphicon){
			Item item = navigationItem(label, target, actionName, tag, glyphicon);
			items.addObject(item);
			return item;
		}
		
		
		
	}
	
	public Configuration configuration(){
		return (Configuration)valueForBinding("configuration");
	}
	
	
	/**
	 * Interface Delegate which gets called on different actions
	 * @author robin
	 *
	 */
	public static interface Delegate {
		boolean navigationBarItemIsSelected(BSNavigationBar navigationBar, Item item);
		WOActionResults navigationBarItemOnSelect(BSNavigationBar navigationBar, Item item);
	}
	
	
	private String _navbarElementID;
	
	/**
	 * Constructor
	 * @param context
	 */
	public BSNavigationBar(WOContext context) {
        super(context);
    }
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		_navbarElementID = null;
	}
	
	/**
	 * Returns "active" when the delegate says it's selected
	 * @return CSS class name
	 */
	public String cssClassLiItem() {
		Item item = (Item) valueForBinding("item");
		NSMutableArray<String> entries = new NSMutableArray<String>();
		
		if (delegate() != null && delegate().navigationBarItemIsSelected(this, item)) {
			entries.addObject("active");
		}
		
		if (item.hasChildItems()) {
			entries.addObject("dropdown");
		}
		
		return entries.isEmpty() ? null : entries.componentsJoinedByString(" ");
	}
	

	/**
	 * Select the current item inside the repetition
	 * @return
	 */
	public WOActionResults selectItem() {
		Item item = (Item) valueForBinding("item");
		setValueForBinding(item, "selection");
		if (delegate() != null) {
			delegate().navigationBarItemOnSelect(this, item);
		}
		return item.action(context());
	}
	

	/**
	 * Action for defaultItem.
	 * @return item.action or null
	 */
	public WOActionResults selectDefaultItem() {
		if (configuration().defaultItem()!=null) {
			Item item = configuration().defaultItem();
			if (item != null) {
				return item.action(context());
			}
		}
		
		return null;
	}
	
	
	/**
	 * Returns the navigation-bar's DOM element ID for this component.
	 * @return DOM element id
	 */
	public String navbarElementID() {
		if (ERXStringUtilities.isBlank(_navbarElementID)) {
			// We have to replace all "." chars by "-" otherwise it won't work
			// as a DOM selector at all.
			_navbarElementID = "navbar-" + StringUtils.replace(context().elementID(), ".", "-");
		}
		return _navbarElementID;
	}
	
	
	/**
	 * Helper Method. Returns the delegate which was set via binding or null.
	 * @return Delegate
	 */
	private Delegate delegate() {
		Delegate delegate = null;
		
		if (canGetValueForBinding("delegate")) {
			delegate = (Delegate) valueForBinding("delegate");
		}
		
		return delegate;
	}
	
	public static <T extends WOComponent> Item navigationItem(String label, Class<T> page, Object tag, Glyphicon glyphicon){
		final Class<T> aPage = page;
		Item item = new Item(label){	 
			@Override
			public WOActionResults action(WOContext context) {
				return pageWithName(aPage, context);
			}
		};
		item.tag = tag;
		item.glyphicon = glyphicon;
		return item;
	}
	
	public static <T extends WOComponent> Item navigationItem(String label, String href, String target,  Object tag, Glyphicon glyphicon){
		Item item = new Item(label);
		item.href = href;
		item.hrefTarget = target;
		item.tag = tag;
		item.glyphicon = glyphicon;
		return item;
	}
	
	public static <T extends WOComponent> Item navigationItem(String label, Object target, String actionName, Object tag, Glyphicon glyphicon){
		Item item = new Item(label);
		item.selectorTarget = target;
		item.selector = new NSSelector<WOActionResults>(actionName);
		item.tag = tag;
		item.glyphicon = glyphicon;
		return item;
	}
	
	public static <T extends WOComponent> Item navigationItem(String label, Object tag, Glyphicon glyphicon){
		Item item = new Item(label);
		item.tag = tag;
		item.glyphicon = glyphicon;
		return item;
	}
	
	public static Configuration newConfiguration(Item item){
		return new Configuration(item);	
	}
	
}