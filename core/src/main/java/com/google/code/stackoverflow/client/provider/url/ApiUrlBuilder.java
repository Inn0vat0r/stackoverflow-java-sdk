package com.google.code.stackoverflow.client.provider.url;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.code.stackoverflow.client.StackOverflowApiClient.Paging;
import com.google.code.stackoverflow.client.StackOverflowApiClient.TimePeriod;
import com.google.code.stackoverflow.schema.FilterOption;
import com.google.code.stackoverflow.schema.SortEnum;
import com.google.code.stackoverflow.schema.ValueEnum;

public interface ApiUrlBuilder {

	/**
	 * With parameter.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withParameter(String name, String value);

	/**
	 * With parameters.
	 * 
	 * @param name
	 *            the name
	 * @param values
	 *            the values
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withParameters(String name,
			Collection<String> values);

	/**
	 * With parameter enum.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withParameterEnum(String name,
			ValueEnum value);

	/**
	 * With parameter enum.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withPaging(Paging paging);

	/**
	 * With parameter enum.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withTimePeriod(TimePeriod timePeriod);

	/**
	 * With parameter enum.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withSort(SortEnum sort);

	public ApiUrlBuilder withFetchOptions(
			Set<FilterOption> fetchOptions);

	/**
	 * With parameter enum map.
	 * 
	 * @param enumMap
	 *            the enum map
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withParameterEnumMap(
			Map<? extends ValueEnum, String> enumMap);

	/**
	 * With empty field.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withEmptyField(String name);

	/**
	 * With field.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withField(String name, String value);

	/**
	 * With field.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withField(String name, String value,
			boolean escape);

	/**
	 * With field enum.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withFieldEnum(String name, ValueEnum value);

	/**
	 * With field enum set.
	 * 
	 * @param name
	 *            the name
	 * @param enumSet
	 *            the enum set
	 * 
	 * @return the stack overflow api url builder
	 */
	public ApiUrlBuilder withFieldEnumSet(String name,
			Set<? extends ValueEnum> enumSet);

	/**
	 * Builds the url.
	 * 
	 * @return the string
	 */
	public String buildUrl();

}