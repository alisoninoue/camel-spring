package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class GrupoPortabilidade {

    @JacksonXmlProperty(localName = "IdentdPartAdmdo")
    private Integer identdPartAdmdo;

    @JacksonXmlProperty(localName = "NumCtrlIF")
    private Integer numCtrlIF;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_IdentdContrto")
    private GrupoIdentificadorContrato grupoIdentificadorContrato;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_Cli")
    private GrupoCliente grupoCliente;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_PropPortldd")
    private GrupoProposta grupoProposta;

    @JacksonXmlProperty(localName = "Grupo_ACTC101_EndCartaPortldd")
    private GrupoEndereco grupoEndereco;

    public Integer getIdentdPartAdmdo() {
        return identdPartAdmdo;
    }

    public void setIdentdPartAdmdo(Integer identdPartAdmdo) {
        this.identdPartAdmdo = identdPartAdmdo;
    }

    public Integer getNumCtrlIF() {
        return numCtrlIF;
    }

    public void setNumCtrlIF(Integer numCtrlIF) {
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
}
