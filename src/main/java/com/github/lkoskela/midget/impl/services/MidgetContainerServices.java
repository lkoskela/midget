package com.github.lkoskela.midget.impl.services;

import javax.portlet.PortalContext;

import org.apache.pluto.container.CCPPProfileService;
import org.apache.pluto.container.ContainerServices;
import org.apache.pluto.container.EventCoordinationService;
import org.apache.pluto.container.FilterManagerService;
import org.apache.pluto.container.NamespaceMapper;
import org.apache.pluto.container.PortletEnvironmentService;
import org.apache.pluto.container.PortletInvokerService;
import org.apache.pluto.container.PortletPreferencesService;
import org.apache.pluto.container.PortletRequestContextService;
import org.apache.pluto.container.PortletURLListenerService;
import org.apache.pluto.container.RequestDispatcherService;
import org.apache.pluto.container.UserInfoService;

import com.github.lkoskela.midget.MidgetExecutionContext;
import com.github.lkoskela.midget.impl.MidgetPortalContext;
import com.github.lkoskela.midget.impl.url.MidgetPortletURLListenerService;

public class MidgetContainerServices implements ContainerServices {

	private PortalContext portalContext;
	private EventCoordinationService eventCoordinationService;
	private PortletRequestContextService portletRequestContextService;
	private FilterManagerService filterManagerService;
	private PortletURLListenerService portletURLListenerService;
	private PortletPreferencesService portletPreferencesService;
	private PortletEnvironmentService portletEnvService;
	private MidgetPortletInvokerService portletInvokerService;
	private MidgetUserInfoService userInfoService;
	private MidgetNamespaceMapper namespaceMapper;
	private MidgetCCPPProfileService ccppProfileService;
	private RequestDispatcherService requestDispatcherService;

	public MidgetContainerServices(MidgetExecutionContext executionContext) {
		portalContext = new MidgetPortalContext();
		eventCoordinationService = new MidgetEventCoordinationService();
		portletRequestContextService = new MidgetPortletRequestContextService(
				executionContext);
		filterManagerService = new MidgetFilterManagerService();
		portletURLListenerService = new MidgetPortletURLListenerService();
		portletPreferencesService = new MidgetPortletPreferencesService();
		portletEnvService = new MidgetPortletEnvironmentService(portalContext,
				executionContext);
		portletInvokerService = new MidgetPortletInvokerService(
				executionContext);
		userInfoService = new MidgetUserInfoService();
		namespaceMapper = new MidgetNamespaceMapper();
		ccppProfileService = new MidgetCCPPProfileService();
		requestDispatcherService = new MidgetRequestDispatcherService();
	}

	public PortalContext getPortalContext() {
		return portalContext;
	}

	public EventCoordinationService getEventCoordinationService() {
		return eventCoordinationService;
	}

	public PortletRequestContextService getPortletRequestContextService() {
		return portletRequestContextService;
	}

	public FilterManagerService getFilterManagerService() {
		return filterManagerService;
	}

	public PortletURLListenerService getPortletURLListenerService() {
		return portletURLListenerService;
	}

	public PortletPreferencesService getPortletPreferencesService() {
		return portletPreferencesService;
	}

	public PortletEnvironmentService getPortletEnvironmentService() {
		return portletEnvService;
	}

	public PortletInvokerService getPortletInvokerService() {
		return portletInvokerService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public NamespaceMapper getNamespaceMapper() {
		return namespaceMapper;
	}

	public CCPPProfileService getCCPPProfileService() {
		return ccppProfileService;
	}

	public RequestDispatcherService getRequestDispatcherService() {
		return requestDispatcherService;
	}

}
