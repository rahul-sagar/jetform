package io.jetform.util;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ApplicationProperties {

    private Properties properties;

    public ApplicationProperties(String fileName) {
        // application.properties located at src/main/resource
        //Resource resource = new ClassPathResource("/application.properties");
    	Resource resource = new ClassPathResource(fileName.concat(".properties"));
        try {
            this.properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
    
    public String[] getOptions(String propertyValues) {
    	return propertyValues.split(",");
    }
}