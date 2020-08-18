package com.jf.common.base;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        try {
          String[] databaseCnf=new BaseConfigProperties().getDatabaseConfig();
          props.setProperty("jdbc.url", databaseCnf[0]);
          props.setProperty("jdbc.username", databaseCnf[1]);
          props.setProperty("jdbc.password", databaseCnf[2]);
          super.processProperties(beanFactory, props);
      } catch (Exception e) {
          e.printStackTrace();
          throw new BeanInitializationException(e.getMessage());
      }
	}
}
