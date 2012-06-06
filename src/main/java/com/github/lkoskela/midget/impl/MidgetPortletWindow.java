package com.github.lkoskela.midget.impl;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.PortletWindowID;
import org.apache.pluto.container.om.portlet.PortletDefinition;

public class MidgetPortletWindow implements PortletWindow {

	private final PortletWindowID id;
	private final PortletDefinition portletDefinition;
	private PortletMode portletMode;
	private WindowState windowState;

	public MidgetPortletWindow(PortletDefinition portletDefinition) {
		this(portletDefinition, PortletMode.VIEW);
	}

	public MidgetPortletWindow(PortletDefinition portletDefinition,
			PortletMode mode) {
		this.portletDefinition = portletDefinition;
		this.portletMode = mode;
		this.id = new MidgetPortletWindowID();
		this.windowState = WindowState.NORMAL;
	}

	public PortletWindowID getId() {
		return id;
	}

	public PortletDefinition getPortletDefinition() {
		return portletDefinition;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void setWindowState(WindowState state) {
		this.windowState = state;
	}

	public PortletMode getPortletMode() {
		return portletMode;
	}

	public void setPortletMode(PortletMode mode) {
		portletMode = mode;
	}
}
