package org.ffpy.validator.utils;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.anno.ValidatorAnno;
import org.ffpy.validator.exception.ValidatorException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解工具类。
 */
public class AnnoUtils {
	/** 校验注解的value属性名 */
	private static final String ATTR_VALUE = "value";
	/** 校验注解的group属性名 */
	private static final String ATTR_GROUP = "group";

	/**
	 * 获取校验注解的value属性值。
	 *
	 * @param anno 校验注解
	 * @return value属性值
	 */
	public static String getValue(Annotation anno) {
		return getAttr(anno, ATTR_VALUE);
	}

	/**
	 * 获取校验注解的group属性值。
	 *
	 * @param anno 校验注解
	 * @return group属性值
	 */
	public static String[] getGroup(Annotation anno) {
		return getAttr(anno, ATTR_GROUP);
	}

	/**
	 * 获取注解上的指定属性。
	 *
	 * @param anno 注解
	 * @param name 属性名
	 * @param <T>  属性值类型
	 * @return 属性值
	 */
	public static <T> T getAttr(Annotation anno, String name) {
		Class<? extends Annotation> cls = anno.getClass();
		String className = cls.getName();
		try {
			@SuppressWarnings("unchecked") T attr = (T) cls.getMethod(name).invoke(anno);
			return attr;
		} catch (NoSuchMethodException e) {
			throw new ValidatorException(className + "上没有" + name + "这个属性", e);
		} catch (Exception e) {
			throw new ValidatorException("获取" + className + "上的" + name + "属性失败", e);
		}
	}

	/**
	 * 获取注解上的所有属性。
	 *
	 * @param anno 注解
	 * @return 注解上的属性，以Map的形式存放，key是属性名，value是属性值。
	 */
	public static Map<String, Object> getAttrs(Annotation anno) {
		// 忽略的方法，不是注解的属性
		final String[] ignoreMethods = {"hashCode", "toString"};
		Map<String, Object> attrs = new HashMap<>();
		// 获取注解上的所有方法
		Class<? extends Annotation> cls = anno.getClass();
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			// 跳过非注解属性的方法
			if (method.getParameterCount() != 0) continue;
			// 跳过忽略的方法
			if (ArrayUtils.contains(ignoreMethods, method.getName())) continue;
			try {
				// 获取方法的值，并放入attrs
				attrs.put(method.getName(), method.invoke(anno));
			} catch (Exception e) {
				throw new ValidatorException("获取" + cls.getName() +
						"上的" + method.getName() + "属性失败", e);
			}
		}
		return attrs;
	}

	/**
	 * 获取注解上的所有属性。
	 *
	 * @param anno 注解
	 * @return 注解上的属性，以Map的形式存放，key是属性名，value是属性值。
	 */
	public static AnnoAttrs getAnnoAttrs(Annotation anno) {
		return new AnnoAttrs(getAttrs(anno));
	}

	/**
	 * 获取注解的类型。
	 *
	 * @param anno 要处理的注解
	 * @return 注解的类型
	 */
	public static Class<? extends Annotation> getAnnotationType(Annotation anno) {
		Class<? extends Annotation> cls = anno.getClass();
		try {
			Method method = cls.getDeclaredMethod("annotationType");
			@SuppressWarnings("unchecked") Class<? extends Annotation> annoClass =
					(Class<? extends Annotation>) method.invoke(anno);
			return annoClass;
		} catch (Exception e) {
			throw new ValidatorException("获取" + anno + "的注解类型失败", e);
		}
	}

	/**
	 * 判断是不是校验注解。
	 *
	 * @param anno 要判断的注解
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidatorAnno(Annotation anno) {
		return isValidatorAnno(getAnnotationType(anno));
	}

	/**
	 * 判断是不是校验注解。
	 *
	 * @param annotationType 注解类型
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidatorAnno(Class<? extends Annotation> annotationType) {
		return annotationType.getAnnotation(ValidatorAnno.class) != null;
	}

	private AnnoUtils() {
	}
}
