package br.com.camelspring.util;

import br.com.camelspring.Boot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.List;
import java.util.Map;

public class ValidaDiaUtil {

    public static void leituraArquivo() {
        try {
            ApplicationHome home = new ApplicationHome(Boot.class);
            String path = home.getDir().getPath() + File.separator + "feriados.yml";
            System.out.println("path = " + path);
            String pathName = ValidaDiaUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            System.out.println("pathName = " + pathName);
            pathName = URLDecoder.decode(pathName, "UTF-8");
            System.out.println("pathName = " + pathName);
            pathName = pathName.substring(1,pathName.lastIndexOf("/"));
            System.out.println("pathName = " + pathName);

            String path1 = ValidaDiaUtil.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI().getPath();
            System.out.println("path1 = " + path1);

            File inputStream = null;
                inputStream = new File(path);
//                inputStream = new File(pathName + "feriados.yml");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            Map<String, List<String>> map = mapper.readValue(inputStream, Map.class);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        leituraArquivo();
    }
}
