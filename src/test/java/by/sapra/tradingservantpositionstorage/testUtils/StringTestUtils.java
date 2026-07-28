package by.sapra.tradingservantpositionstorage.testUtils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

public class StringTestUtils {
    public static String readStringFromResources(String path) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(MessageFormat.format("classpath:{0}", path));

        try(InputStreamReader isr = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(isr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
