/**
 * 
 */
package com.google.code.stackexchange.client.query.impl;


import org.json.simple.JSONObject;

import com.google.code.stackexchange.client.constant.StackExchangeApiMethods;
import com.google.code.stackexchange.client.provider.url.DefaultApiUrlBuilder;
import com.google.code.stackexchange.client.query.CommentApiQuery;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Comment;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Range;
import com.google.code.stackexchange.schema.TimePeriod;
import com.google.code.stackexchange.schema.adapter.json.CommentsImpl;

/**
 * The Class CommentApiQueryImpl.
 */
public class CommentApiQueryImpl extends BaseStackOverflowApiQuery<Comment> implements CommentApiQuery {

	/**
	 * Instantiates a new comment api query impl.
	 * 
	 * @param applicationId the application id
	 */
	public CommentApiQueryImpl(String applicationId) {
		super(applicationId);
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withCommentIds(long[])
	 */
	@Override
	public CommentApiQuery withCommentIds(long... commentIds) {
		apiUrlBuilder.withIds(commentIds);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withPaging(com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public CommentApiQuery withPaging(Paging paging) {
		apiUrlBuilder.withPaging(paging);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withSort(com.google.code.stackexchange.schema.Comment.SortOrder)
	 */
	@Override
	public CommentApiQuery withSort(Comment.SortOrder sort) {
		apiUrlBuilder.withSort(sort);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withTimePeriod(com.google.code.stackexchange.schema.TimePeriod)
	 */
	@Override
	public CommentApiQuery withTimePeriod(TimePeriod timePeriod) {
		apiUrlBuilder.withTimePeriod(timePeriod);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withToUserId(long)
	 */
	@Override
	public CommentApiQuery withToUserId(long toUserId) {
		apiUrlBuilder.withField("toid", String.valueOf(toUserId));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withUserIds(long[])
	 */
	@Override
	public CommentApiQuery withUserIds(long... userIds) {
		apiUrlBuilder.withIds(userIds);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.impl.BaseStackOverflowApiQuery#unmarshall(org.json.simple.JSONObject)
	 */
	@Override
	protected PagedList<Comment> unmarshall(JSONObject json) {
		CommentsImpl adapter = new CommentsImpl();
		adapter.adaptFrom(json);
		return adapter.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.StackOverflowApiQuery#reset()
	 */
	@Override
	public void reset() {
		apiUrlBuilder = getApiProvider().createApiUrlBuilder(StackExchangeApiMethods.GET_COMMENT, getApplicationKey(), getApiVersion());
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#withRange(com.google.code.stackexchange.schema.Range)
	 */
	@Override
	public CommentApiQuery withRange(Range range) {
		apiUrlBuilder.withRange(range);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#listUserComments()
	 */
	@Override
	public PagedList<Comment> listUserComments() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
		return super.list();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#listUserCommentsToUser()
	 */
	@Override
	public PagedList<Comment> listUserCommentsToUser() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
		return super.list();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.CommentApiQuery#listUserMentions()
	 */
	@Override
	public PagedList<Comment> listUserMentions() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackExchangeApiMethods.GET_USER_MENTIONS);
		return super.list();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.query.impl.BaseStackOverflowApiQuery#list()
	 */
	@Override
	public PagedList<Comment> list() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackExchangeApiMethods.GET_COMMENT);
		return super.list();
	}
}