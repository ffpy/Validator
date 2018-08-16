package org.ffpy.validator.manager;

import org.ffpy.validator.FieldData;
import org.ffpy.validator.annotation.*;
import org.ffpy.validator.constant.AnnotationEnum;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 校验字段数据管理类
 */
public class FileDataManager {
	/** 用于缓存的Map */
	private static final Map<Field, FieldData> cache = new ConcurrentHashMap<>();

	/**
	 * 获取字段的数据。
	 *
	 * @param field 处理的字段
	 * @return 字段的数据
	 */
	public static FieldData getFieldData(Field field) {
		FieldData fieldData = cache.get(field);
		if (fieldData == null) {
			fieldData = new FieldData(field, getFieldAnnotationEnums(field));
			cache.put(field, fieldData);
		}
		return fieldData;
	}

	/**
	 * 获取字段的校验注解枚举集合。
	 *
	 * @param field 处理的字段
	 * @return 字段上的校验注解枚举集合
	 */
	private static EnumSet<AnnotationEnum> getFieldAnnotationEnums(Field field) {
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
