package com.lz.framecase.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-29       创建class
 */
@Target({TYPE, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
//@Retention(RetentionPolicy.RUNTIME)
public @interface ClassRuntime {
}
