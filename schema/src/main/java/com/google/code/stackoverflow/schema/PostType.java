package com.google.code.stackoverflow.schema;

import java.util.HashMap;
import java.util.Map;


/**
 * The Enum PostType.
 */
public enum PostType implements ValueEnum {
	
	/** The ANSWER. */
	ANSWER("answer"),
	
	/** The COMMENT. */
	COMMENT("comment"),
	
	/** The QUESTION. */
	QUESTION("question");
	
    /** The Constant stringToEnum. */
	private static final Map<String, PostType> stringToEnum = new HashMap<String, PostType>();

	static { // Initialize map from constant name to enum constant
		for (PostType op : values()) {
			stringToEnum.put(op.value(), op);
		}
	}
	
    /** The value. */
    private final String value;
    
    /**
     * Instantiates a new post type.
     * 
     * @param value the value
     */
    PostType(String value) {
        this.value = value;
    }

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.schema.ValueEnum#value()
	 */
	@Override
	public String value() {
		return value;
	}
	
	/**
	 * From value.
	 * 
	 * @param value the value
	 * 
	 * @return the post type
	 */
	public static PostType fromValue(String value) {
		return stringToEnum.get(value);
	}
}
