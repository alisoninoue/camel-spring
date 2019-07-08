package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.ACTCDOCComplexType;
import br.com.camelspring.bean.actc104FromXsd.GrupoACTC104PortlddComplexType;
import org.apache.camel.Body;

import java.util.List;

public class Actc104Processor {

    public void testeJaxb(@Body ACTCDOCComplexType actcdocComplexType) {
        System.out.println(actcdocComplexType);
    }

    public List<GrupoACTC104PortlddComplexType> testeSplit(@Body ACTCDOCComplexType actcdocComplexType) {
        return actcdocComplexType.getSISARQ().getACTC104().getGrupoACTC104Portldd();
    }
}