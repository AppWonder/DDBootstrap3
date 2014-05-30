package com.dd.bootstrap.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;

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
		public Item() {}
		public Item(String title) { this.title = title; }
		public WOActionResults action() { return null; }
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
	
	
	/**
	 * Constructor
	 * @param context
	 */
	public BSNavigationBar(WOContext context) {
        super(context);
    }
	
	
	/**
	 * Returns "active" when the delegate says it's selected
	 * @return CSS class name
	 */
	public String cssClassLiItem() {
		Item item = (Item) valueForBinding("item");
		if (delegate() != null && delegate().navigationBarItemIsSelected(this, item)) {
			return "active";
		}
		return null;
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
			return item.action();
		}
		return null;
	}
	

	/**
	 * Action for defaultItem.
	 * @return item.action or null
	 */
	public WOActionResults selectDefaultItem() {
		if (canGetValueForBinding("defaultItem")) {
			Item item = (Item) valueForBinding("defaultItem");
			if (item != null) {
				return item.action();
			}
		}
		
		return null;
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
	
}