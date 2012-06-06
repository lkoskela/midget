package com.github.lkoskela.midget.impl;

import static com.github.lkoskela.midget.util.CollectionUtils.enumerate;
import static javax.portlet.PortletMode.EDIT;
import static javax.portlet.PortletMode.HELP;
import static javax.portlet.PortletMode.VIEW;
import static javax.portlet.WindowState.MAXIMIZED;
import static javax.portlet.WindowState.MINIMIZED;
import static javax.portlet.WindowState.NORMAL;

import java.util.Enumeration;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

public class MidgetPortalContext implements PortalContext {

	public String getProperty(String name) {
		return null;
	}

	public Enumeration<String> getPropertyNames() {
		return null;
	}

	public Enumeration<PortletMode> getSupportedPortletModes() {
		return enumerate(VIEW, EDIT, HELP);
	}

	public Enumeration<WindowState> getSupportedWindowStates() {
		return enumerate(NORMAL, MINIMIZED, MAXIMIZED);
	}

	public String getPortalInfo() {
		return "PortletTestContainer/2.0";
	}
}
