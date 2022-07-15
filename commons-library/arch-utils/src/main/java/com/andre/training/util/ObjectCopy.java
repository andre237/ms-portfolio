package com.andre.training.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class ObjectCopy {

    public static <T> T copyProperties(Object source, T target, String... ignore) {
        BeanUtils.copyProperties(source, target, ignore);
        return target;
    }

    public static <T> T copyNonNullProperties(Object source, T target, String... ignore) {
        String[] nullPropertyNames = ArrayUtils.addAll(getNullPropertyNames(source), ignore);
        BeanUtils.copyProperties(source, target, nullPropertyNames);
        return target;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }


}
