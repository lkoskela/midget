package com.github.lkoskela.midget.impl;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.pluto.container.om.portlet.Supports;

public class MidgetSupports implements Supports {

	private final String mimeType;

	public MidgetSupports(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public List<String> getPortletModes() {
		return asList();
	}

	public void addPortletMode(String portletMode) {
	}

	public List<String> getWindowStates() {
		return asList();
	}

	public void addWindowState(String windowState) {
	}
}
