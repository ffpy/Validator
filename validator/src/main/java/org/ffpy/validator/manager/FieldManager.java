package org.ffpy.validator.manager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 校验字段管理类
 */
public class FieldManager {
	/** 用于缓存的Map */
	private static final Map<Class<?>, List<Field>> cache = new ConcurrentHashMap<>();

	/**
	 * 获取类上带有校验注解的字段列表，使用缓存。
	 *
	 * @param cls 处理的类
	 * @return 带有校验注解的字段列表
	 */
	public static List<Field> getHasValidateAnnotationFields(Class<?> cls) {
		List<Field> hasValidateAnnotationFields = cache.get(cls);
		if (hasValidateAnnotationFields == null) {
			hasValidateAnnotationFields = getHasValidateAnnotationFieldsWithoutCache(cls);
			cache.put(cls, hasValidateAnnotationFields);
		}
		return hasValidateAnnotationFields;
	}

	/**
	 * 获取带有校验注解的字段，不使用缓存。
	 *
	 * @param cls 处理的类
	 * @return 带有校验注解的字段列表
	 */
	private static List<Field> getHasValidateAnnotationFieldsWithoutCache(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		List<Field> hasValidateAnnotationFields = new ArrayList<>(fields.length);
		for (Field field : fields) {
			if (!field.isAccessible()) field.setAccessible(true);
			if (hasValidateAnnotation(field)) hasValidateAnnotationFields.add(field);
		}
		((ArrayList) hasValidateAnnotationFields).trimToSize();
		return hasValidateAnnotationFields;
	}

	/**
	 * 判断字段是否有校验注解。
	 *
	 * @param field 处理的字段
	 * @return 有则返回true，没有返回false
	 */
	private static boolean hasValidateAnnotation(Field field) {
		return FileDataManager.getFieldData(field).hasValidateAnnotations();
	}

	private FieldManager() {
		throw new AssertionError();
	}
}
