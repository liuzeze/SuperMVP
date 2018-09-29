package com.lz.framecase.anotation;

import android.util.Log;
import android.widget.Toast;

import com.lz.MyInjectUtils;
import com.lz.framecase.logic.MyApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Locale;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-29       创建class
 */
@Aspect
public class MethodBehaviorAspect {
    private static final String TAG = "aspect_aby";

    @Pointcut("execution(@com.lz.framecase.anotation.ComponentInject * *(..))")
    public void firstMethodAnnotationBehavior() {
    }

    @Pointcut("execution(* com.lz.framecase.base.BaseFragment.injectComponent())")
    public void secondMethodAnnotationBehavior() {
    }

    @Around("firstMethodAnnotationBehavior()")
    public Object wavePointcutAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            // 类名
            String className = methodSignature.getDeclaringType().getSimpleName();
            // 方法名
            String methodName = methodSignature.getName();
            // 功能名
            ComponentInject behaviorTrace = methodSignature.getMethod()
                    .getAnnotation(ComponentInject.class);
            String value = behaviorTrace.value();

            Log.e(TAG, String.format("%s类中%s方法执行%s功能,耗时：%dms", className, methodName, value, duration));
            Toast.makeText(MyApplication.mApplication, String.format(Locale.CHINESE, "%s类中%s方法执行%s功能,耗时：%dms", className, methodName, value, duration), Toast.LENGTH_SHORT).show();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result;
    }

}
