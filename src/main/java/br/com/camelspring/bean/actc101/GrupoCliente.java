package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@JacksonXmlRootElement(localName = "Grupo_ACTC101_Cli")
@JsonPropertyOrder({"tipo", "cnpjCpf", "nome", "telefone", "email"})
public class GrupoCliente {

    @JacksonXmlProperty(localName = "TpCli")
    private String tipo = "F";

    @JacksonXmlProperty(localName = "CNPJ_CPFCli")
    private String cnpjCpf;

    @JacksonXmlProperty(localName = "NomCli")
    private String nome;

    @JacksonXmlProperty(localName = "TelCli")
    private String telefone;

    @JacksonXmlProperty(localName = "EmailCli")
    @JsonInclude(Include.NON_NULL)
    private String email;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GrupoCliente{" +
                "tipo='" + tipo + '\'' +
                ", cnpjCpf=" + cnpjCpf +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
