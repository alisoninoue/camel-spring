package br.com.camelspring.services;

import br.com.camelspring.Config;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@ActiveProfiles(value = "test")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisableJmx(true)
@SpringBootTest(classes = {Config.class},
        properties = {"camel.springboot.java-routes-include-pattern=**/ProdutoService*"})
public class ProdutoServiceTest {

    @Autowired
    private CamelContext context;

    @EndpointInject(uri = "{{file.from.pedidos}}")
    private ProducerTemplate template;

    @Test
    public void test() throws InterruptedException {
        NotifyBuilder notify = new NotifyBuilder(context).whenDone(1).create();

        template.sendBodyAndHeader("Hello World", Exchange.FILE_NAME, "hello.txt");

        assertTrue(notify.matchesMockWaitTime());

        File target = new File("saida/hello_novaTag.xml");
        assertTrue("File should have been moved", target.exists());

        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("<xpto>Hello World</xpto>", content);
    }

}