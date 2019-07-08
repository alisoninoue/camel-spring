package br.com.camelspring.bean;

import br.com.camelspring.bean.actc101.Actc101;
import br.com.camelspring.bean.actc101FromXsd.*;
import org.apache.camel.Body;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Actc101Processor {

    public void teste(@Body Actc101 actc101 ){
        System.out.println("bean method teste called!");

    }

    public ACTCDOCComplexType processa(@Body List<Player> players ){
        System.out.println("bean method teste called!");

        ObjectFactory factory = new ObjectFactory();

        CNPJCPFCodErro cnpjcpfCodErro = factory.createCNPJCPFCodErro();
        cnpjcpfCodErro.setValue("132132131");

        TelefoneCodErro telefoneCodErro = factory.createTelefoneCodErro();
        telefoneCodErro.setValue("123123213");

        BCARQComplexType bcarqComplexType = factory.createBCARQComplexType();
        bcarqComplexType.setDtHrArq(LocalDateTime.now());

        GrupoIdentdContrtoComplexType grupoIdentdContrtoComplexType = factory.createGrupoIdentdContrtoComplexType();

        GrupoACTCCliComplexTypeCodErro grupoACTCCliComplexTypeCodErro = factory.createGrupoACTCCliComplexTypeCodErro();
        grupoACTCCliComplexTypeCodErro.setCNPJCPFCli(cnpjcpfCodErro);
        grupoACTCCliComplexTypeCodErro.setTelCli(telefoneCodErro);


        GrupoACTC10XIdentdContrtoComplexType actc10XIdentdContrtoComplexType = factory.createGrupoACTC10XIdentdContrtoComplexType();


        GrupoACTC10XPropPortlddComplexType grupoACTC10XPropPortlddComplexType = factory.createGrupoACTC10XPropPortlddComplexType();

        DataCodErro dtContrOp = factory.createDataCodErro();
        dtContrOp.setValue(players.get(0).getRetirementDate());
        grupoACTC10XPropPortlddComplexType.setDtContrOp(dtContrOp);

        DataCodErro dtRefSaldDevdrContb = factory.createDataCodErro();
        dtRefSaldDevdrContb.setValue(LocalDate.now());
        grupoACTC10XPropPortlddComplexType.setDtRefSaldDevdrContb(dtRefSaldDevdrContb);

        ValorCodErro valorCodErro = factory.createValorCodErro();
        valorCodErro.setValue(players.get(0).getStrikeRate());
        grupoACTC10XPropPortlddComplexType.setVlrFaceParclContrto(valorCodErro);

        GrupoACTC101PortlddComplexType grupoACTC101PortlddCT = factory.createGrupoACTC101PortlddComplexType();
        grupoACTC101PortlddCT.setIdentdPartAdmdo("teste");
        grupoACTC101PortlddCT.setNumCtrlIF("1234");
        grupoACTC101PortlddCT.setGrupoACTC101Cli(grupoACTCCliComplexTypeCodErro);
        grupoACTC101PortlddCT.setGrupoACTC101PropPortldd(grupoACTC10XPropPortlddComplexType);

        ACTC101ComplexType actc101ComplexType = factory.createACTC101ComplexType();
        List<GrupoACTC101PortlddComplexType> grupoACTC101Portldd = actc101ComplexType.getGrupoACTC101Portldd();
        grupoACTC101Portldd.add(grupoACTC101PortlddCT);

        SISARQComplexType sisarqComplexType = factory.createSISARQComplexType();
        sisarqComplexType.setACTC101(actc101ComplexType);

        ACTCDOCComplexType actcdocComplexType = factory.createACTCDOCComplexType();
        actcdocComplexType.setSISARQ(sisarqComplexType);
        actcdocComplexType.setBCARQ(bcarqComplexType);

        return actcdocComplexType;

    }

    public void testeJaxb(@Body ACTCDOCComplexType actcdocComplexType){
        System.out.println(actcdocComplexType);
    }

    public void testeXpath (@Body String xml){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xml);

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            String name = getEmployeeNameById(doc, xpath, 4);
            System.out.println("Employee Name with ID 4: " + name);

            List<String> names = getEmployeeNameWithAge(doc, xpath, 30);
            System.out.println("Employees with 'age>30' are:" + Arrays.toString(names.toArray()));

            List<String> femaleEmps = getFemaleEmployeesName(doc, xpath);
            System.out.println("Female Employees names are:" +
                    Arrays.toString(femaleEmps.toArray()));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }
    private static List<String> getFemaleEmployeesName(Document doc, XPath xpath) {
        List<String> list = new ArrayList<>();
        try {
            //create XPathExpression object
            XPathExpression expr =
                    xpath.compile("/Employees/Employee[gender='Female']/name/text()");
            //evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static List<String> getEmployeeNameWithAge(Document doc, XPath xpath, int age) {
        List<String> list = new ArrayList<>();
        try {
            XPathExpression expr =
                    xpath.compile("/Employees/Employee[age>" + age + "]/name/text()");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static String getEmployeeNameById(Document doc, XPath xpath, int id) {
        String name = null;
        try {
            XPathExpression expr =
                    xpath.compile("/ACTC101/Grupo_ACTC101_Portldd/IdentdPartAdmdo/name/text()");
            name = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return name;
    }
}
