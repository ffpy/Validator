package org.ffpy.validator.exception;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.message.MessageBuilder;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * 校验失败时抛出的异常。
 */
public class ValidateFailException extends Exception {
	/** 校验注解类型的Key */
	private static final String KEY_ANNOTATION_TYPE = "annotationType";
	/** 字段信息 */
	private FieldData fieldData;
	/** 注释属性 */
	private AnnoAttrs attrs;
	/** 校验失败的提示信息 */
	private String message;

	/**
	 * 创建ValidateFailException实例。
	 *
	 * @param fieldData  字段信息
	 * @param attrs      校验注解属性
	 * @param templateId 模板ID
	 * @return ValidateFailException实例
	 */
	public static ValidateFailException newInstance(
			FieldData fieldData, AnnoAttrs attrs, String templateId) {
		String message = MessageBuilder.of(fieldData)
				.addAttrs(attrs)
				.build(templateId);
		return new ValidateFailException(fieldData, attrs, message);
	}

	/**
	 * 创建ValidateFailException实例。
	 *
	 * @param fieldData 字段信息
	 * @param attrs     校验注解属性
	 * @param message   提示信息
	 * @return ValidateFailException实例
	 */
	public static ValidateFailException newInstanceWithMessage(
			FieldData fieldData, AnnoAttrs attrs, String message) {
		return new ValidateFailException(fieldData, attrs, message);
	}

	/**
	 * @param fieldData 字段信息
	 * @param attrs     校验注解属性
	 * @param message   校验失败的提示消息
	 */
	private ValidateFailException(FieldData fieldData, AnnoAttrs attrs, String message) {
		this.fieldData = fieldData;
		this.attrs = attrs;
		this.message = Objects.requireNonNull(message);
	}

	/**
	 * 获取字段信息。
	 *
	 * @return 字段名
	 */
	public FieldData getFieldData() {
		return fieldData;
	}

	/**
	 * 获取注解属性。
	 *
	 * @return 注解属性
	 */
	public AnnoAttrs getAttrs() {
		return attrs;
	}

	/**
	 * 获取注解属性。
	 *
	 * @param name 属性名
	 * @return 注解属性
	 */
	public String getAttr(String name) {
		return attrs.get(name);
	}

	/**
	 * 获取校验注解的类型。
	 *
	 * @return 校验注解的类型
	 */
	public Class<? extends Annotation> getAnnotationType() {
		return attrs.get(KEY_ANNOTATION_TYPE);
	}

	/**
	 * 获取提示信息。
	 *
	 * @return 提示信息
	 */
	@Override
	public String getMessage() {
		return message;
	}
}