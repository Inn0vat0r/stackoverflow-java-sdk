/**
 * 
 */
package com.google.code.stackexchange.schema.adapter.json;

import org.json.simple.JSONObject;

import com.google.code.stackexchange.schema.Tag;
import com.google.code.stackexchange.schema.adapter.Adaptable;

/**
 * The Class TagImpl.
 */
public class TagImpl extends BaseJsonAdapter implements Tag, Adaptable<Tag, JSONObject> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4228675989610922635L;
	
	/** The name. */
	private String name;
	
	/** The count. */
	private long count;
	
	/** The user id. */
	private long userId;
	
	private boolean fulfillsRequired;
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#getUserId()
	 */
	@Override
	public long getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#setUserId(long)
	 */
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#getCount()
	 */
	public long getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.Tag#setCount(long)
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the fulfillsRequired
	 */
	public boolean isFulfillsRequired() {
		return fulfillsRequired;
	}

	/**
	 * @param fulfillsRequired the fulfillsRequired to set
	 */
	public void setFulfillsRequired(boolean fulfillsRequired) {
		this.fulfillsRequired = fulfillsRequired;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.adapter.Adaptable#adaptFrom(java.lang.Object)
	 */
	@Override
	public void adaptFrom(JSONObject adaptee) {
		copyProperties(this, adaptee);
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.schema.adapter.Adaptable#adaptTo()
	 */
	@Override
	public JSONObject adaptTo() {
		JSONObject adapter = new JSONObject();
		copyProperties(adapter, this);
		return adapter;
	}
}
