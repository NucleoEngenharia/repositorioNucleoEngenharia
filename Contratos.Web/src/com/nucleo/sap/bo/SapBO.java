/**
 * SapBO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.sap.bo;

public interface SapBO extends java.rmi.Remote {
    public com.nucleo.sap.to.ProjetoTO[] getProjetos() throws java.rmi.RemoteException;
    public com.nucleo.sap.to.ImpostoTO[] getImpostos() throws java.rmi.RemoteException;
}
