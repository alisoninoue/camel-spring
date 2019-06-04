package br.com.camelspring.bean.actc101;

import org.apache.camel.Body;

public class Actc101Processor {

    public void teste(@Body Actc101 actc101 ){
        System.out.println("bean method teste called!");

    }
}
