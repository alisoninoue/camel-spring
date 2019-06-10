package br.com.camelspring.bean;

import br.com.camelspring.bean.actc101.Actc101;
import br.com.camelspring.bean.actc101FromXsd.ACTCDOCComplexType;
import org.apache.camel.Body;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actc101Processor {

    public void teste(@Body Actc101 actc101 ){
        System.out.println("bean method teste called!");

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
