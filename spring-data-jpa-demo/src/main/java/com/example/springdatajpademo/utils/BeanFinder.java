package com.example.springdatajpademo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

/**
 * BeanFinder
 */
@Component
public class BeanFinder implements ApplicationContextAware {
  
  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  public static ApplicationContext getContext() {
    return context;
  }

  @SuppressWarnings("unchecked")
   public static <T> T getBean(ParameterizedTypeReference<T> ref) {
    final String[] names = context.getBeanNamesForType(ResolvableType.forType(ref));
    if (names.length < 1) {
      return null;
    }
    return (T) context.getBean(names[0]);
  }
}
