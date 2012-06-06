package com.github.lkoskela.midget.impl.url;

import org.apache.pluto.container.ResourceURLProvider;

public class MidgetResourceURLProvider implements ResourceURLProvider {

	private static final String PORTAL_BASE_URL = "http://localhost/midget";

	private String absoluteUrl = PORTAL_BASE_URL;

	public void setAbsoluteURL(String path) {
		this.absoluteUrl = path;
	}

	public void setFullPath(String path) {
		this.absoluteUrl = PORTAL_BASE_URL + path;
	}

	@Override
	public String toString() {
		return absoluteUrl;
	}
}
