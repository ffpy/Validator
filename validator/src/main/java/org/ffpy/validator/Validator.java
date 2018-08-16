package org.ffpy.validator;

import org.ffpy.validator.annotation.*;
import org.ffpy.validator.cache.AnnotationCache;
import org.ffpy.validator.constant.AnnotationEnum;
import org.ffpy.validator.constant.MessageTemplate;
import org.ffpy.validator.exception.*;

import java.lang.reflect.Field;
import java.util.EnumSet;

/**
 * 校验器
 */
public class Validator {

	/**
	 * 校验Bean
	 * 校验Bean上的以下注解：
	 * 1. {@link Null}
	 * 2. {@link NotNull}
	 * 3. {@link Empty}
	 * 4. {@link NotEmpty}
	 * 5. {@link Match}
	 * 6. {@link NotMatch}
	 * 7. {@link Range}
	 * 8. {@link NotRange}
	 * 校验失败则抛出对应的异常：
	 * 1. {@link ValidateNullException}
	 * 2. {@link ValidateNotNullException}
	 * 3. {@link ValidateEmptyException}
	 * 4. {@link ValidateNotEmptyException}
	 * 5. {@link ValidateMatchException}
	 * 6. {@link ValidateNotMatchException}
	 * 7. {@link ValidateRangeException}
	 * 8. {@link ValidateNotRangeException}
	 *
	 * @param bean 要校验的Bean
	 * @throws ValidateException 校验失败或出错
	 */
	public static void validate(Object bean) throws ValidateException {
		FieldUtils.listWithValidateAnnotationFields(bean, (value, field) -> {
			// 获取字段上的校验注解枚举集合
			EnumSet<AnnotationEnum> annotationEnumEnumSet =
					AnnotationCache.getFieldAnnotationEnums(field);
			if (annotationEnumEnumSet == null) return;

			// 如果字段上有指定的校验注解，就校验
			if (annotationEnumEnumSet.contains(AnnotationEnum.NULL))
				validateNull(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.NOT_NULL))
				validateNotNull(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.EMPTY))
				validateEmpty(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.NOT_EMPTY))
				validateNotEmpty(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.MATCH))
				validateMatch(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.NOT_MATCH))
				validateNotMatch(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.RANGE))
				validateRange(value, field);
			if (annotationEnumEnumSet.contains(AnnotationEnum.NOT_RANGE))
				validateNotRange(value, field);
		});
	}

	/**
	 * 校验为Null
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateNullException 校验{@link Null}失败
	 */
	private static void validateNull(
			Object value, Field field) throws ValidateNullException {
		Null att = field.getAnnotation(Null.class);
		if (att == null) return;
		if (value != null) {
			String message = MessageBuilder.of(field, att.value())
					.build(MessageTemplate.NULL);
			throw new ValidateNullException(field, att.value(), value, message);
		}
	}

	/**
	 * 校验不为Null
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateNotNullException 校验{@link NotNull}失败
	 */
	private static void validateNotNull(
			Object value, Field field) throws ValidateNotNullException {
		NotNull att = field.getAnnotation(NotNull.class);
		if (att == null) return;
		if (value == null) {
			String message = MessageBuilder.of(field, att.value())
					.build(MessageTemplate.NOT_NULL);
			throw new ValidateNotNullException(field, att.value(), value, message);
		}
	}

	/**
	 * 校验为空
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateEmptyException 校验{@link Empty}失败
	 */
	private static void validateEmpty(
			Object value, Field field) throws ValidateEmptyException {
		Empty att = field.getAnnotation(Empty.class);
		if (att == null) return;
		if (ObjectUtils.isNotEmpty(value)) {
			String message = MessageBuilder.of(field, att.value())
					.build(MessageTemplate.EMPTY);
			throw new ValidateEmptyException(field, att.value(), value, message);
		}
	}

	/**
	 * 校验不为空
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateNotEmptyException 校验{@link NotEmpty}失败
	 */
	private static void validateNotEmpty(
			Object value, Field field) throws ValidateNotEmptyException {
		NotEmpty att = field.getAnnotation(NotEmpty.class);
		if (att == null) return;
		if (ObjectUtils.isEmpty(value)) {
			String message = MessageBuilder.of(field, att.value())
					.build(MessageTemplate.NOT_EMPTY);
			throw new ValidateNotEmptyException(field, att.value(), value, message);
		}
	}

	/**
	 * 校验匹配正则表达式
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateMatchException 校验{@link Match}失败
	 */
	private static void validateMatch(
			Object value, Field field) throws ValidateMatchException {
		Match att = field.getAnnotation(Match.class);
		if (att == null) return;
		if (ObjectUtils.isNotMatch(value, att.pattern())) {
			String message = MessageBuilder.of(field, att.value())
					.pattern(att.pattern())
					.build(MessageTemplate.MATCH);
			throw new ValidateMatchException(field, att.value(), value, att.pattern(), message);
		}
	}

	/**
	 * 校验不匹配正则表达式
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateNotMatchException 校验{@link NotMatch}失败
	 */
	private static void validateNotMatch(
			Object value, Field field) throws ValidateNotMatchException {
		NotMatch att = field.getAnnotation(NotMatch.class);
		if (att == null) return;
		if (ObjectUtils.isMatch(value, att.pattern())) {
			String message = MessageBuilder.of(field, att.value())
					.pattern(att.pattern())
					.build(MessageTemplate.NOT_MATCH);
			throw new ValidateNotMatchException(field, att.value(), value, att.pattern(), message);
		}
	}

	/**
	 * 校验数值在范围内
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateRangeException 校验{@link Range}失败
	 */
	private static void validateRange(
			Object value, Field field) throws ValidateRangeException {
		Range att = field.getAnnotation(Range.class);
		if (att == null) return;
		if (ObjectUtils.isNotInRange(value, att.min(), att.max())) {
			String message = MessageBuilder.of(field, att.value())
					.min(att.min()).max(att.max())
					.build(MessageTemplate.RANGE);
			throw new ValidateRangeException(field, att.value(), value, message,
					att.min(), att.max());
		}
	}

	/**
	 * 校验数值不在范围内
	 *
	 * @param value 字段值
	 * @param field 字段
	 * @throws ValidateNotRangeException 校验{@link NotRange}失败
	 */
	private static void validateNotRange(
			Object value, Field field) throws ValidateNotRangeException {
		NotRange att = field.getAnnotation(NotRange.class);
		if (att == null) return;
		if (ObjectUtils.isInRange(value, att.min(), att.max())) {
			String message = MessageBuilder.of(field, att.value())
					.min(att.min()).max(att.max())
					.build(MessageTemplate.NOT_RANGE);
			throw new ValidateNotRangeException(field, att.value(), value, message,
					att.min(), att.max());
		}
	}

	private Validator() {
		throw new AssertionError();
	}
}