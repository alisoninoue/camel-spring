package br.com.camelspring.bean;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

import java.io.Serializable;

@Link
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @DataField(pos = 11, length = 15, trim = true, align = "L")
    private String name;

    @DataField(pos = 12, length = 8, trim = true, align = "L")
    private String cep;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
