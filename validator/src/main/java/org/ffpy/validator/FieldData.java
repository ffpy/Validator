package org.ffpy.validator;

import org.ffpy.validator.annotation.*;
import org.ffpy.validator.constant.AnnotationEnum;

import java.lang.reflect.Field;
import java.util.EnumSet;

/**
 * 保存字段上的校验数据
 */
public class FieldData {
	/** 校验注解枚举集合 */
	private final EnumSet<AnnotationEnum> annotationEnumSet;
	/** 字段名 */
	private final String field;
	/** 字段名称 */
	private final String name;

	/**
	 * @param field             指定的字段
	 * @param annotationEnumSet 校验注解枚举集合
	 */
	public FieldData(Field field, EnumSet<AnnotationEnum> annotationEnumSet) {
		this.annotationEnumSet = annotationEnumSet;
		this.field = field.getName();
		this.name = initName(field);
	}

	/**
	 * 初始化字段名称，字段上的所有校验注解上都没有设置的话，默认为字段名。
	 *
	 * @param field 指定的字段
	 * @return 字段名称
	 */
	private String initName(Field field) {
		String name = "";
		if (hasNullAnnotation())
			name = getNullAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasNotNullAnnotation())
			name = getNotNullAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasEmptyAnnotation())
			name = getEmptyAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasNotEmptyAnnotation())
			name = getNotEmptyAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasMatchAnnotation())
			name = getMatchAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasNotMatchAnnotation())
			name = getNotMatchAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasRangeAnnotation())
			name = getRangeAnnotation(field).value();
		if (StringUtils.isEmpty(name) && hasNotRangeAnnotation())
			name = getNotRangeAnnotation(field).value();
		// 校验注解上没有设置，设置默认值
		if (StringUtils.isEmpty(name))
			name = field.getName();
		return name;
	}

	/**
	 * 获取字段名。
	 *
	 * @return 字段名
	 */
	public String getField() {
		return field;
	}

	/**
	 * 获取字段名称。
	 *
	 * @return 字段名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取字段的校验注解枚举集合。
	 *
	 * @return 校验注解枚举集合
	 */
	public EnumSet<AnnotationEnum> getAnnotationEnumSet() {
		return annotationEnumSet;
	}

	/**
	 * 判断有没有校验注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasValidateAnnotations() {
		return !annotationEnumSet.isEmpty();
	}

	/**
	 * 判断有没有@Null注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasNullAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NULL);
	}

	/**
	 * 获取字段上的@Null注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@Null注解
	 */
	public Null getNullAnnotation(Field field) {
		return hasNullAnnotation() ? field.getAnnotation(Null.class) : null;
	}

	/**
	 * 判断有没有@NotNull注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasNotNullAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_NULL);
	}

	/**
	 * 获取字段上的@NotNull注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@NotNull注解
	 */
	public NotNull getNotNullAnnotation(Field field) {
		return hasNotNullAnnotation() ? field.getAnnotation(NotNull.class) : null;
	}

	/**
	 * 判断有没有@Empty注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasEmptyAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.EMPTY);
	}

	/**
	 * 获取字段上的@Empty注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@Empty注解
	 */
	public Empty getEmptyAnnotation(Field field) {
		return hasEmptyAnnotation() ? field.getAnnotation(Empty.class) : null;
	}

	/**
	 * 判断有没有@NotEmpty注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasNotEmptyAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_EMPTY);
	}

	/**
	 * 获取字段上的@NotEmpty注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@NotEmpty注解
	 */
	public NotEmpty getNotEmptyAnnotation(Field field) {
		return hasNotEmptyAnnotation() ? field.getAnnotation(NotEmpty.class) : null;
	}

	/**
	 * 判断有没有@Match注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasMatchAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.MATCH);
	}

	/**
	 * 获取字段上的@Match注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@Match注解
	 */
	public Match getMatchAnnotation(Field field) {
		return hasMatchAnnotation() ? field.getAnnotation(Match.class) : null;
	}

	/**
	 * 判断有没有@NotMatch注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasNotMatchAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_MATCH);
	}

	/**
	 * 获取字段上的@NotMatch注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@NotMatch注解
	 */
	public NotMatch getNotMatchAnnotation(Field field) {
		return hasNotMatchAnnotation() ? field.getAnnotation(NotMatch.class) : null;
	}

	/**
	 * 判断有没有@Range注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasRangeAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.RANGE);
	}

	/**
	 * 获取字段上的@Range注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@Range注解
	 */
	public Range getRangeAnnotation(Field field) {
		return hasRangeAnnotation() ? field.getAnnotation(Range.class) : null;
	}

	/**
	 * 判断有没有@NotRange注解。
	 *
	 * @return 有则返回true，否则返回false
	 */
	public boolean hasNotRangeAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_RANGE);
	}

	/**
	 * 获取字段上的@NotRange注解。
	 *
	 * @param field 要获取的字段
	 * @return 字段上的@NotRange注解
	 */
	public NotRange getNotRangeAnnotation(Field field) {
		return hasNotRangeAnnotation() ? field.getAnnotation(NotRange.class) : null;
	}
}
