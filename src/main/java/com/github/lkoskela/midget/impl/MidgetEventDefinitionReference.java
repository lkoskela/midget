package com.github.lkoskela.midget.impl;

import javax.xml.namespace.QName;

import org.apache.pluto.container.om.portlet.EventDefinitionReference;

public class MidgetEventDefinitionReference implements
		EventDefinitionReference {
	private final QName qname;

	MidgetEventDefinitionReference(QName qname) {
		this.qname = qname;
	}

	public QName getQName() {
		return qname;
	}

	public String getName() {
		return qname.getLocalPart();
	}

	public QName getQualifiedName(String defaultNamespace) {
		return getQName();
	}
}