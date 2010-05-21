/**
 * 
 */
package com.google.code.stackoverflow.client.query.impl;

import org.json.simple.JSONObject;

import com.google.code.stackoverflow.client.common.PagedArrayList;
import com.google.code.stackoverflow.client.common.PagedList;
import com.google.code.stackoverflow.client.constant.StackOverflowApiMethods;
import com.google.code.stackoverflow.client.query.StatisticsApiQuery;
import com.google.code.stackoverflow.schema.Statistics;
import com.google.code.stackoverflow.schema.adapter.json.StatisticsImpl;

/**
 * The Class StatisticsApiQueryImpl.
 */
public class StatisticsApiQueryImpl extends BaseStackOverflowApiQuery<Statistics> implements StatisticsApiQuery {

	/**
	 * Instantiates a new statistics api query impl.
	 * 
	 * @param applicationId the application id
	 */
	public StatisticsApiQueryImpl(String applicationId) {
		super(applicationId);
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.impl.BaseStackOverflowApiQuery#unmarshall(org.json.simple.JSONObject)
	 */
	@Override
	protected PagedList<Statistics> unmarshall(JSONObject json) {
		Statistics adapter = new StatisticsImpl();
		((StatisticsImpl) adapter).adaptFrom(json);
		PagedList<Statistics> list = new PagedArrayList<Statistics>();
		list.add(adapter);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackoverflow.client.query.StackOverflowApiQuery#reset()
	 */
	@Override
	public void reset() {
		apiUrlBuilder = getApiProvider().createApiUrlBuilder(StackOverflowApiMethods.GET_STATISTICS, getApplicationKey(), getApiVersion());
	}
}
