/**
 * EnderecoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.endereco.to;

public class EnderecoTO  implements java.io.Serializable {
    private com.nucleo.endereco.to.BairroTO bairro;

    private java.lang.String cep;

    private com.nucleo.endereco.to.CidadeTO cidade;

    private int idBairro;

    private int idCidade;

    private java.lang.String logradouro;

    public EnderecoTO() {
    }

    public EnderecoTO(
           com.nucleo.endereco.to.BairroTO bairro,
           java.lang.String cep,
           com.nucleo.endereco.to.CidadeTO cidade,
           int idBairro,
           int idCidade,
           java.lang.String logradouro) {
           this.bairro = bairro;
           this.cep = cep;
           this.cidade = cidade;
           this.idBairro = idBairro;
           this.idCidade = idCidade;
           this.logradouro = logradouro;
    }


    /**
     * Gets the bairro value for this EnderecoTO.
     * 
     * @return bairro
     */
    public com.nucleo.endereco.to.BairroTO getBairro() {
        return bairro;
    }


    /**
     * Sets the bairro value for this EnderecoTO.
     * 
     * @param bairro
     */
    public void setBairro(com.nucleo.endereco.to.BairroTO bairro) {
        this.bairro = bairro;
    }


    /**
     * Gets the cep value for this EnderecoTO.
     * 
     * @return cep
     */
    public java.lang.String getCep() {
        return cep;
    }


    /**
     * Sets the cep value for this EnderecoTO.
     * 
     * @param cep
     */
    public void setCep(java.lang.String cep) {
        this.cep = cep;
    }


    /**
     * Gets the cidade value for this EnderecoTO.
     * 
     * @return cidade
     */
    public com.nucleo.endereco.to.CidadeTO getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this EnderecoTO.
     * 
     * @param cidade
     */
    public void setCidade(com.nucleo.endereco.to.CidadeTO cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the idBairro value for this EnderecoTO.
     * 
     * @return idBairro
     */
    public int getIdBairro() {
        return idBairro;
    }


    /**
     * Sets the idBairro value for this EnderecoTO.
     * 
     * @param idBairro
     */
    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }


    /**
     * Gets the idCidade value for this EnderecoTO.
     * 
     * @return idCidade
     */
    public int getIdCidade() {
        return idCidade;
    }


    /**
     * Sets the idCidade value for this EnderecoTO.
     * 
     * @param idCidade
     */
    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }


    /**
     * Gets the logradouro value for this EnderecoTO.
     * 
     * @return logradouro
     */
    public java.lang.String getLogradouro() {
        return logradouro;
    }


    /**
     * Sets the logradouro value for this EnderecoTO.
     * 
     * @param logradouro
     */
    public void setLogradouro(java.lang.String logradouro) {
        this.logradouro = logradouro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnderecoTO)) return false;
        EnderecoTO other = (EnderecoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bairro==null && other.getBairro()==null) || 
             (this.bairro!=null &&
              this.bairro.equals(other.getBairro()))) &&
            ((this.cep==null && other.getCep()==null) || 
             (this.cep!=null &&
              this.cep.equals(other.getCep()))) &&
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            this.idBairro == other.getIdBairro() &&
            this.idCidade == other.getIdCidade() &&
            ((this.logradouro==null && other.getLogradouro()==null) || 
             (this.logradouro!=null &&
              this.logradouro.equals(other.getLogradouro())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBairro() != null) {
            _hashCode += getBairro().hashCode();
        }
        if (getCep() != null) {
            _hashCode += getCep().hashCode();
        }
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        _hashCode += getIdBairro();
        _hashCode += getIdCidade();
        if (getLogradouro() != null) {
            _hashCode += getLogradouro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnderecoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "EnderecoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bairro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "bairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "BairroTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "cep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "CidadeTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idBairro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "idBairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "idCidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "logradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
