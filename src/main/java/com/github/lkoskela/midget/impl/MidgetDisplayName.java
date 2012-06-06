package com.github.lkoskela.midget.impl;

import java.util.Locale;

import org.apache.pluto.container.om.portlet.DisplayName;

public class MidgetDisplayName implements DisplayName {

	private final String lang;
	private String displayName;
	private Locale locale;

	public MidgetDisplayName(String lang) {
		this.lang = lang;
		this.locale = Locale.getDefault();
	}

	public String getLang() {
		return lang;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Locale getLocale() {
		return locale;
	}

}
