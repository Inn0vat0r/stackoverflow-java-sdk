/**
 *
 */
package com.google.code.stackexchange.client.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.code.stackexchange.client.StackExchangeApiClient;
import com.google.code.stackexchange.client.constant.StackExchangeApiMethods;
import com.google.code.stackexchange.client.provider.url.ApiUrlBuilder;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Answer;
import com.google.code.stackexchange.schema.Answers;
import com.google.code.stackexchange.schema.Badge;
import com.google.code.stackexchange.schema.Badges;
import com.google.code.stackexchange.schema.Comment;
import com.google.code.stackexchange.schema.Comments;
import com.google.code.stackexchange.schema.FilterOption;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.PostTimeline;
import com.google.code.stackexchange.schema.PostTimelines;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.Questions;
import com.google.code.stackexchange.schema.Range;
import com.google.code.stackexchange.schema.Reputation;
import com.google.code.stackexchange.schema.Reputations;
import com.google.code.stackexchange.schema.Revision;
import com.google.code.stackexchange.schema.Revisions;
import com.google.code.stackexchange.schema.Statistics;
import com.google.code.stackexchange.schema.Tag;
import com.google.code.stackexchange.schema.Tags;
import com.google.code.stackexchange.schema.TimePeriod;
import com.google.code.stackexchange.schema.User;
import com.google.code.stackexchange.schema.UserTimeline;
import com.google.code.stackexchange.schema.UserTimelines;
import com.google.code.stackexchange.schema.Users;
import com.google.code.stackexchange.schema.User.QuestionSortOrder;

/**
 * The Class BaseStackExchangeApiClient.
 */
public abstract class BaseStackExchangeApiClient extends StackExchangeApiGateway implements StackExchangeApiClient {

    /**
     * Instantiates a new base stack exchange api client.
     * 
     * @param applicationKey the application key
     */
    protected BaseStackExchangeApiClient(String applicationKey) {
        requestHeaders = new HashMap<String, String>();

        // by default we compress contents
        requestHeaders.put("Accept-Encoding", "gzip, deflate");
        this.applicationKey = applicationKey;
    }
    
    /**
     * Instantiates a new base stack exchange api client.
     * 
     * @param applicationKey the application key
     * @param apiVersion the api version
     */
    protected BaseStackExchangeApiClient(String applicationKey, String apiVersion) {
    	this(applicationKey);
    	this.apiVersion = apiVersion;
    }
    
    /**
     * Creates the stack overflow api url builder.
     * 
     * @param methodName the method name
     * 
     * @return the api url builder
     */
    protected abstract ApiUrlBuilder createStackOverflowApiUrlBuilder(String methodName);

    /**
     * Checks if is null or empty.
     * 
     * @param s the s
     * 
     * @return true, if is null or empty
     */
    protected boolean isNullOrEmpty(String s) {
        return ((s == null) || (s.length() == 0));
    }

    /**
     * Assert not null or empty.
     * 
     * @param name the name
     * @param value the value
     */
    protected void assertNotNullOrEmpty(String name, String value) {
        if (isNullOrEmpty(value)) {
            throw new IllegalArgumentException(name + " cannot be null or empty.");
        }
    }

    /**
     * Assert not null or empty.
     * 
     * @param name the name
     * @param value the value
     */
    protected void assertNotNullOrEmpty(String name, Collection<?> value) {
        if ((value == null) || value.isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be null or empty.");
        }
    }

