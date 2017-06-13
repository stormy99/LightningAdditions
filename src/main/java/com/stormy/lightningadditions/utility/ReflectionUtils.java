package com.stormy.lightningadditions.utility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public ReflectionUtils() {
    }

    public static <T> T createInstance(Class<? extends T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException var2) {
            var2.printStackTrace();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

    public static boolean fieldExists(Class<?> clazz, Class<?> type) {
        Field[] var2 = clazz.getFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.getType().equals(type)) {
                return true;
            }
        }

        return false;
    }

    public static void invokeMethod(Method method, Object clazzInstance, Object... params) {
        try {
            method.invoke(clazzInstance, params);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static void invokeMethod(Method method, Class<?> clazz, Object... params) {
        try {
            method.invoke(clazz, params);
        } catch (InvocationTargetException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static Object getField(Field field, Class<?> type) {
        try {
            return field.get(type);
        } catch (IllegalAccessException var3) {
            var3.printStackTrace();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return null;
    }

    public static void setField(Field field, Class<?> type, Object value) {
        try {
            field.set(type, value);
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public static Method getMethodForAnnotation(Class<? extends Annotation> annotation, Class<?> clazz) {
        Method[] var2 = clazz.getMethods();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method method = var2[var4];
            if (method.isAnnotationPresent(annotation)) {
                return method;
            }
        }

        return null;
    }

    public static Field getFieldForAnnotation(Class<? extends Annotation> annotation, Class<?> clazz) {
        Field[] var2 = clazz.getFields();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.isAnnotationPresent(annotation)) {
                return field;
            }
        }

        return null;
    }
}
