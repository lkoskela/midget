package com.github.lkoskela.midget.impl.services;

import java.util.Map;

import javax.portlet.PortletRequest;

import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.UserInfoService;

public class MidgetUserInfoService implements UserInfoService {

	public Map<String, String> getUserInfo(PortletRequest request,
			PortletWindow window) throws PortletContainerException {
		return null;
	}

}
