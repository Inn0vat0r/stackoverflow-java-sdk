/**
 * 
 */
package com.google.code.stackoverflow.client.query.impl;

import java.util.Set;

import org.json.simple.JSONObject;

import com.google.code.stackoverflow.client.constant.StackOverflowApiMethods;
import com.google.code.stackoverflow.client.provider.url.DefaultApiUrlBuilder;
import com.google.code.stackoverflow.client.query.AnswerApiQuery;
import com.google.code.stackoverflow.common.PagedList;
import com.google.code.stackoverflow.schema.Answer;
import com.google.code.stackoverflow.schema.FilterOption;
import com.google.code.stackoverflow.schema.Range;
import com.google.code.stackoverflow.schema.adapter.json.AnswersImpl;

/**
 * The Class AnswerApiQueryImpl.
 */
public class AnswerApiQueryImpl extends BaseStackOverflowApiQuery<Answer> implements AnswerApiQuery {

	/**
	 * Instantiates a new answer api query impl.
	 * 
	 * @param applicationId the application id
	 */
	public AnswerApiQueryImpl(String applicationId) {
		super(applicationId);
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.AnswerApiQuery#withAnswerIds(long[])
	 */
	@Override
	public AnswerApiQuery withAnswerIds(long... answerIds) {
		apiUrlBuilder.withIds(answerIds);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.AnswerApiQuery#withFetchOptions(java.util.Set)
	 */
	@Override
	public AnswerApiQuery withFetchOptions(Set<FilterOption> fetchOptions) {
		apiUrlBuilder.withFetchOptions(fetchOptions);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.AnswerApiQuery#withSort(com.google.code.stackoverflow.schema.Answer.SortOrder)
	 */
	@Override
	public AnswerApiQuery withSort(Answer.SortOrder sort) {
		apiUrlBuilder.withSort(sort);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.AnswerApiQuery#withUserIds(long[])
	 */
	@Override
	public AnswerApiQuery withUserIds(long... userIds) {
		apiUrlBuilder.withIds(userIds);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.AnswerApiQuery#withQuestionIds(long[])
	 */
	@Override
	public AnswerApiQuery withQuestionIds(long... questionIds) {
		apiUrlBuilder.withIds(questionIds);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.impl.BaseStackOverflowApiQuery#unmarshall(org.json.simple.JSONObject)
	 */
	@Override
	protected PagedList<Answer> unmarshall(JSONObject json) {
		AnswersImpl adapter = new AnswersImpl();
		adapter.adaptFrom(json);
		return adapter.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.StackOverflowApiQuery#reset()
	 */
	@Override
	public void reset() {
		apiUrlBuilder = getApiProvider().createApiUrlBuilder(StackOverflowApiMethods.GET_ANSWER, getApplicationKey(), getApiVersion());
	}

	@Override
	public AnswerApiQuery withRange(Range range) {
		apiUrlBuilder.withRange(range);
		return this;
	}

	@Override
	public PagedList<Answer> listByQuestions() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackOverflowApiMethods.GET_ANSWERS_BY_QUESTION);
		return super.list();
	}

	@Override
	public PagedList<Answer> listByUsers() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackOverflowApiMethods.GET_ANSWERS_BY_USER);
		return super.list();
	}
	
	@Override
	public PagedList<Answer> list() {
		((DefaultApiUrlBuilder) apiUrlBuilder).withMethod(StackOverflowApiMethods.GET_ANSWER);
		return super.list();
	}
}
