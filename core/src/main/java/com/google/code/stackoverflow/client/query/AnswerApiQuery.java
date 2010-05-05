/**
 * 
 */
package com.google.code.stackoverflow.client.query;

import java.util.Set;

import com.google.code.stackoverflow.schema.Answer;
import com.google.code.stackoverflow.schema.FilterOption;

/**
 * The Interface AnswerApiQuery.
 */
public interface AnswerApiQuery extends StackOverflowApiQuery<Answer> {
	
	/**
	 * The Enum Classification.
	 */
	public enum Classification {
		
		/** The NONE. */
		NONE, 
 /** The B y_ user. */
 BY_USER, 
 /** The B y_ question. */
 BY_QUESTION;
	}
	
	/**
	 * With answer ids.
	 * 
	 * @param answerIds the answer ids
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withAnswerIds(long... answerIds);
	
	/**
	 * With user ids.
	 * 
	 * @param userIds the user ids
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withUserIds(long... userIds);
	
	/**
	 * With question ids.
	 * 
	 * @param questionIds the question ids
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withQuestionIds(long... questionIds);
	
	/**
	 * With sort.
	 * 
	 * @param sort the sort
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withSort(Answer.SortOrder sort);
	
	/**
	 * With fetch options.
	 * 
	 * @param fetchOptions the fetch options
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withFetchOptions(Set<FilterOption> fetchOptions);
	
	/**
	 * With classification.
	 * 
	 * @param classification the classification
	 * 
	 * @return the answer api query
	 */
	public AnswerApiQuery withClassification(Classification classification);
}
