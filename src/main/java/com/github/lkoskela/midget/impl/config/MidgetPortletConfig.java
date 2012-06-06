package com.github.lkoskela.midget.impl.config;

import static com.github.lkoskela.midget.util.CollectionUtils.enumerate;
import static com.github.lkoskela.midget.util.ID.idWithPrefix;
import static java.util.Locale.getAvailableLocales;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.xml.namespace.QName;

import org.springframework.mock.web.portlet.MockPortletContext;

import com.github.lkoskela.midget.MidgetExecutionContext;

public class MidgetPortletConfig implements PortletConfig {
	private final String portletName = idWithPrefix("Portlet-");
	private MockPortletContext portletContext;

	public MidgetPortletConfig(MidgetExecutionContext executionContext) {
		this.portletContext = new MidgetPortletContext(executionContext);
	}

	public String getPortletName() {
		return portletName;
	}

	public PortletContext getPortletContext() {
		return portletContext;
	}

	public ResourceBundle getResourceBundle(Locale locale) {
		try {
			Properties properties = new Properties();
			properties
					.setProperty("javax.portlet.title", "javax.portlet.title");
			ByteArrayOutputStream bundle = new ByteArrayOutputStream();
			properties.store(bundle, "Fake portlet resource bundle");
			return new PropertyResourceBundle(new ByteArrayInputStream(
					bundle.toByteArray()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDefaultNamespace() {
		return "http://defaultnamespace";
	}

	public String getInitParameter(String name) {
		return null;
	}

	public Enumeration<String> getInitParameterNames() {
		return enumerate();
	}

	public Enumeration<String> getPublicRenderParameterNames() {
		return enumerate();
	}

	public Enumeration<QName> getPublishingEventQNames() {
		return enumerate();
	}

	public Enumeration<QName> getProcessingEventQNames() {
		return enumerate();
	}

	public Enumeration<Locale> getSupportedLocales() {
		return enumerate(getAvailableLocales());
	}

	public Map<String, String[]> getContainerRuntimeOptions() {
		return new HashMap<String, String[]>();
	}
}
