/**
 * 
 */
package com.google.code.stackoverflow.client.query;

import com.google.code.stackoverflow.schema.Paging;
import com.google.code.stackoverflow.schema.Tag;

/**
 * @author nmukhtar
 *
 */
public interface TagApiQuery extends StackOverflowApiQuery<Tag> {
	
	public enum Classification {
		NONE, BY_USER;
	}
	
	/**
	 * 
	 */
	public TagApiQuery withUserIds(long... userIds);
	
	/**
	 * 
	 */
	public TagApiQuery withPaging(Paging paging);

	/**
	 * 
	 */
	public TagApiQuery withSort(Tag.SortOrder sort);
	
	/**
	 * 
	 */
	public TagApiQuery withClassification(Classification classification);
}
