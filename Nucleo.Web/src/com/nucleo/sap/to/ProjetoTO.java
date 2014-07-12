/**
 * ProjetoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nucleo.sap.to;

public class ProjetoTO  implements java.io.Serializable {
    private java.lang.String CN;

    private int atividade;

    private java.util.Calendar dtFim;

    private java.util.Calendar dtInicio;

    private java.lang.String nome;

    private int setor;

    private java.math.BigDecimal vlOriginal;

    public ProjetoTO() {
    }

    public ProjetoTO(
           java.lang.String CN,
           int atividade,
           java.util.Calendar dtFim,
           java.util.Calendar dtInicio,
           java.lang.String nome,
           int setor,
           java.math.BigDecimal vlOriginal) {
           this.CN = CN;
           this.atividade = atividade;
           this.dtFim = dtFim;
           this.dtInicio = dtInicio;
           this.nome = nome;
           this.setor = setor;
           this.vlOriginal = vlOriginal;
    }


    /**
     * Gets the CN value for this ProjetoTO.
     * 
     * @return CN
     */
    public java.lang.String getCN() {
        return CN;
    }


    /**
     * Sets the CN value for this ProjetoTO.
     * 
     * @param CN
     */
    public void setCN(java.lang.String CN) {
        this.CN = CN;
    }


    /**
     * Gets the atividade value for this ProjetoTO.
     * 
     * @return atividade
     */
    public int getAtividade() {
        return atividade;
    }


    /**
     * Sets the atividade value for this ProjetoTO.
     * 
     * @param atividade
     */
    public void setAtividade(int atividade) {
        this.atividade = atividade;
    }


    /**
     * Gets the dtFim value for this ProjetoTO.
     * 
     * @return dtFim
     */
    public java.util.Calendar getDtFim() {
        return dtFim;
    }


    /**
     * Sets the dtFim value for this ProjetoTO.
     * 
     * @param dtFim
     */
    public void setDtFim(java.util.Calendar dtFim) {
        this.dtFim = dtFim;
    }


    /**
     * Gets the dtInicio value for this ProjetoTO.
     * 
     * @return dtInicio
     */
    public java.util.Calendar getDtInicio() {
        return dtInicio;
    }


    /**
     * Sets the dtInicio value for this ProjetoTO.
     * 
     * @param dtInicio
     */
    public void setDtInicio(java.util.Calendar dtInicio) {
        this.dtInicio = dtInicio;
    }


    /**
     * Gets the nome value for this ProjetoTO.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this ProjetoTO.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the setor value for this ProjetoTO.
     * 
     * @return setor
     */
    public int getSetor() {
        return setor;
    }


    /**
     * Sets the setor value for this ProjetoTO.
     * 
     * @param setor
     */
    public void setSetor(int setor) {
        this.setor = setor;
    }


    /**
     * Gets the vlOriginal value for this ProjetoTO.
     * 
     * @return vlOriginal
     */
    public java.math.BigDecimal getVlOriginal() {
        return vlOriginal;
    }


    /**
     * Sets the vlOriginal value for this ProjetoTO.
     * 
     * @param vlOriginal
     */
    public void setVlOriginal(java.math.BigDecimal vlOriginal) {
        this.vlOriginal = vlOriginal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProjetoTO)) return false;
        ProjetoTO other = (ProjetoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CN==null && other.getCN()==null) || 
             (this.CN!=null &&
              this.CN.equals(other.getCN()))) &&
            this.atividade == other.getAtividade() &&
            ((this.dtFim==null && other.getDtFim()==null) || 
             (this.dtFim!=null &&
              this.dtFim.equals(other.getDtFim()))) &&
            ((this.dtInicio==null && other.getDtInicio()==null) || 
             (this.dtInicio!=null &&
              this.dtInicio.equals(other.getDtInicio()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            this.setor == other.getSetor() &&
            ((this.vlOriginal==null && other.getVlOriginal()==null) || 
             (this.vlOriginal!=null &&
              this.vlOriginal.equals(other.getVlOriginal())));
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
        if (getCN() != null) {
            _hashCode += getCN().hashCode();
        }
        _hashCode += getAtividade();
        if (getDtFim() != null) {
            _hashCode += getDtFim().hashCode();
        }
        if (getDtInicio() != null) {
            _hashCode += getDtInicio().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        _hashCode += getSetor();
        if (getVlOriginal() != null) {
            _hashCode += getVlOriginal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProjetoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "ProjetoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "CN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atividade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "atividade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "dtFim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "dtInicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "setor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vlOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://to.sap.nucleo.com", "vlOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
