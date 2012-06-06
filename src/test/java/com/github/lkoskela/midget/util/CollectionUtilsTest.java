package com.github.lkoskela.midget.util;

import static com.github.lkoskela.midget.util.CollectionUtils.enumerate;
import static com.github.lkoskela.midget.util.CollectionUtils.iterate;
import static com.github.lkoskela.midget.util.CollectionUtils.join;
import static com.github.lkoskela.midget.util.CollectionUtils.joinWith;
import static java.util.Arrays.asList;
import static java.util.Collections.list;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class CollectionUtilsTest {

	private final Object obj1 = new Object();
	private final Object obj2 = new Object();

	@Test
	public void joinConcatenatesStringsTogether() throws Exception {
		assertEquals("abc", join("a", "b", "c"));
		assertEquals("a,b,c", join(",", asList("a", "b", "c")));
	}

	@Test
	public void objectsAreJoinedTogetherUsingTheirToString() throws Exception {
		assertEquals("123", join(1, 2, 3));
		assertEquals(obj1.toString() + obj2.toString(), join(obj1, obj2));
	}

	@Test
	public void joiningWithSpecificDelimiter() throws Exception {
		String expected = obj1.toString() + ", " + obj2.toString();
		assertEquals(expected, joinWith(", ", obj1, obj2));
		assertEquals("a,b,c", joinWith(",", new String[] { "a", "b", "c" }));
	}

	@Test
	public void enumerates() throws Exception {
		List<String> contentsOfEnumeration = list(enumerate("a", "b", "c"));
		assertEquals(asList("a", "b", "c"), contentsOfEnumeration);
	}

	@Test
	public void iterates() throws Exception {
		Iterable<String> iterable = iterate(enumerate("a", "b", "c"));
		assertEquals(asList("a", "b", "c"), CollectionUtils.list(iterable));
	}
}
