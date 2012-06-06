package com.github.lkoskela.midget.impl;

import static com.github.lkoskela.midget.util.ID.idWithPrefix;

import org.apache.pluto.container.PortletWindowID;

public class MidgetPortletWindowID implements PortletWindowID {

	private final String id = idWithPrefix("PortletWindow-");

	public String getStringId() {
		return id;
	}
}
