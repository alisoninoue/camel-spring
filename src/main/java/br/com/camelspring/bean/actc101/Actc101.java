package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ACTC101")
public class Actc101 {
    @JacksonXmlProperty(localName = "Grupo_ACTC101_Portldd")
    private GrupoPortabilidade grupoPortabilidade;

    public GrupoPortabilidade getGrupoPortabilidade() {
        return grupoPortabilidade;
    }

    public void setGrupoPortabilidade(GrupoPortabilidade grupoPortabilidade) {
        this.grupoPortabilidade = grupoPortabilidade;
    }
}
