package org.ffpy.validator;

import org.ffpy.validator.annotation.*;
import org.ffpy.validator.constant.AnnotationEnum;

import java.lang.reflect.Field;
import java.util.EnumSet;

/**
 * 保存字段上的校验数据
 */
public class FieldData {
	// 校验注解枚举集合
	private final EnumSet<AnnotationEnum> annotationEnumSet;
	// 字段名
	private final String field;
	// 字段名称
	private final String name;

	/**
	 * 构造器
	 *
	 * @param field             指定的字段
	 * @param annotationEnumSet 校验注解枚举集合
	 */
	public FieldData(Field field, EnumSet<AnnotationEnum> annotationEnumSet) {
		this.annotationEnumSet = annotationEnumSet;
		this.field = field.getName();
		this.name = initName(field);
	}

	/**
	 * 初始化字段名称，字段上的所有校验注解上都没有设置的话，默认为字段名
	 *
	 * @param field 指定的字段
	 * @return 字段名称
	 */
	private String initName(Field field) {
		String name = null;
		if (hasNullAnnotation())
			name = getNullAnnotation(field).value();
		if (name == null && hasNotNullAnnotation())
			name = getNotNullAnnotation(field).value();
		if (name == null && hasEmptyAnnotation())
			name = getEmptyAnnotation(field).value();
		if (name == null && hasNotEmptyAnnotation())
			name = getNotEmptyAnnotation(field).value();
		if (name == null && hasMatchAnnotation())
			name = getMatchAnnotation(field).value();
		if (name == null && hasNotMatchAnnotation())
			name = getNotMatchAnnotation(field).value();
		if (name == null && hasRangeAnnotation())
			name = getRangeAnnotation(field).value();
		if (name == null && hasNotRangeAnnotation())
			name = getNotRangeAnnotation(field).value();
		if (name == null)
			name = field.getName();
		return name;
	}

	public String getField() {
		return field;
	}

	public String getName() {
		return name;
	}

	public EnumSet<AnnotationEnum> getAnnotationEnumSet() {
		return annotationEnumSet;
	}

	public boolean hasValidateAnnotations() {
		return !annotationEnumSet.isEmpty();
	}

	public boolean hasNullAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NULL);
	}

	public Null getNullAnnotation(Field field) {
		return hasNullAnnotation() ? field.getAnnotation(Null.class) : null;
	}

	public boolean hasNotNullAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_NULL);
	}

	public NotNull getNotNullAnnotation(Field field) {
		return hasNotNullAnnotation() ? field.getAnnotation(NotNull.class) : null;
	}

	public boolean hasEmptyAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.EMPTY);
	}

	public Empty getEmptyAnnotation(Field field) {
		return hasEmptyAnnotation() ? field.getAnnotation(Empty.class) : null;
	}

	public boolean hasNotEmptyAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_EMPTY);
	}

	public NotEmpty getNotEmptyAnnotation(Field field) {
		return hasNotEmptyAnnotation() ? field.getAnnotation(NotEmpty.class) : null;
	}

	public boolean hasMatchAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.MATCH);
	}

	public Match getMatchAnnotation(Field field) {
		return hasMatchAnnotation() ? field.getAnnotation(Match.class) : null;
	}

	public boolean hasNotMatchAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_MATCH);
	}

	public NotMatch getNotMatchAnnotation(Field field) {
		return hasNotMatchAnnotation() ? field.getAnnotation(NotMatch.class) : null;
	}

	public boolean hasRangeAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.RANGE);
	}

	public Range getRangeAnnotation(Field field) {
		return hasRangeAnnotation() ? field.getAnnotation(Range.class) : null;
	}

	public boolean hasNotRangeAnnotation() {
		return annotationEnumSet.contains(AnnotationEnum.NOT_RANGE);
	}

	public NotRange getNotRangeAnnotation(Field field) {
		return hasNotRangeAnnotation() ? field.getAnnotation(NotRange.class) : null;
	}
}