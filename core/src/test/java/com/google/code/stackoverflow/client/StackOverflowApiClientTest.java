/**
 *
 */
package com.google.code.stackoverflow.client;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.google.code.stackoverflow.client.constant.TestConstants;
import com.google.code.stackoverflow.schema.Paging;
import com.google.code.stackoverflow.schema.TimePeriod;

/**
 * The Class StackOverflowApiClientTest.
 */
public abstract class StackOverflowApiClientTest extends TestCase {

    /** The factory. */
    protected StackOverflowApiClientFactory factory;

    /** The Constant RESOURCE_MISSING_MESSAGE. */
	protected static final String RESOURCE_MISSING_MESSAGE = "Please define a test %s in TestConstants.properties file.";


    /**
     * Sets the up before class.
     * 
     * @throws Exception the exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    /**
     * Tear down after class.
     * 
     * @throws Exception the exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Before
    public void setUp() throws Exception {
    	assertNotNull(String.format(RESOURCE_MISSING_MESSAGE, "Application Key"), TestConstants.STACK_OVERFLOW_TEST_API_KEY);
    	factory =
            StackOverflowApiClientFactory.newInstance(TestConstants.STACK_OVERFLOW_TEST_API_KEY);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @After
    public void tearDown() throws Exception {
    	factory = null;
    }

	/**
	 * Gets the current date.
	 * 
	 * @return the current date
	 */
	protected Date getCurrentDate() {
		return new Date();
	}

	/**
	 * Gets the last week date.
	 * 
	 * @return the last week date
	 */
	protected Date getLastWeekDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -7);
		return calendar.getTime();
	}

	/**
	 * Gets the paging.
	 * 
	 * @return the paging
	 */
	protected Paging getPaging() {
    	assertNotNullOrEmpty(String.format(RESOURCE_MISSING_MESSAGE, "Test Page No."), TestConstants.STACK_OVERFLOW_TEST_PAGE_NO);
    	assertNotNullOrEmpty(String.format(RESOURCE_MISSING_MESSAGE, "Test Page Size"), TestConstants.STACK_OVERFLOW_TEST_PAGE_SIZE);
    	return new Paging(Integer.parseInt(TestConstants.STACK_OVERFLOW_TEST_PAGE_NO), Integer.parseInt(TestConstants.STACK_OVERFLOW_TEST_PAGE_SIZE));    	
	}
	
	/**
	 * Gets the tags.
	 * 
	 * @return the tags
	 */
	protected List<String> getTags() {
    	assertNotNullOrEmpty(String.format(RESOURCE_MISSING_MESSAGE, "Test Tags."), TestConstants.STACK_OVERFLOW_TEST_TAGS);
    	return Arrays.asList(TestConstants.STACK_OVERFLOW_TEST_TAGS.split(","));
	}
	
	/**
	 * Gets the time period.
	 * 
	 * @return the time period
	 */
	protected TimePeriod getTimePeriod() {
		return new TimePeriod(getLastWeekDate(), new Date());
	}
	
	/**
	 * Gets the ids.
	 * 
	 * @param idString the id string
	 * 
	 * @return the ids
	 */
	protected long[] getIds(String idString) {
		String[] tokens = idString.split(",");
		long[] ids = new long[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			ids[i] = Long.valueOf(tokens[i]);
		}
		return ids;
	}
	
	/**
	 * Assert not null or empty.
	 * 
	 * @param message the message
	 * @param value the value
	 */
	protected static void assertNotNullOrEmpty(String message, String value) {
		assertNotNull(message, value);
		assertFalse(message, "".equals(value));
	}
	
	/**
	 * Assert not null or empty.
	 * 
	 * @param message the message
	 * @param value the value
	 */
	protected static void assertNotNullOrEmpty(String message, Collection<?> value) {
		assertNotNull(message, value);
		assertFalse(message, value.isEmpty());
	}
}
