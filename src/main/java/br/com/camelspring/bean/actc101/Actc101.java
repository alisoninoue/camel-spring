package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Arrays;
import java.util.List;

@JacksonXmlRootElement(localName = "ACTC101")
public class Actc101 {
    @JacksonXmlProperty(localName = "Grupo_ACTC101_Portldd")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GrupoPortabilidade> grupoPortabilidade;

    public List<GrupoPortabilidade> getGrupoPortabilidade() {
        return grupoPortabilidade;
    }

    public void setGrupoPortabilidade(List<GrupoPortabilidade> grupoPortabilidade) {
        this.grupoPortabilidade = grupoPortabilidade;
    }

    @Override
    public String toString() {
        return "Actc101{" +
                "grupoPortabilidade=" + grupoPortabilidade +
                '}';
    }
}
