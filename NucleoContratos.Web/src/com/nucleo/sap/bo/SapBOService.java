/**
 * SapBOService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.sap.bo;

public interface SapBOService extends javax.xml.rpc.Service {
    public java.lang.String getSapBOAddress();

    public com.nucleo.sap.bo.SapBO getSapBO() throws javax.xml.rpc.ServiceException;

    public com.nucleo.sap.bo.SapBO getSapBO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
