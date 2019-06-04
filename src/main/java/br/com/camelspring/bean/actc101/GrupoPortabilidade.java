package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class GrupoPortabilidade {

    @JacksonXmlProperty(localName = "CodErro", isAttribute = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String codErroIdentdPartAdmdo;

    @JacksonXmlProperty(localName = "IdentdPartAdmdo")
    private String identdPartAdmdo;


    @JacksonXmlProperty(localName = "NumCtrlIF")
    private String numCtrlIF;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_IdentdContrto")
    private GrupoIdentificadorContrato grupoIdentificadorContrato;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_Cli")
    private GrupoCliente grupoCliente;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_PropPortldd")
    private GrupoProposta grupoProposta;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_EndCartaPortldd")
    private GrupoEndereco grupoEndereco;

    public String getCodErroIdentdPartAdmdo() {
        return codErroIdentdPartAdmdo;
    }

    public void setCodErroIdentdPartAdmdo(String codErroIdentdPartAdmdo) {
        this.codErroIdentdPartAdmdo = codErroIdentdPartAdmdo;
    }

    public String getIdentdPartAdmdo() {
        return identdPartAdmdo;
    }

    public void setIdentdPartAdmdo(String identdPartAdmdo) {
        this.identdPartAdmdo = identdPartAdmdo;
    }

    public String getNumCtrlIF() {
        return numCtrlIF;
    }

    public void setNumCtrlIF(String numCtrlIF) {
        this.numCtrlIF = numCtrlIF;
    }

    public GrupoIdentificadorContrato getGrupoIdentificadorContrato() {
        return grupoIdentificadorContrato;
    }

    public void setGrupoIdentificadorContrato(GrupoIdentificadorContrato grupoIdentificadorContrato) {
        this.grupoIdentificadorContrato = grupoIdentificadorContrato;
    }

    public GrupoCliente getGrupoCliente() {
        return grupoCliente;
    }

    public void setGrupoCliente(GrupoCliente grupoCliente) {
        this.grupoCliente = grupoCliente;
    }

    public GrupoProposta getGrupoProposta() {
        return grupoProposta;
    }

    public void setGrupoProposta(GrupoProposta grupoProposta) {
        this.grupoProposta = grupoProposta;
    }

    public GrupoEndereco getGrupoEndereco() {
        return grupoEndereco;
    }

    public void setGrupoEndereco(GrupoEndereco grupoEndereco) {
        this.grupoEndereco = grupoEndereco;
    }

    @Override
    public String toString() {
        return "GrupoPortabilidade{" +
                "identdPartAdmdo=" + identdPartAdmdo +
                ", numCtrlIF=" + numCtrlIF +
                ", grupoIdentificadorContrato=" + grupoIdentificadorContrato +
                ", grupoCliente=" + grupoCliente +
                ", grupoProposta=" + grupoProposta +
                ", grupoEndereco=" + grupoEndereco +
                '}';
    }
}
