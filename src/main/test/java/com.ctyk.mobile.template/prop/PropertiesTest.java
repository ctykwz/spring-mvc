package com.ctyk.mobile.template.prop;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * @author Ctyk on 2017/11/15
 */
public class PropertiesTest {

    @Test
    public void test() throws FileNotFoundException, ConfigurationException {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties().setFileName("redis.properties"));
        try {
            Configuration config = builder.getConfiguration();
            Iterator iterator = config.getKeys();
            while (iterator.hasNext()) {
                Object key = iterator.next();
                System.out.println(key + "\t" + config.getString((String) key));
            }
            System.out.println(config.getInt("redis.soTimeout"));
        } catch (ConfigurationException cex) {
            System.out.println(cex.getMessage());
        }
    }
}
