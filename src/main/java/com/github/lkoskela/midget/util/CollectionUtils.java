package com.github.lkoskela.midget.util;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.enumeration;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CollectionUtils {
	private static final String DEFAULT_JOIN_DELIMITER = "";

	public static <T> Enumeration<T> enumerate(T... values) {
		return enumeration(asList(values));
	}

	public static <T> Iterable<T> iterate(Enumeration<T> enumeration) {
		List<T> list = new ArrayList<T>();
		while (enumeration.hasMoreElements()) {
			list.add(enumeration.nextElement());
		}
		return list;
	}

	public static Iterable<String> iterateAsStrings(Enumeration<?> enumeration) {
		List<String> list = new ArrayList<String>();
		while (enumeration.hasMoreElements()) {
			list.add(valueOf(enumeration.nextElement()));
		}
		return list;
	}

	public static String joinWith(String delimiter, Object... values) {
		return join(delimiter, asList(values));
	}

	public static String joinWith(String delimiter, String... values) {
		return join(delimiter, asList(values));
	}

	public static String joinWith(String delimiter, Enumeration<?> values) {
		return join(delimiter, iterateAsStrings(values));
	}

	public static String join(Object... values) {
		return joinWith(DEFAULT_JOIN_DELIMITER, values);
	}

	public static String join(Iterable<?> values) {
		return join(DEFAULT_JOIN_DELIMITER, values);
	}

	public static String join(Enumeration<?> values) {
		return join(DEFAULT_JOIN_DELIMITER, iterate(values));
	}

	public static String join(String delimiter, Iterable<?> values) {
		StringBuilder s = new StringBuilder();
		boolean shouldAppendDelimiter = false;
		for (Object each : values) {
			if (shouldAppendDelimiter) {
				s.append(delimiter);
			}
			s.append(valueOf(each));
			shouldAppendDelimiter = true;
		}
		return s.toString();
	}

	public static <T> List<T> list(Iterable<T> iterable) {
		List<T> list = new ArrayList<T>();
		for (T each : iterable) {
			list.add(each);
		}
		return list;
	}
}