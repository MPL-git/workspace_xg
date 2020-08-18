package com.jf.common.ext.util;

import org.springframework.core.type.ClassMetadata;

import java.lang.annotation.Annotation;

/**
 * @author luoyb
 * Created on 2019/11/18
 */
public final class AnnotationHelper {

    /**
     * 根据ClassMetadata获取类对象的指定注解，
     * 先从当前类上找，再从父类上找。
     */
    public static <T extends Annotation> T findClassOrSuperAnnotation(ClassMetadata classMetadata, Class<T> annotationClass) {
        Class<?> targetClass;
        T targetAnnotation;
        try {
            targetClass = classMetadata.getClass().getClassLoader().loadClass(classMetadata.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            targetAnnotation = targetClass.getAnnotation(annotationClass);
            if (targetAnnotation != null) return targetAnnotation;
            targetClass = targetClass.getSuperclass();
            if (Object.class.equals(targetClass)) return null;
        }
    }

    private AnnotationHelper() {
    }

}
