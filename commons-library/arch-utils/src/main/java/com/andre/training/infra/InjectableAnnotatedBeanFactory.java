package com.andre.training.infra;

import com.andre.training.core.domain.stereotype.Injectable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Slf4j @Component
public class InjectableAnnotatedBeanFactory implements BeanFactoryPostProcessor {

    private final ClassPathScanningCandidateComponentProvider cpScanner;

    public InjectableAnnotatedBeanFactory() {
        this.cpScanner = new ClassPathScanningCandidateComponentProvider(false);
        cpScanner.addIncludeFilter(new AnnotationTypeFilter(Injectable.class));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        cpScanner.findCandidateComponents("com.andre.training.core").stream()
                .map(GenericBeanDefinition::new)
                .filter(bd -> bd.getBeanClassName() != null)
                .forEach(bd -> {
                    log.info("Registering bean of type {} at runtime", bd.getBeanClassName());
                    ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(bd.getBeanClassName(), bd);
                });
    }

}
