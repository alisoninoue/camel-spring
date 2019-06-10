package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.ACTCDOCComplexType;
import org.apache.camel.Body;

public class Actc104Processor {

    public void testeJaxb(@Body ACTCDOCComplexType actcdocComplexType){
        System.out.println(actcdocComplexType);
    }

}
