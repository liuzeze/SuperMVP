package com.lz.fram.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2018/7/30       刘泽
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AttachView {
}
