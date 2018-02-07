package com.lebaoxun.commons.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.lebaoxun.commons.exception.I18nMessageException;

public abstract class Assert {
	
	/**
	 * Assert that an object is {@code null} .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the object is not {@code null}
	 */
	public static void isNull(Object object, String errcode,String message) {
		if (object != null) {
			throw new I18nMessageException(errcode,message);
		}
	}

	/**
	 * Assert that an object is {@code null} .
	 * <pre class="code">Assert.isNull(value);</pre>
	 * @param object the object to check
	 * @throws I18nMessageException if the object is not {@code null}
	 */
	public static void isNull(Object object) {
		isNull(object, "500" , "[Assertion failed] - the object argument must be null");
	}

	/**
	 * Assert that an object is not {@code null} .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the object is {@code null}
	 */
	public static void notNull(Object object, String errcode , String message) {
		if (object == null) {
			throw new I18nMessageException(errcode,message);
		}
	}

	/**
	 * Assert that an object is not {@code null} .
	 * <pre class="code">Assert.notNull(clazz);</pre>
	 * @param object the object to check
	 * @throws I18nMessageException if the object is {@code null}
	 */
	public static void notNull(Object object,String errcode) {
		notNull(object, errcode == null ? "500" : errcode , "[Assertion failed] - this argument is required; it must not be null");
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(String str, String errcode,String message) {
		if (org.apache.commons.lang.StringUtils.isEmpty(str)) {
			throw new I18nMessageException(errcode,message);
		}
	}
	
	/**
	 * Assert that an array has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(Object[] array, String errcode) {
		if (ObjectUtils.isEmpty(array)) {
			throw new I18nMessageException(errcode);
		}
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is empty!
	 * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
	 * @param array the array to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the object array contains a {@code null} element
	 */
	public static void noNullElements(Object[] array, String errcode) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new I18nMessageException(errcode);
				}
			}
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * {@code null} and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the collection is {@code null} or has no elements
	 */
	public static void notEmpty(Collection<?> collection, String errcode) {
		if (CollectionUtils.isEmpty(collection)) {
			throw new I18nMessageException(errcode);
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be {@code null}
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
	 * @param map the map to check
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if the map is {@code null} or has no entries
	 */
	public static void notEmpty(Map<?, ?> map, String errcode, String message) {
		if (CollectionUtils.isEmpty(map)) {
			throw new I18nMessageException(errcode,message);
		}
	}

	/**
	 * Assert a boolean expression, throwing {@code I18nMessageException}
	 * if the test result is {@code false}. Call isTrue if you wish to
	 * throw I18nMessageException on an assertion failure.
	 * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
	 * @param expression a boolean expression
	 * @param errcode the exception errcode to use if the assertion fails
	 * @throws I18nMessageException if expression is {@code false}
	 */
	public static void state(boolean expression, String errcode) {
		if (!expression) {
			throw new I18nMessageException(errcode);
		}
	}
	
	public static void equals(Object o1, Object o2 , String errcode, String message) {
		if (!o1.equals(o2)) {
			throw new I18nMessageException(errcode,message);
		}
	}
	
	public static void isTrue(boolean result, String errcode,String message) {
		if (!result) {
			throw new I18nMessageException(errcode,message);
		}
	}
	
	public static void isNumber(String val, String errcode,String message) {
		if (!org.apache.commons.lang.StringUtils.isNumeric(val)) {
			throw new I18nMessageException(errcode,message);
		}
	}
	
	public static void hasLength(String str,int length, String errcode,String message) {
		notEmpty(str, errcode, message);
		if (str.length() != length) {
			throw new I18nMessageException(errcode,message);
		}
	}
	
}
