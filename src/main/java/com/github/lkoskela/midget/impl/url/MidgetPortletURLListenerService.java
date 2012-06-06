package com.github.lkoskela.midget.impl.url;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURLGenerationListener;

import org.apache.pluto.container.PortletURLListenerService;
import org.apache.pluto.container.om.portlet.PortletApplicationDefinition;

public class MidgetPortletURLListenerService implements PortletURLListenerService {

	public List<PortletURLGenerationListener> getPortletURLGenerationListeners(
			PortletApplicationDefinition app) {
		return new ArrayList<PortletURLGenerationListener>();
	}
}
