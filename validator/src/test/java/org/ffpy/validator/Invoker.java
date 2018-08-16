package org.ffpy.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 通过反射调用私用方法方便进行单元测试的工具类
 */
class Invoker {
    private Class<?> cls;
    private Object obj;
    private Class<?> parameterTypes;
    private String methodName;

    private Invoker() {
    }

    public static Invoker ofClass(Class<?> cls, String methodName) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(methodName);

        Invoker invoker = new Invoker();
        invoker.cls = cls;
        invoker.methodName = methodName;
        return invoker;
    }

    public static Invoker ofObject(Object obj, String methodName) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(methodName);

        Invoker invoker = new Invoker();
        invoker.cls = obj.getClass();
        invoker.obj = obj;
        invoker.methodName = methodName;
        return invoker;
    }

    public Invoker paramTypes(Class<?> parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public <T> T invoke(Object... args) {
        try {
            Method method = cls.getDeclaredMethod(methodName, parameterTypes);
            return (T) method.invoke(obj, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
