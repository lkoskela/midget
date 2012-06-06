package com.github.lkoskela.midget.end2end.portlets;

import static java.lang.Integer.toHexString;
import static java.lang.Math.floor;
import static java.lang.Math.random;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class BinaryGeneratingPortlet extends GenericPortlet {
	public byte[] content;

	public BinaryGeneratingPortlet() {
		this.content = new byte[2 ^ 4];
		for (int i = 0; i < content.length; i++) {
			content[i] = (byte) floor(random() * 256);
		}
	}

	public byte[] getContent() {
		return content;
	}

	public String getContentAsHex() {
		StringBuilder s = new StringBuilder("0x");
		for (byte b : content) {
			s.append(toHexString(b));
		}
		return s.toString();
	}

	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws PortletException, IOException {
		OutputStream out = response.getPortletOutputStream();
		out.write(getContent());
		out.close();
	}

	public static String asHex(byte[] bytes) {
		StringBuilder s = new StringBuilder("0x");
		for (byte b : bytes) {
			s.append(toHexString(b).toUpperCase());
		}
		return s.toString();
	}
}
