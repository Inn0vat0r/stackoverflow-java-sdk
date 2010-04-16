package com.google.code.stackoverflow.schema;

import java.util.Date;


public interface Timeline extends SchemaEntity {

	/**
	 * @return the timeline_type
	 */
	public TimelineType getTimelineType();

	/**
	 * @param timeline_type the timeline_type to set
	 */
	public void setTimelineType(TimelineType timelineType);

	/**
	 * @return the post_id
	 */
	public long getPostId();

	/**
	 * @param post_id the post_id to set
	 */
	public void setPostId(long postId);

	/**
	 * @return the action
	 */
	public String getAction();

	/**
	 * @param action the action to set
	 */
	public void setAction(String action);

	/**
	 * @return the creation_date
	 */
	public Date getCreationDate();

	/**
	 * @param creation_date the creation_date to set
	 */
	public void setCreationDate(Date creationDate);

	/**
	 * @return the description
	 */
	public String getDescription();

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description);

	/**
	 * @return the detail
	 */
	public String getDetail();

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail);

	/**
	 * @return the userId
	 */
	public long getUserId();

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId);

	/**
	 * @return the ownerUserId
	 */
	public long getOwnerUserId();

	/**
	 * @param ownerUserId the ownerUserId to set
	 */
	public void setOwnerUserId(long ownerUserId);
	
	/**
	 * @return the revisionGuid
	 */
	public String getRevisionGuid();
	
	/**
	 * @param revisionGuid the revisionGuid to set
	 */
	public void setRevisionGuid(String revisionGuid);
	
	/**
	 * @return the commentId
	 */
	public long getCommentId();
	
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(long commentId);

	/**
	 * @return the postType
	 */
	public PostType getPostType();
	
	/**
	 * @param postType the postType to set
	 */
	public void setPostType(PostType postType);
}