package com.nucleo.endereco.bo;

public class EnderecoBOProxy implements com.nucleo.endereco.bo.EnderecoBO {
  private String _endpoint = null;
  private com.nucleo.endereco.bo.EnderecoBO enderecoBO = null;
  
  public EnderecoBOProxy() {
    _initEnderecoBOProxy();
  }
  
  public EnderecoBOProxy(String endpoint) {
    _endpoint = endpoint;
    _initEnderecoBOProxy();
  }
  
  private void _initEnderecoBOProxy() {
    try {
      enderecoBO = (new com.nucleo.endereco.bo.EnderecoBOServiceLocator()).getEnderecoBO();
      if (enderecoBO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)enderecoBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)enderecoBO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (enderecoBO != null)
      ((javax.xml.rpc.Stub)enderecoBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.nucleo.endereco.bo.EnderecoBO getEnderecoBO() {
    if (enderecoBO == null)
      _initEnderecoBOProxy();
    return enderecoBO;
  }
  
  public com.nucleo.endereco.to.CidadeTO[] getCidadesPorUF(java.lang.String uf) throws java.rmi.RemoteException{
    if (enderecoBO == null)
      _initEnderecoBOProxy();
    return enderecoBO.getCidadesPorUF(uf);
  }
  
  
}