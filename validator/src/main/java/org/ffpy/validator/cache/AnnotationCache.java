package org.ffpy.validator.cache;

import org.ffpy.validator.annotation.*;
import org.ffpy.validator.constant.AnnotationEnum;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 校验注解缓存类
 */
public class AnnotationCache {
	// 用于缓存的Map
	private static final Map<Field, EnumSet<AnnotationEnum>> cache = new ConcurrentHashMap<>();

	/**
	 * 获取字段的校验注解枚举集合，使用缓存
	 *
	 * @param field 处理的字段
	 * @return 字段上的校验注解枚举集合
	 */
	public static EnumSet<AnnotationEnum> getFieldAnnotationEnums(Field field) {
		EnumSet<AnnotationEnum> annotationEnums = cache.get(field);
		if (annotationEnums == null) {
			annotationEnums = getFieldAnnotationEnumsWithoutCache(field);
			cache.put(field, annotationEnums);
		}
		return annotationEnums;
	}

	/**
	 * 获取字段的校验注解枚举集合，不使用缓存
	 *
	 * @param field 处理的字段
	 * @return 字段上的校验注解枚举集合
	 */
	private static EnumSet<AnnotationEnum> getFieldAnnotationEnumsWithoutCache(Field field) {
		EnumSet<AnnotationEnum> annotationEnums = EnumSet.noneOf(AnnotationEnum.class);
		if (field.getAnnotation(Null.class) != null)
			annotationEnums.add(AnnotationEnum.NULL);
		if (field.getAnnotation(NotNull.class) != null)
			annotationEnums.add(AnnotationEnum.NOT_NULL);
		if (field.getAnnotation(Empty.class) != null)
			annotationEnums.add(AnnotationEnum.EMPTY);
		if (field.getAnnotation(NotEmpty.class) != null)
			annotationEnums.add(AnnotationEnum.NOT_EMPTY);
		if (field.getAnnotation(Match.class) != null)
			annotationEnums.add(AnnotationEnum.MATCH);
		if (field.getAnnotation(NotMatch.class) != null)
			annotationEnums.add(AnnotationEnum.NOT_MATCH);
		if (field.getAnnotation(Range.class) != null)
			annotationEnums.add(AnnotationEnum.RANGE);
		if (field.getAnnotation(NotRange.class) != null)
			annotationEnums.add(AnnotationEnum.NOT_RANGE);
		return annotationEnums;
	}
}
