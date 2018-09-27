package com.lz.inject_annotation;


import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * Created by JokAr on 16/8/6.
 */
public class InjectTools {
    private static final HashMap<String, Object> injectMap = new HashMap<>();
    public static final String PACKAGEPATH = "com.lz.MyInjectUtils";

    public static void inject(Object object) {
        String className = object.getClass().getName();
        try {
            Object inject = injectMap.get(className);

            if (inject == null) {
                Class<?> aClass = Class.forName(PACKAGEPATH);
                inject = aClass.newInstance();
                injectMap.put(className, inject);
            }
            Method m1 = inject.getClass().getDeclaredMethod("inject" + object.getClass().getSimpleName(), object.getClass());
            m1.invoke(inject, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
