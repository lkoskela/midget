package com.github.lkoskela.midget.impl;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.xml.namespace.QName;

import org.apache.pluto.container.om.portlet.ContainerRuntimeOption;
import org.apache.pluto.container.om.portlet.Description;
import org.apache.pluto.container.om.portlet.DisplayName;
import org.apache.pluto.container.om.portlet.EventDefinitionReference;
import org.apache.pluto.container.om.portlet.InitParam;
import org.apache.pluto.container.om.portlet.PortletApplicationDefinition;
import org.apache.pluto.container.om.portlet.PortletDefinition;
import org.apache.pluto.container.om.portlet.PortletInfo;
import org.apache.pluto.container.om.portlet.Preferences;
import org.apache.pluto.container.om.portlet.SecurityRoleRef;
import org.apache.pluto.container.om.portlet.Supports;

public class MidgetPortletDefinition implements PortletDefinition {

	private final PortletConfig config;
	private String portletClass;
	private String resourceBundle;
	private Map<QName, EventDefinitionReference> supportedProcessingEvents;
	private Map<QName, EventDefinitionReference> supportedPublishingEvents;
	private List<String> supportedPublicRenderParameters;
	private List<SecurityRoleRef> securityRoleRefs;
	private List<Supports> supports;
	private List<Description> descriptions;
	private List<DisplayName> displayNames;
	private List<String> supportedLocales;
	private int expirationCache;
	private String cacheScope;
	private List<ContainerRuntimeOption> containerRuntimeOptions;

	public MidgetPortletDefinition(Class<? extends Portlet> portletClass,
			PortletConfig config) {
		this.portletClass = portletClass.getName();
		this.config = config;
		this.supportedProcessingEvents = new HashMap<QName, EventDefinitionReference>();
		this.supportedPublishingEvents = new HashMap<QName, EventDefinitionReference>();
		this.supportedPublicRenderParameters = new ArrayList<String>();
		this.securityRoleRefs = new ArrayList<SecurityRoleRef>();
		this.supports = new ArrayList<Supports>();
		this.descriptions = new ArrayList<Description>();
		this.displayNames = new ArrayList<DisplayName>();
		this.supportedLocales = new ArrayList<String>();
		this.expirationCache = 0;
		this.containerRuntimeOptions = new ArrayList<ContainerRuntimeOption>();
	}

	public String getPortletName() {
		return config.getPortletName();
	}

	public String getPortletClass() {
		return portletClass;
	}

	public void setPortletClass(String portletClass) {
		this.portletClass = portletClass;
	}

	public PortletApplicationDefinition getApplication() {
		return null;
	}

	public InitParam getInitParam(String paramName) {
		return null;
	}

	public List<? extends InitParam> getInitParams() {
		return null;
	}

	public InitParam addInitParam(String paramName) {
		return null;
	}

	public PortletInfo getPortletInfo() {
		return null;
	}

	public Preferences getPortletPreferences() {
		return null;
	}

	public List<? extends EventDefinitionReference> getSupportedProcessingEvents() {
		return unmodifiableList(new ArrayList<EventDefinitionReference>(
				supportedProcessingEvents.values()));
	}

	public EventDefinitionReference addSupportedProcessingEvent(
			final QName qname) {
		EventDefinitionReference def = new MidgetEventDefinitionReference(qname);
		supportedProcessingEvents.put(qname, def);
		return def;
	}

	public EventDefinitionReference addSupportedProcessingEvent(String name) {
		return addSupportedProcessingEvent(new QName(name));
	}

	public List<? extends EventDefinitionReference> getSupportedPublishingEvents() {
		return unmodifiableList(new ArrayList<EventDefinitionReference>(
				supportedPublishingEvents.values()));
	}

	public EventDefinitionReference addSupportedPublishingEvent(QName qname) {
		EventDefinitionReference def = new MidgetEventDefinitionReference(qname);
		supportedPublishingEvents.put(qname, def);
		return def;
	}

	public EventDefinitionReference addSupportedPublishingEvent(String name) {
		return addSupportedPublishingEvent(new QName(name));
	}

	public List<String> getSupportedPublicRenderParameters() {
		return unmodifiableList(supportedPublicRenderParameters);
	}

	public void addSupportedPublicRenderParameter(String identifier) {
		supportedPublicRenderParameters.add(identifier);
	}

	public String getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(String resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public SecurityRoleRef getSecurityRoleRef(String roleName) {
		for (SecurityRoleRef ref : getSecurityRoleRefs()) {
			if (roleName.equals(ref.getRoleName())) {
				return ref;
			}
		}
		return null;
	}

	public List<? extends SecurityRoleRef> getSecurityRoleRefs() {
		return unmodifiableList(securityRoleRefs);
	}

	public SecurityRoleRef addSecurityRoleRef(String roleName) {
		SecurityRoleRef ref = new MidgetSecurityRoleRef(roleName);
		securityRoleRefs.add(ref);
		return ref;
	}

	public Supports getSupports(String mimeType) {
		for (Supports s : getSupports()) {
			if (mimeType.equals(s.getMimeType())) {
				return s;
			}
		}
		return null;
	}

	public List<? extends Supports> getSupports() {
		return unmodifiableList(supports);
	}

	public Supports addSupports(String mimeType) {
		Supports s = new MidgetSupports(mimeType);
		supports.add(s);
		return s;
	}

	public Description getDescription(Locale locale) {
		for (Description d : getDescriptions()) {
			if (locale.equals(d.getLocale())) {
				return d;
			}
		}
		return null;
	}

	public List<? extends Description> getDescriptions() {
		return unmodifiableList(descriptions);
	}

	public Description addDescription(String lang) {
		Description d = new MidgetDescription(lang);
		descriptions.add(d);
		return d;
	}

	public DisplayName getDisplayName(Locale locale) {
		for (DisplayName name : getDisplayNames()) {
			if (locale.equals(name.getLocale())) {
				return name;
			}
		}
		return null;
	}

	public List<? extends DisplayName> getDisplayNames() {
		return unmodifiableList(displayNames);
	}

	public DisplayName addDisplayName(String lang) {
		DisplayName name = new MidgetDisplayName(lang);
		displayNames.add(name);
		return name;
	}

	public List<String> getSupportedLocales() {
		return unmodifiableList(supportedLocales);
	}

	public void addSupportedLocale(String lang) {
		supportedLocales.add(lang);
	}

	public int getExpirationCache() {
		return expirationCache;
	}

	public void setExpirationCache(int expirationCache) {
		this.expirationCache = expirationCache;
	}

	public String getCacheScope() {
		return cacheScope;
	}

	public void setCacheScope(String cacheScope) {
		this.cacheScope = cacheScope;
	}

	public ContainerRuntimeOption getContainerRuntimeOption(String name) {
		for (ContainerRuntimeOption option : getContainerRuntimeOptions()) {
			if (name.equals(option.getName())) {
				return option;
			}
		}
		return null;
	}

	public List<? extends ContainerRuntimeOption> getContainerRuntimeOptions() {
		return unmodifiableList(containerRuntimeOptions);
	}

	public ContainerRuntimeOption addContainerRuntimeOption(String name) {
		ContainerRuntimeOption option = new MidgetContainerRuntimeOption(name);
		containerRuntimeOptions.add(option);
		return option;
	}
}
