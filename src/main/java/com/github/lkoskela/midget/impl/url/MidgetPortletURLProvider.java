package com.github.lkoskela.midget.impl.url;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletSecurityException;
import javax.portlet.WindowState;

import org.apache.pluto.container.PortletURLProvider;

public class MidgetPortletURLProvider implements PortletURLProvider {

	private final TYPE type;
	private PortletMode portletMode;
	private WindowState windowState;
	private boolean isSecure;
	private String cacheLevel;
	private String resourceID;

	public MidgetPortletURLProvider(TYPE type) {
		this.type = type;
	}

	public TYPE getType() {
		return type;
	}

	public void setPortletMode(PortletMode mode) {
		this.portletMode = mode;
	}

	public PortletMode getPortletMode() {
		return portletMode;
	}

	public void setWindowState(WindowState state) {
		this.windowState = state;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void setSecure(boolean secure) throws PortletSecurityException {
		this.isSecure = secure;
	}

	public boolean isSecure() {
		return isSecure;
	}

	public Map<String, String[]> getRenderParameters() {
		return new HashMap<String, String[]>();
	}

	public Map<String, String[]> getPublicRenderParameters() {
		return new HashMap<String, String[]>();
	}

	public String getCacheability() {
		return cacheLevel;
	}

	public void setCacheability(String cacheLevel) {
		this.cacheLevel = cacheLevel;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String toURL() {
		return "http://localhost/midget/" + getResourceID();
	}

	public void write(Writer out, boolean escapeXML) throws IOException {
		throw new RuntimeException("Not implemented.");
	}

	public Map<String, List<String>> getProperties() {
		return new HashMap<String, List<String>>();
	}
}
