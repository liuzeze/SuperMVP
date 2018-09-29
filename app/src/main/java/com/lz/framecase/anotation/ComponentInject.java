package com.lz.framecase.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-29       创建class
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentInject {
    String value();
}
