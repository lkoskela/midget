package com.github.lkoskela.midget.impl.config;

import javax.portlet.PortletRequestDispatcher;

import org.springframework.mock.web.portlet.MockPortletContext;

import com.github.lkoskela.midget.MidgetExecutionContext;
import com.github.lkoskela.midget.impl.MidgetPortletRequestDispatcher;

public class MidgetPortletContext extends MockPortletContext {

	private final MidgetExecutionContext executionContext;

	public MidgetPortletContext(MidgetExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	@Override
	public PortletRequestDispatcher getRequestDispatcher(String path) {
		return new MidgetPortletRequestDispatcher(executionContext, path);
	}

	@Override
	public PortletRequestDispatcher getNamedDispatcher(String path) {
		throw new RuntimeException("Not implemented.");
	}
}
