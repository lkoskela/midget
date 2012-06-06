package com.github.lkoskela.midget.util;

import static com.github.lkoskela.midget.util.ID.idWithPrefix;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IDTest {
	@Test
	public void generatesPrefixedNames() throws Exception {
		assertThat(idWithPrefix("prefix"), startsWith("prefix"));
	}

	@Test
	public void generatesUniqueNames() throws Exception {
		String prefix = "whatever";
		String first = idWithPrefix(prefix);
		String second = idWithPrefix(prefix);
		assertThat(second, is(not(equalTo(first))));
	}
}