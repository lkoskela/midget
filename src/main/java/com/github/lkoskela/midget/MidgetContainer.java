package com.github.lkoskela.midget;

import static com.github.lkoskela.midget.util.ID.idWithPrefix;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.ServletContext;

import org.apache.pluto.container.ContainerServices;
import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.impl.PortletContainerImpl;
import org.apache.pluto.container.om.portlet.PortletDefinition;
import org.springframework.mock.web.MockServletContext;

import com.github.lkoskela.midget.impl.MidgetPortletDefinition;
import com.github.lkoskela.midget.impl.MidgetPortletWindow;
import com.github.lkoskela.midget.impl.config.MidgetPortletConfig;
import com.github.lkoskela.midget.impl.services.MidgetContainerServices;

public class MidgetContainer {

	private PortletContainer container;
	private MidgetExecutionContext executionContext;

	public MidgetContainer() {
		this.executionContext = new MidgetExecutionContext();
		this.container = initializePortletContainer();
	}

	/**
	 * Deploy a Portlet to the test bench.
	 * 
	 * @return DeployedPortlet for triggering simulated requests to the Portlet.
	 */
	public DeployedPortlet deploy(Portlet portlet) {
		PortletConfig config = new MidgetPortletConfig(executionContext);
		MidgetPortletWindow window = createPortletWindow(portlet, config);
		initialize(portlet, config);
		return createDeployedPortlet(portlet, config, window);
	}

	/**
	 * Destroy the container. This is advisable to do in test classes tear-down
	 * methods to free objects for garbage collection.
	 */
	public void destroy() throws PortletContainerException {
		container.destroy();
	}

	private PortletContainer initializePortletContainer() {
		String id = idWithPrefix("Container-");
		ContainerServices services = new MidgetContainerServices(
				executionContext);
		PortletContainer container = new PortletContainerImpl(id, services);
		try {
			container.init();
		} catch (PortletContainerException e) {
			throw new RuntimeException(e);
		}
		return container;
	}

	private DeployedPortlet createDeployedPortlet(Portlet portlet,
			PortletConfig config, MidgetPortletWindow window) {
		ServletContext servletContext = new MockServletContext();
		DeployedPortlet handle = new DeployedPortlet(container, portlet,
				config.getPortletContext(), servletContext, executionContext,
				window);
		executionContext.add(config, servletContext, handle);
		return handle;
	}

	private void initialize(Portlet portlet, PortletConfig config) {
		try {
			portlet.init(config);
		} catch (PortletException e) {
			throw new RuntimeException(e);
		}
	}

	private MidgetPortletWindow createPortletWindow(Portlet portlet,
			PortletConfig config) {
		PortletDefinition definition = new MidgetPortletDefinition(
				portlet.getClass(), config);
		return new MidgetPortletWindow(definition);
	}
}
