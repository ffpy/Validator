package org.ffpy.validator.exception;

import com.sun.istack.internal.Nullable;

import java.util.Objects;

/**
 * 校验失败的基类
 */
public abstract class ValidateException extends Exception {
	/** 字段名 */
	private String field;
	/** 字段名称 */
	private String name;
	/** 字段值 */
	private Object value;
	/** 校验失败的提示信息 */
	private String message;

	/**
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败的提示消息
	 */
	ValidateException(String field, String name, @Nullable Object value, String message) {
		this.field = field;
		this.name = Objects.requireNonNull(name);
		this.value = value;
		this.message = Objects.requireNonNull(message);
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
	 * 获取字段值。
	 *
	 * @return 字段值
	 */
	public Object getValue() {
		return value;
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