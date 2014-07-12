/**
 * CidadeTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.endereco.to;

public class CidadeTO  implements java.io.Serializable {
    private com.nucleo.endereco.to.BairroTO[] bairros;

    private int id;

    private java.lang.String nome;

    private java.lang.String uf;

    public CidadeTO() {
    }

    public CidadeTO(
           com.nucleo.endereco.to.BairroTO[] bairros,
           int id,
           java.lang.String nome,
           java.lang.String uf) {
           this.bairros = bairros;
           this.id = id;
           this.nome = nome;
           this.uf = uf;
    }


    /**
     * Gets the bairros value for this CidadeTO.
     * 
     * @return bairros
     */
    public com.nucleo.endereco.to.BairroTO[] getBairros() {
        return bairros;
    }


    /**
     * Sets the bairros value for this CidadeTO.
     * 
     * @param bairros
     */
    public void setBairros(com.nucleo.endereco.to.BairroTO[] bairros) {
        this.bairros = bairros;
    }


    /**
     * Gets the id value for this CidadeTO.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this CidadeTO.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the nome value for this CidadeTO.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this CidadeTO.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the uf value for this CidadeTO.
     * 
     * @return uf
     */
    public java.lang.String getUf() {
        return uf;
    }


    /**
     * Sets the uf value for this CidadeTO.
     * 
     * @param uf
     */
    public void setUf(java.lang.String uf) {
        this.uf = uf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CidadeTO)) return false;
        CidadeTO other = (CidadeTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bairros==null && other.getBairros()==null) || 
             (this.bairros!=null &&
              java.util.Arrays.equals(this.bairros, other.getBairros()))) &&
            this.id == other.getId() &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.uf==null && other.getUf()==null) || 
             (this.uf!=null &&
              this.uf.equals(other.getUf())));
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
        if (getBairros() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBairros());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBairros(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getId();
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getUf() != null) {
            _hashCode += getUf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CidadeTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "CidadeTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bairros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "bairros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "BairroTO"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://bo.endereco.nucleo.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "uf"));
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
