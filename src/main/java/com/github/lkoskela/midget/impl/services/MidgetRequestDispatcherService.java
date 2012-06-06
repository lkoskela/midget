package com.github.lkoskela.midget.impl.services;

import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.pluto.container.RequestDispatcherService;
import org.apache.pluto.container.om.portlet.PortletApplicationDefinition;

public class MidgetRequestDispatcherService implements RequestDispatcherService {

	public PortletRequestDispatcher getRequestDispatcher(
			ServletContext servletContext, PortletApplicationDefinition app,
			String path) {
		throw new RuntimeException("Not implemented.");
	}

	public PortletRequestDispatcher getNamedDispatcher(
			ServletContext servletContext, PortletApplicationDefinition app,
			String name) {
		throw new RuntimeException("Not implemented.");
	}

	public HttpServletRequestWrapper getRequestWrapper(
			ServletContext servletContext, HttpServletRequest servletRequest,
			PortletRequest portletRequest, HttpSession session,
			boolean included, boolean named) {
		throw new RuntimeException("Not implemented.");
	}

	public HttpServletResponseWrapper getResponseWraper(
			ServletContext servletContext, HttpServletResponse servletResponse,
			PortletRequest portletRequest, PortletResponse portletResponse,
			boolean included) {
		throw new RuntimeException("Not implemented.");
	}
}
