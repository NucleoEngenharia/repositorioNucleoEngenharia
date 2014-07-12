/**
 * BairroTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.endereco.to;

public class BairroTO  implements java.io.Serializable {
    private com.nucleo.endereco.to.CidadeTO cidade;

    private com.nucleo.endereco.to.EnderecoTO[] enderecos;

    private int id;

    private int idCidade;

    private java.lang.String nome;

    public BairroTO() {
    }

    public BairroTO(
           com.nucleo.endereco.to.CidadeTO cidade,
           com.nucleo.endereco.to.EnderecoTO[] enderecos,
           int id,
           int idCidade,
           java.lang.String nome) {
           this.cidade = cidade;
           this.enderecos = enderecos;
           this.id = id;
           this.idCidade = idCidade;
           this.nome = nome;
    }


    /**
     * Gets the cidade value for this BairroTO.
     * 
     * @return cidade
     */
    public com.nucleo.endereco.to.CidadeTO getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this BairroTO.
     * 
     * @param cidade
     */
    public void setCidade(com.nucleo.endereco.to.CidadeTO cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the enderecos value for this BairroTO.
     * 
     * @return enderecos
     */
    public com.nucleo.endereco.to.EnderecoTO[] getEnderecos() {
        return enderecos;
    }


    /**
     * Sets the enderecos value for this BairroTO.
     * 
     * @param enderecos
     */
    public void setEnderecos(com.nucleo.endereco.to.EnderecoTO[] enderecos) {
        this.enderecos = enderecos;
    }


    /**
     * Gets the id value for this BairroTO.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this BairroTO.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the idCidade value for this BairroTO.
     * 
     * @return idCidade
     */
    public int getIdCidade() {
        return idCidade;
    }


    /**
     * Sets the idCidade value for this BairroTO.
     * 
     * @param idCidade
     */
    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }


    /**
     * Gets the nome value for this BairroTO.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this BairroTO.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BairroTO)) return false;
        BairroTO other = (BairroTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.enderecos==null && other.getEnderecos()==null) || 
             (this.enderecos!=null &&
              java.util.Arrays.equals(this.enderecos, other.getEnderecos()))) &&
            this.id == other.getId() &&
            this.idCidade == other.getIdCidade() &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome())));
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
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        if (getEnderecos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnderecos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnderecos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getId();
        _hashCode += getIdCidade();
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BairroTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "BairroTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "CidadeTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enderecos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "enderecos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "EnderecoTO"));
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
        elemField.setFieldName("idCidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "idCidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.endereco.nucleo.com", "nome"));
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
