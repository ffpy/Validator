package org.ffpy.validator;

import org.ffpy.validator.annotation.*;
import org.ffpy.validator.manager.FileDataManager;
import org.ffpy.validator.constant.MessageTemplate;
import org.ffpy.validator.exception.*;

import java.lang.reflect.Field;

/**
 * 校验器
 */
public class Validator {

	/**
	 * 校验Bean上带校验注解的字段，校验失败则抛出对应的异常。
	 * <table>
	 *     <caption>校验注解与异常</caption>
	 *     <tr>
	 *         <th>校验注解</th>
	 *         <th>抛出的异常</th>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link Null}</td>
	 *         <td>{@link ValidateNullException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link NotNull}</td>
	 *         <td>{@link ValidateNotNullException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link Empty}</td>
	 *         <td>{@link ValidateEmptyException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link NotEmpty}</td>
	 *         <td>{@link ValidateNotEmptyException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link Match}</td>
	 *         <td>{@link ValidateMatchException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link NotMatch}</td>
	 *         <td>{@link ValidateNotMatchException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link Range}</td>
	 *         <td>{@link ValidateRangeException}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>{@link NotRange}</td>
	 *         <td>{@link ValidateNotRangeException}</td>
	 *     </tr>
	 * </table>
	 *
	 * @param bean 要校验的Bean
	 * @throws ValidateException 校验失败
	 */
	public static void validate(Object bean) throws ValidateException {
		FieldUtils.listWithValidateAnnotationFields(bean, (value, field) -> {
			FieldData fieldData = FileDataManager.getFieldData(field);
			if (fieldData == null || !fieldData.hasValidateAnnotations()) return;

			if (fieldData.hasNullAnnotation())
				validateNull(value, fieldData);
			if (fieldData.hasNotNullAnnotation())
				validateNotNull(value, fieldData);
			if (fieldData.hasEmptyAnnotation())
				validateEmpty(value, fieldData);
			if (fieldData.hasNotEmptyAnnotation())
				validateNotEmpty(value, fieldData);
			if (fieldData.hasMatchAnnotation())
				validateMatch(value, fieldData, field);
			if (fieldData.hasNotMatchAnnotation())
				validateNotMatch(value, fieldData, field);
			if (fieldData.hasRangeAnnotation())
				validateRange(value, fieldData, field);
			if (fieldData.hasNotRangeAnnotation())
				validateNotRange(value, fieldData, field);
		});
	}

	/**
	 * 校验为Null。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @throws ValidateNullException 校验{@link Null}失败
	 */
	private static void validateNull(
			Object value, FieldData fieldData) throws ValidateNullException {
		if (value != null) {
			String message = MessageBuilder.of(fieldData).build(MessageTemplate.NULL);
			throw new ValidateNullException(fieldData.getField(), fieldData.getName(), value, message);
		}
	}

	/**
	 * 校验不为Null。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @throws ValidateNotNullException 校验{@link NotNull}失败
	 */
	private static void validateNotNull(
			Object value, FieldData fieldData) throws ValidateNotNullException {
		if (value == null) {
			String message = MessageBuilder.of(fieldData).build(MessageTemplate.NOT_NULL);
			throw new ValidateNotNullException(fieldData.getField(), fieldData.getName(), value, message);
		}
	}

	/**
	 * 校验为空。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @throws ValidateEmptyException 校验{@link Empty}失败
	 */
	private static void validateEmpty(
			Object value, FieldData fieldData) throws ValidateEmptyException {
		if (ObjectUtils.isNotEmpty(value)) {
			String message = MessageBuilder.of(fieldData).build(MessageTemplate.EMPTY);
			throw new ValidateEmptyException(fieldData.getField(), fieldData.getName(), value, message);
		}
	}

	/**
	 * 校验不为空。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @throws ValidateNotEmptyException 校验{@link NotEmpty}失败
	 */
	private static void validateNotEmpty(
			Object value, FieldData fieldData) throws ValidateNotEmptyException {
		if (ObjectUtils.isEmpty(value)) {
			String message = MessageBuilder.of(fieldData).build(MessageTemplate.NOT_EMPTY);
			throw new ValidateNotEmptyException(fieldData.getField(), fieldData.getName(), value, message);
		}
	}

	/**
	 * 校验匹配正则表达式。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @param field     字段
	 * @throws ValidateMatchException 校验{@link Match}失败
	 */
	private static void validateMatch(
			Object value, FieldData fieldData, Field field) throws ValidateMatchException {
		Match att = fieldData.getMatchAnnotation(field);
		if (ObjectUtils.isNotMatch(value, att.pattern())) {
			String message = MessageBuilder.of(fieldData)
					.pattern(att.pattern())
					.build(MessageTemplate.MATCH);
			throw new ValidateMatchException(fieldData.getField(), fieldData.getName(),
					value, att.pattern(), message);
		}
	}

	/**
	 * 校验不匹配正则表达式。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @param field     字段
	 * @throws ValidateNotMatchException 校验{@link NotMatch}失败
	 */
	private static void validateNotMatch(
			Object value, FieldData fieldData, Field field) throws ValidateNotMatchException {
		NotMatch att = fieldData.getNotMatchAnnotation(field);
		if (ObjectUtils.isMatch(value, att.pattern())) {
			String message = MessageBuilder.of(fieldData)
					.pattern(att.pattern())
					.build(MessageTemplate.NOT_MATCH);
			throw new ValidateNotMatchException(fieldData.getField(), fieldData.getName(),
					value, att.pattern(), message);
		}
	}

	/**
	 * 校验数值在范围内。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @param field     字段
	 * @throws ValidateRangeException 校验{@link Range}失败
	 */
	private static void validateRange(
			Object value, FieldData fieldData, Field field) throws ValidateRangeException {
		Range att = fieldData.getRangeAnnotation(field);
		if (ObjectUtils.isNotInRange(value, att.min(), att.max())) {
			String message = MessageBuilder.of(fieldData)
					.min(att.min()).max(att.max())
					.build(MessageTemplate.RANGE);
			throw new ValidateRangeException(fieldData.getField(), fieldData.getName(),
					value, message, att.min(), att.max());
		}
	}

	/**
	 * 校验数值不在范围内。
	 *
	 * @param value     字段值
	 * @param fieldData 字段数据
	 * @param field     字段
	 * @throws ValidateNotRangeException 校验{@link NotRange}失败
	 */
	private static void validateNotRange(
			Object value, FieldData fieldData, Field field) throws ValidateNotRangeException {
		NotRange att = fieldData.getNotRangeAnnotation(field);
		if (ObjectUtils.isInRange(value, att.min(), att.max())) {
			String message = MessageBuilder.of(fieldData)
					.min(att.min()).max(att.max())
					.build(MessageTemplate.NOT_RANGE);
			throw new ValidateNotRangeException(fieldData.getField(), fieldData.getName(),
					value, message, att.min(), att.max());
		}
	}

	private Validator() {
		throw new AssertionError();
	}
}