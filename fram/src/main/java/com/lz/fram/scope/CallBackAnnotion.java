package com.lz.fram.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-29       创建class
 */
@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CallBackAnnotion {
    String value();
}
