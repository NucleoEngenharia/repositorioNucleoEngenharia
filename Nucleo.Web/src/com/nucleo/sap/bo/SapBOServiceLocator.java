/**
 * SapBOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.sap.bo;

public class SapBOServiceLocator extends org.apache.axis.client.Service implements com.nucleo.sap.bo.SapBOService {

    public SapBOServiceLocator() {
    }


    public SapBOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SapBOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SapBO
    private java.lang.String SapBO_address = "http://179.184.226.66:8181/Nucleo.SAP.ws/services/SapBO";
  //  private java.lang.String SapBO_address = "http://nucleosede.no-ip.org:8181/Nucleo.SAP.ws/services/SapBO";
    
    public java.lang.String getSapBOAddress() {
        return SapBO_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SapBOWSDDServiceName = "SapBO";

    public java.lang.String getSapBOWSDDServiceName() {
        return SapBOWSDDServiceName;
    }

    public void setSapBOWSDDServiceName(java.lang.String name) {
        SapBOWSDDServiceName = name;
    }

    public com.nucleo.sap.bo.SapBO getSapBO() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SapBO_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSapBO(endpoint);
    }

    public com.nucleo.sap.bo.SapBO getSapBO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nucleo.sap.bo.SapBOSoapBindingStub _stub = new com.nucleo.sap.bo.SapBOSoapBindingStub(portAddress, this);
            _stub.setPortName(getSapBOWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSapBOEndpointAddress(java.lang.String address) {
        SapBO_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nucleo.sap.bo.SapBO.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nucleo.sap.bo.SapBOSoapBindingStub _stub = new com.nucleo.sap.bo.SapBOSoapBindingStub(new java.net.URL(SapBO_address), this);
                _stub.setPortName(getSapBOWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SapBO".equals(inputPortName)) {
            return getSapBO();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bo.sap.nucleo.com", "SapBOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bo.sap.nucleo.com", "SapBO"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SapBO".equals(portName)) {
            setSapBOEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
