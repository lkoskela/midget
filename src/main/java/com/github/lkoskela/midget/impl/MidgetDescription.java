package com.github.lkoskela.midget.impl;

import java.util.Locale;

import org.apache.pluto.container.om.portlet.Description;

public class MidgetDescription implements Description {

	private final String lang;
	private String description;
	private Locale locale;

	public MidgetDescription(String lang) {
		this.lang = lang;
		this.locale = Locale.getDefault();
	}

	public String getLang() {
		return lang;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Locale getLocale() {
		return locale;
	}

}
