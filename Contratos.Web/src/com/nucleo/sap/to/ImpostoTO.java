/**
 * ImpostoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.sap.to;

public class ImpostoTO  implements java.io.Serializable {
    private java.lang.String inativo;

    private java.math.BigDecimal taxa;

    private java.lang.String wtCode;

    private java.lang.String wtName;

    public ImpostoTO() {
    }

    public ImpostoTO(
           java.lang.String inativo,
           java.math.BigDecimal taxa,
           java.lang.String wtCode,
           java.lang.String wtName) {
           this.inativo = inativo;
           this.taxa = taxa;
           this.wtCode = wtCode;
           this.wtName = wtName;
    }


    /**
     * Gets the inativo value for this ImpostoTO.
     * 
     * @return inativo
     */
    public java.lang.String getInativo() {
        return inativo;
    }


    /**
     * Sets the inativo value for this ImpostoTO.
     * 
     * @param inativo
     */
    public void setInativo(java.lang.String inativo) {
        this.inativo = inativo;
    }


    /**
     * Gets the taxa value for this ImpostoTO.
     * 
     * @return taxa
     */
    public java.math.BigDecimal getTaxa() {
        return taxa;
    }


    /**
     * Sets the taxa value for this ImpostoTO.
     * 
     * @param taxa
     */
    public void setTaxa(java.math.BigDecimal taxa) {
        this.taxa = taxa;
    }


    /**
     * Gets the wtCode value for this ImpostoTO.
     * 
     * @return wtCode
     */
    public java.lang.String getWtCode() {
        return wtCode;
    }


    /**
     * Sets the wtCode value for this ImpostoTO.
     * 
     * @param wtCode
     */
    public void setWtCode(java.lang.String wtCode) {
        this.wtCode = wtCode;
    }


    /**
     * Gets the wtName value for this ImpostoTO.
     * 
     * @return wtName
     */
    public java.lang.String getWtName() {
        return wtName;
    }


    /**
     * Sets the wtName value for this ImpostoTO.
     * 
     * @param wtName
     */
    public void setWtName(java.lang.String wtName) {
        this.wtName = wtName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImpostoTO)) return false;
        ImpostoTO other = (ImpostoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.inativo==null && other.getInativo()==null) || 
             (this.inativo!=null &&
              this.inativo.equals(other.getInativo()))) &&
            ((this.taxa==null && other.getTaxa()==null) || 
             (this.taxa!=null &&
              this.taxa.equals(other.getTaxa()))) &&
            ((this.wtCode==null && other.getWtCode()==null) || 
             (this.wtCode!=null &&
              this.wtCode.equals(other.getWtCode()))) &&
            ((this.wtName==null && other.getWtName()==null) || 
             (this.wtName!=null &&
              this.wtName.equals(other.getWtName())));
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
        if (getInativo() != null) {
            _hashCode += getInativo().hashCode();
        }
        if (getTaxa() != null) {
            _hashCode += getTaxa().hashCode();
        }
        if (getWtCode() != null) {
            _hashCode += getWtCode().hashCode();
        }
        if (getWtName() != null) {
            _hashCode += getWtName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImpostoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "ImpostoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inativo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "inativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "taxa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wtCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "wtCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wtName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "wtName"));
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
