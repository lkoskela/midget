package com.github.lkoskela.midget.impl.services;

import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.PortletPreference;
import org.apache.pluto.container.PortletPreferencesService;
import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.om.portlet.PortletDefinition;

public class MidgetPortletPreferencesService implements PortletPreferencesService {

	public Map<String, PortletPreference> getDefaultPreferences(
			PortletWindow portletWindow, PortletRequest request)
			throws PortletContainerException {
		return null;
	}

	public Map<String, PortletPreference> getStoredPreferences(
			PortletWindow portletWindow, PortletRequest request)
			throws PortletContainerException {
		return null;
	}

	public void store(PortletWindow portletWindow, PortletRequest request,
			Map<String, PortletPreference> preferences)
			throws PortletContainerException {
	}

	public PreferencesValidator getPreferencesValidator(
			PortletDefinition portletDefinition) throws ValidatorException {
		return null;
	}

}
