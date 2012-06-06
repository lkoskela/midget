package com.github.lkoskela.midget.impl;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.pluto.container.om.portlet.Description;
import org.apache.pluto.container.om.portlet.SecurityRoleRef;

public class MidgetSecurityRoleRef implements
		SecurityRoleRef {
	private final String roleName;
	private String roleLink;
	private List<Description> descriptions = new ArrayList<Description>();

	MidgetSecurityRoleRef(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleLink(String roleLink) {
		this.roleLink = roleLink;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getRoleLink() {
		return roleLink;
	}

	public List<? extends Description> getDescriptions() {
		return unmodifiableList(descriptions);
	}

	public Description getDescription(Locale locale) {
		for (Description d : getDescriptions()) {
			if (locale.equals(d.getLocale())) {
				return d;
			}
		}
		return null;
	}

	public Description addDescription(String lang) {
		Description description = new MidgetDescription(lang);
		descriptions.add(description);
		return description;
	}
}