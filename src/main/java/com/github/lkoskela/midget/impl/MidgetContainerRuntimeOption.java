package com.github.lkoskela.midget.impl;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import org.apache.pluto.container.om.portlet.ContainerRuntimeOption;

public class MidgetContainerRuntimeOption implements ContainerRuntimeOption {

	private final String name;
	private List<String> values;

	public MidgetContainerRuntimeOption(String name) {
		this.name = name;
		this.values = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public List<String> getValues() {
		return unmodifiableList(values);
	}

	public void addValue(String value) {
		values.add(value);
	}

}
