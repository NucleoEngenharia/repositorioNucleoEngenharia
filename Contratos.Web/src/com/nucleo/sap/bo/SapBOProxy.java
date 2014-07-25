package com.nucleo.sap.bo;

public class SapBOProxy implements com.nucleo.sap.bo.SapBO {
  private String _endpoint = null;
  private com.nucleo.sap.bo.SapBO sapBO = null;
  
  public SapBOProxy() {
    _initSapBOProxy();
  }
  
  public SapBOProxy(String endpoint) {
    _endpoint = endpoint;
    _initSapBOProxy();
  }
  
  private void _initSapBOProxy() {
    try {
      sapBO = (new com.nucleo.sap.bo.SapBOServiceLocator()).getSapBO();
      if (sapBO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sapBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sapBO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sapBO != null)
      ((javax.xml.rpc.Stub)sapBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.nucleo.sap.bo.SapBO getSapBO() {
    if (sapBO == null)
      _initSapBOProxy();
    return sapBO;
  }
  
  public com.nucleo.sap.to.ProjetoTO[] getProjetos() throws java.rmi.RemoteException{
    if (sapBO == null)
      _initSapBOProxy();
    return sapBO.getProjetos();
  }
  
  public com.nucleo.sap.to.ImpostoTO[] getImpostos() throws java.rmi.RemoteException{
    if (sapBO == null)
      _initSapBOProxy();
    return sapBO.getImpostos();
  }
  
  
}