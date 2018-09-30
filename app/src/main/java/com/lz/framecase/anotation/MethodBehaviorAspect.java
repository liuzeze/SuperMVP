package com.lz.framecase.anotation;

import android.app.Activity;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.lz.framecase.BuildConfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * -----------作者----------日期----------变更内容-----
 * -          刘泽      2018-09-29       创建class
 */
@Aspect
public class MethodBehaviorAspect {
    private static final String TAGCYCLE = "debug_cycle--->";
    private static final String TAGTIME = "debug_runtime--->";
    private static final String TAGDOT = "debug_dot--->";

    /**
     * 埋点日志
     */
    @Pointcut("execution(@com.lz.framecase.anotation.MethodDot * *(..))")
    public void methodDotCut() {
    }

    @After("methodDotCut()")
    public void methodDotBefore(JoinPoint joinPoint) throws Throwable {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            MethodDot behaviorTrace = methodSignature.getMethod()
                    .getAnnotation(MethodDot.class);
            String value = behaviorTrace.value();
            Log.d(TAGDOT, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * activitu 生命周期切入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* android.app.Application.ActivityLifecycleCallbacks.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        try {
            if (BuildConfig.DEBUG) {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                //参数值
                String className = "";
                Object[] parameterValues = joinPoint.getArgs();
                for (Object parameterValue : parameterValues) {
                    if (parameterValue instanceof Activity) {
                        className = ((Activity) parameterValue).getClass().getSimpleName();
                        break;
                    }
                }
                Log.d(TAGCYCLE, "  classname---" + className + "---methodName---" + methodSignature.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fragment生命周期切入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* android.support.v4.app.Fragment.on**(..))")
    public void onFragmentMethodBefore(JoinPoint joinPoint) throws Throwable {
        try {
            if (BuildConfig.DEBUG) {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                String className = methodSignature.getDeclaringType().getSimpleName();
                Log.d(TAGCYCLE, "  classname---" + className + "---methodName---" + methodSignature.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 自定义切点
     */
    @Pointcut("within(@com.lz.framecase.anotation.ClassRuntime *)")
    public void withinAnnotatedClass() {
    }

    /**
     * 匿名类判断 此方法会统计匿名类  如果不需要刻意用下面的方法替换
     *
     * @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
     * <p>
     * synthetic在字节码中代表含义是匿名类标识  !synthetic 非匿名类
     */
    @Pointcut("execution(* *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {
    }

    /**
     * 构造函数切入
     *
     * @Pointcut("execution(!synthetic*.new(..)) && withinAnnotatedClass()")  不包括匿名类
     */
    @Pointcut("execution(*.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {
    }

    /**
     * 执行有MethodRuntime 标识的方法
     */
    @Pointcut("execution(@com.lz.framecase.anotation.ClassRuntime * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    /**
     * 执行有MethodRuntime 标识的构造
     */
    @Pointcut("execution(@com.lz.framecase.anotation.ClassRuntime *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    /**
     * 替换原有方法执行
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("method() || constructor()")
    public Object logAndExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        //开始执行切点
        enterMethod(joinPoint);

        long startNanos = System.nanoTime();
        //执行原方法
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

        //执行完成切点
        exitMethod(joinPoint, result, lengthMillis);

        return result;
    }

    private static void enterMethod(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        //class 对象
        Class<?> cls = codeSignature.getDeclaringType();
        //方法名
        String methodName = codeSignature.getName();
        //参数类型
        String[] parameterNames = codeSignature.getParameterNames();
        //参数值
        Object[] parameterValues = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder("\u21E2 ");
        //拼接参数类型 参数值
        builder.append(methodName).append('(');
        for (int i = 0; i < parameterValues.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(parameterNames[i]).append('=');

            builder.append(ObjectUtil.toString(parameterValues[i]));
        }
        builder.append(')');

        //如果不在主线程打印线程id
        if (Looper.myLooper() != Looper.getMainLooper()) {
            builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
        }

        Log.v(asTag(cls), builder.toString());
    }

    private static void exitMethod(JoinPoint joinPoint, Object result, long lengthMillis) {
        Signature signature = joinPoint.getSignature();
        //class 对象
        Class<?> cls = signature.getDeclaringType();
        //方法名
        String methodName = signature.getName();
        //判断方法返回值
        boolean hasReturnType = signature instanceof MethodSignature
                && ((MethodSignature) signature).getReturnType() != void.class;

        //方法执行时间
        StringBuilder builder = new StringBuilder("\u21E0 ")
                .append(methodName)
                .append(" [")
                .append(lengthMillis)
                .append("ms]");

        //返回值信息
        if (hasReturnType) {
            builder.append(" = ");
            builder.append(ObjectUtil.toString(result));
        }

        Log.v(asTag(cls), builder.toString());
    }

    private static String asTag(Class<?> cls) {
        //判断是否是匿名类
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return TAGTIME + cls.getSimpleName();
    }

}
