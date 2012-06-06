package com.github.lkoskela.midget.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ID {
	private static final Map<String, AtomicLong> sequences = new HashMap<String, AtomicLong>();

	public static String idWithPrefix(String prefix) {
		if (!sequences.containsKey(prefix)) {
			sequences.put(prefix, new AtomicLong());
		}
		return prefix + sequences.get(prefix).incrementAndGet();
	}
}