    /**
     * Assert positive number.
     * 
     * @param name the name
     * @param value the value
     */
    protected void assertPositiveNumber(String name, int value) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be less than zero.");
        }
    }

    /**
     * Assert not null.
     * 
     * @param name the name
     * @param value the value
     */
    protected void assertNotNull(String name, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(name + " cannot be null.");
        }
    }

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(Paging paging, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(TimePeriod timePeriod,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(com.google.code.stackexchange.schema.User.FavoriteSortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(User.FavoriteSortOrder sort,
			Paging paging, TimePeriod timePeriod, Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withPaging(paging).withTimePeriod(timePeriod).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Question> getQuestions(Paging paging, long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getQuestions(Set<FilterOption> filterOptions, long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.Paging, java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getQuestions(Paging paging, Set<FilterOption> filterOptions,
			long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withPaging(paging).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsTimeline(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<PostTimeline> getQuestionsTimeline(TimePeriod timePeriod,
			long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION_TIMELINE);
        String                apiUrl  = builder.withIds(questionIds).withTimePeriod(timePeriod).buildUrl();

        PostTimelines timelines = readResponse(PostTimelines.class, callApiMethod(apiUrl));
        return timelines.getTimelines();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Question> getQuestions(Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.TimePeriod)
	 */
	@Override
	public PagedList<Question> getQuestions(TimePeriod timePeriod) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withTimePeriod(timePeriod).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(java.util.Set)
	 */
	@Override
	public PagedList<Question> getQuestions(Set<FilterOption> filterOptions) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.Question.SortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, java.util.Set)
	 */
	@Override
	public PagedList<Question> getQuestions(Question.SortOrder sort, Paging paging,
			TimePeriod timePeriod, Set<FilterOption> filterOptions) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).withPaging(paging).withTimePeriod(timePeriod).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(Paging paging, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, java.util.Set, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(User.QuestionSortOrder sort,
			Paging paging, TimePeriod timePeriod, Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withPaging(paging).withTimePeriod(timePeriod).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTaggedQuestions(java.util.List, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, java.util.Set)
	 */
	@Override
	public PagedList<Question> getTaggedQuestions(List<String> tags,
			Paging paging, TimePeriod timePeriod,
			Set<FilterOption> filterOptions) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGGED_QUESTIONS);
        String                apiUrl  = builder.withFields("tags", tags).withPaging(paging).withTimePeriod(timePeriod).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTags(com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Tag> getTags(Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS);
        String                apiUrl  = builder.withPaging(paging).buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTags(com.google.code.stackexchange.schema.Tag.SortOrder, com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Tag> getTags(Tag.SortOrder sort, Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS);
        String                apiUrl  = builder.withSort(sort).withPaging(paging).buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTagsForUsers(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Tag> getTagsForUsers(Paging paging, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS_FOR_USER);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions(com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions(com.google.code.stackexchange.schema.TimePeriod)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(TimePeriod timePeriod) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withTimePeriod(timePeriod).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions(java.util.Set)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(Set<FilterOption> filterOptions) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions(com.google.code.stackexchange.schema.Question.UnansweredSortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, java.util.Set)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(
			Question.UnansweredSortOrder sort, Paging paging,
			TimePeriod timePeriod, Set<FilterOption> filterOptions) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).withPaging(paging).withTimePeriod(timePeriod).withFetchOptions(filterOptions).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersMentions(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Comment> getUsersMentions(TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_MENTIONS);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        Comments mentions = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return mentions.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersTimeline(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<UserTimeline> getUsersTimeline(TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_TIMELINE);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        UserTimelines timelines = readResponse(UserTimelines.class, callApiMethod(apiUrl));
        
        return timelines.getTimelines();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers(com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<User> getUsers(Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.withPaging(paging).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers(java.lang.String)
	 */
	@Override
	public PagedList<User> getUsers(String filter) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.withParameter("filter", filter).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers(java.lang.String, com.google.code.stackexchange.schema.User.SortOrder, com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<User> getUsers(String filter, User.SortOrder sort, Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.withParameter("filter", filter).withSort(sort).withPaging(paging).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswers(long[])
	 */
	@Override
	public PagedList<Answer> getAnswers(long... answerIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWER);
        String                apiUrl  = builder.withIds(answerIds).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByUsers(long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByQuestions(long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByQuestions(long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByUsers(com.google.code.stackexchange.schema.Answer.SortOrder, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByUsers(Answer.SortOrder sort, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getAnswersByUsers(com.google.code.stackexchange.schema.Answer.SortOrder, com.google.code.stackexchange.schema.Range, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByUsers(Answer.SortOrder sort, Range range, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withRange(range).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByQuestions(com.google.code.stackexchange.schema.Answer.SortOrder, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByQuestions(Answer.SortOrder sort, long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withSort(sort).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getAnswersByQuestions(com.google.code.stackexchange.schema.Answer.SortOrder, com.google.code.stackexchange.schema.Range, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByQuestions(Answer.SortOrder sort, Range range, long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withSort(sort).withRange(range).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadges()
	 */
	@Override
	public PagedList<Badge> getBadges() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGES);
        String                apiUrl  = builder.buildUrl();

        Badges badges = readResponse(Badges.class, callApiMethod(apiUrl));
        
        return badges.getBadges();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadgesByName()
	 */
	@Override
	public PagedList<Badge> getBadgesByName() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGES_BY_NAME);
        String                apiUrl  = builder.buildUrl();

        Badges badges = readResponse(Badges.class, callApiMethod(apiUrl));
        
        return badges.getBadges();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadgesByTags()
	 */
	@Override
	public PagedList<Badge> getBadgesByTags() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGES_BY_TAGS);
        String                apiUrl  = builder.buildUrl();

        Badges badges = readResponse(Badges.class, callApiMethod(apiUrl));
        
        return badges.getBadges();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadgesForUsers(long[])
	 */
	@Override
	public PagedList<Badge> getBadgesForUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGES_FOR_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Badges badges = readResponse(Badges.class, callApiMethod(apiUrl));
        
        return badges.getBadges();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getFavoriteQuestionsByUsers(com.google.code.stackexchange.schema.User.FavoriteSortOrder, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(User.FavoriteSortOrder sort,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getFavoriteQuestionsByUsers(com.google.code.stackexchange.schema.User.FavoriteSortOrder, com.google.code.stackexchange.schema.Range, long[])
	 */
	@Override
	public PagedList<Question> getFavoriteQuestionsByUsers(User.FavoriteSortOrder sort, Range range,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_FAVORITE_QUESTIONS);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withRange(range).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(long[])
	 */
	@Override
	public PagedList<Question> getQuestions(long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsTimeline(long[])
	 */
	@Override
	public PagedList<PostTimeline> getQuestionsTimeline(long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTION_TIMELINE);
        String                apiUrl  = builder.withIds(questionIds).buildUrl();

        PostTimelines timelines = readResponse(PostTimelines.class, callApiMethod(apiUrl));
        return timelines.getTimelines();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions()
	 */
	@Override
	public PagedList<Question> getQuestions() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestions(com.google.code.stackexchange.schema.Question.SortOrder)
	 */
	@Override
	public PagedList<Question> getQuestions(Question.SortOrder sort) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).buildUrl();
        
        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getQuestions(com.google.code.stackexchange.schema.Question.SortOrder, com.google.code.stackexchange.schema.Range)
	 */
	@Override
	public PagedList<Question> getQuestions(Question.SortOrder sort, Range range) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).withRange(range).buildUrl();
        
        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getQuestionsByUsers(com.google.code.stackexchange.schema.User.QuestionSortOrder, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(User.QuestionSortOrder sort,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getQuestionsByUsers(com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Range, long[])
	 */
	@Override
	public PagedList<Question> getQuestionsByUsers(User.QuestionSortOrder sort, Range range,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_QUESTIONS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withRange(range).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTaggedQuestions(java.util.List)
	 */
	@Override
	public PagedList<Question> getTaggedQuestions(List<String> tags) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGGED_QUESTIONS);
        String                apiUrl  = builder.withFields("tags", tags).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTags()
	 */
	@Override
	public PagedList<Tag> getTags() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS);
        String                apiUrl  = builder.buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTags(com.google.code.stackexchange.schema.Tag.SortOrder)
	 */
	@Override
	public PagedList<Tag> getTags(Tag.SortOrder sort) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS);
        String                apiUrl  = builder.withSort(sort).buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getTagsForUsers(long[])
	 */
	@Override
	public PagedList<Tag> getTagsForUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_TAGS_FOR_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Tags tags = readResponse(Tags.class, callApiMethod(apiUrl));
        
        return tags.getTags();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions()
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUnansweredQuestions(com.google.code.stackexchange.schema.Question.UnansweredSortOrder)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(Question.UnansweredSortOrder sort) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getUnansweredQuestions(com.google.code.stackexchange.schema.Question.UnansweredSortOrder, com.google.code.stackexchange.schema.Range)
	 */
	@Override
	public PagedList<Question> getUnansweredQuestions(Question.UnansweredSortOrder sort, Range range) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_UN_ANSWERED_QUESTIONS);
        String                apiUrl  = builder.withSort(sort).withRange(range).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers(long[])
	 */
	@Override
	public PagedList<User> getUsers(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersMentions(long[])
	 */
	@Override
	public PagedList<Comment> getUsersMentions(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_MENTIONS);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Comments mentions = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return mentions.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersTimeline(long[])
	 */
	@Override
	public PagedList<UserTimeline> getUsersTimeline(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_TIMELINE);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        UserTimelines timelines = readResponse(UserTimelines.class, callApiMethod(apiUrl));
        
        return timelines.getTimelines();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers()
	 */
	@Override
	public PagedList<User> getUsers() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsers(com.google.code.stackexchange.schema.User.SortOrder)
	 */
	@Override
	public PagedList<User> getUsers(User.SortOrder sort) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.withSort(sort).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getUsers(com.google.code.stackexchange.schema.User.SortOrder, com.google.code.stackexchange.schema.Range)
	 */
	@Override
	public PagedList<User> getUsers(User.SortOrder sort, Range range) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USERS);
        String                apiUrl  = builder.withSort(sort).withRange(range).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersReputations(long[])
	 */
	@Override
	public PagedList<Reputation> getUsersReputations(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_REPUTATIONS);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Reputations reputations = readResponse(Reputations.class, callApiMethod(apiUrl));
        
        return reputations.getReputations();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersReputations(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Reputation> getUsersReputations(TimePeriod timePeriod,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_REPUTATIONS);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        Reputations reputations = readResponse(Reputations.class, callApiMethod(apiUrl));
        
        return reputations.getReputations();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersReputations(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Reputation> getUsersReputations(Paging paging, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_REPUTATIONS);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).buildUrl();

        Reputations reputations = readResponse(Reputations.class, callApiMethod(apiUrl));
        
        return reputations.getReputations();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersReputations(com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Reputation> getUsersReputations(Paging paging,
			TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_USER_REPUTATIONS);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).withTimePeriod(timePeriod).buildUrl();

        Reputations reputations = readResponse(Reputations.class, callApiMethod(apiUrl));
        
        return reputations.getReputations();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersComments(long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersComments(com.google.code.stackexchange.schema.Comment.SortOrder, long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(Comment.SortOrder sort, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#getUsersComments(com.google.code.stackexchange.schema.Comment.SortOrder, com.google.code.stackexchange.schema.Range, long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(Comment.SortOrder sort, Range range, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withRange(range).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersComments(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withTimePeriod(timePeriod).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersComments(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(Paging paging, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withPaging(paging).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersComments(com.google.code.stackexchange.schema.Comment.SortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Comment> getUsersComments(Comment.SortOrder sort, Paging paging,
			TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withPaging(paging).withTimePeriod(timePeriod).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersCommentsToUser(long, long[])
	 */
	@Override
	public PagedList<Comment> getUsersCommentsToUser(long toUserId, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
        String                apiUrl  = builder.withIds(userIds).withField("toid", String.valueOf(toUserId)).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersCommentsToUser(long, com.google.code.stackexchange.schema.Comment.SortOrder, long[])
	 */
	@Override
	public PagedList<Comment> getUsersCommentsToUser(long toUserId, Comment.SortOrder sort,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
        String                apiUrl  = builder.withIds(userIds).withField("toid", String.valueOf(toUserId)).withSort(sort).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersCommentsToUser(long, com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Comment> getUsersCommentsToUser(long toUserId, TimePeriod timePeriod,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
        String                apiUrl  = builder.withIds(userIds).withField("toid", String.valueOf(toUserId)).withTimePeriod(timePeriod).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersCommentsToUser(long, com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<Comment> getUsersCommentsToUser(long toUserId, Paging paging,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
        String                apiUrl  = builder.withIds(userIds).withField("toid", String.valueOf(toUserId)).withPaging(paging).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getUsersCommentsToUser(long, com.google.code.stackexchange.schema.Comment.SortOrder, com.google.code.stackexchange.schema.Paging, com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Comment> getUsersCommentsToUser(long toUserId, Comment.SortOrder sort,
			Paging paging, TimePeriod timePeriod, long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENTS_BY_USER_TO_USER);
        String                apiUrl  = builder.withIds(userIds).withField("toid", String.valueOf(toUserId)).withSort(sort).withPaging(paging).withTimePeriod(timePeriod).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getComments(long[])
	 */
	@Override
	public PagedList<Comment> getComments(long... commentIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_COMMENT);
        String                apiUrl  = builder.withIds(commentIds).buildUrl();

        Comments comments = readResponse(Comments.class, callApiMethod(apiUrl));
        
        return comments.getComments();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getStatistics()
	 */
	@Override
	public Statistics getStatistics() {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_STATISTICS);
        String                apiUrl  = builder.buildUrl();

        return readResponse(Statistics.class, callApiMethod(apiUrl));
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswers(java.util.Set, long[])
	 */
	@Override
	public PagedList<Answer> getAnswers(Set<FilterOption> filterOptions, long... answerIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWER);
        String                apiUrl  = builder.withIds(answerIds).withFetchOptions(filterOptions).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByUsers(java.util.Set, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByUsers(Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withFetchOptions(filterOptions).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByQuestions(java.util.Set, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByQuestions(Set<FilterOption> filterOptions,
			long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withFetchOptions(filterOptions).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByUsers(com.google.code.stackexchange.schema.Answer.SortOrder, java.util.Set, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByUsers(Answer.SortOrder sort, Set<FilterOption> filterOptions,
			long... userIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_USER);
        String                apiUrl  = builder.withIds(userIds).withSort(sort).withFetchOptions(filterOptions).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getAnswersByQuestions(com.google.code.stackexchange.schema.Answer.SortOrder, java.util.Set, long[])
	 */
	@Override
	public PagedList<Answer> getAnswersByQuestions(Answer.SortOrder sort, Set<FilterOption> filterOptions,
			long... questionIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_ANSWERS_BY_QUESTION);
        String                apiUrl  = builder.withIds(questionIds).withSort(sort).withFetchOptions(filterOptions).buildUrl();

        Answers answers = readResponse(Answers.class, callApiMethod(apiUrl));
        
        return answers.getAnswers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getRevisionForPost(long, java.lang.String)
	 */
	@Override
	public Revision getRevisionForPost(long postId, String revisionGuid) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_REVISIONS_FOR_POST);
        String                apiUrl  = builder.withId(postId).withField("revisionguid", revisionGuid).buildUrl();

        Revisions revisions = readResponse(Revisions.class, callApiMethod(apiUrl));
        
        return getFirstElement(revisions.getRevisions());
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getRevisionsForPosts(long[])
	 */
	@Override
	public PagedList<Revision> getRevisionsForPosts(long... postIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_REVISIONS_FOR_POST);
        String                apiUrl  = builder.withIds(postIds).buildUrl();

        Revisions revisions = readResponse(Revisions.class, callApiMethod(apiUrl));
        
        return revisions.getRevisions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getRevisionsForPosts(com.google.code.stackexchange.schema.TimePeriod, long[])
	 */
	@Override
	public PagedList<Revision> getRevisionsForPosts(TimePeriod timePeriod, long... postIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_REVISIONS_FOR_POST);
        String                apiUrl  = builder.withIds(postIds).withTimePeriod(timePeriod).buildUrl();

        Revisions revisions = readResponse(Revisions.class, callApiMethod(apiUrl));
        
        return revisions.getRevisions();
	}
	
	/**
	 * Gets the first element.
	 * 
	 * @param list the list
	 * 
	 * @return the first element
	 */
	private <T> T getFirstElement(List<T> list) {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadgesRecipients(long[])
	 */
	@Override
	public PagedList<User> getBadgesRecipients(long... badgeIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGE_RECIPIENTS);
        String                apiUrl  = builder.withIds(badgeIds).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#getBadgesRecipients(com.google.code.stackexchange.schema.Paging, long[])
	 */
	@Override
	public PagedList<User> getBadgesRecipients(Paging paging, long... badgeIds) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.GET_BADGE_RECIPIENTS);
        String                apiUrl  = builder.withIds(badgeIds).withPaging(paging).buildUrl();

        Users users = readResponse(Users.class, callApiMethod(apiUrl));
        
        return users.getUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#searchQuestions(java.lang.String)
	 */
	@Override
	public PagedList<Question> searchQuestions(String query) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameter("intitle", query).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#searchQuestions(java.lang.String, com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Question> searchQuestions(String query, QuestionSortOrder sort,
			Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameter("intitle", query).withSort(sort).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackExchangeApiClient#searchQuestions(java.lang.String, com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Range)
	 */
	@Override
	public PagedList<Question> searchQuestions(String query, QuestionSortOrder sort,
			Range range) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameter("intitle", query).withSort(sort).withRange(range).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
	
	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#searchQuestions(java.util.List, java.util.List)
	 */
	@Override
	public PagedList<Question> searchQuestions(List<String> includeTags,
			List<String> excludeTags) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameters("tagged", includeTags).withParameters("nottagged", excludeTags).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#searchQuestions(java.util.List, java.util.List, com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Question> searchQuestions(List<String> includeTags,
			List<String> excludeTags, QuestionSortOrder sort, Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameters("tagged", includeTags).withParameters("nottagged", excludeTags).withSort(sort).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}

	/* (non-Javadoc)
	 * @see com.google.code.stackexchange.client.StackOverflowApiClient#searchQuestions(java.lang.String, java.util.List, java.util.List, com.google.code.stackexchange.schema.User.QuestionSortOrder, com.google.code.stackexchange.schema.Paging)
	 */
	@Override
	public PagedList<Question> searchQuestions(String query,
			List<String> includeTags, List<String> excludeTags,
			QuestionSortOrder sort, Paging paging) {
		ApiUrlBuilder builder = createStackOverflowApiUrlBuilder(StackExchangeApiMethods.SEARCH_QUESTIONS);
        String                apiUrl  = builder.withParameter("intitle", query).withParameters("tagged", includeTags).withParameters("nottagged", excludeTags).withSort(sort).withPaging(paging).buildUrl();

        Questions questions = readResponse(Questions.class, callApiMethod(apiUrl));
        
        return questions.getQuestions();
	}
}