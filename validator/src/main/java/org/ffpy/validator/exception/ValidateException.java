package org.ffpy.validator.exception;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 校验失败的基类
 */
public abstract class ValidateException extends Exception {
	// 字段名
	private String field;
	// 字段名称
	private String name;
	// 字段值
	private Object value;
	// 校验失败的提示信息
	private String message;

	/**
	 * 构造器
	 *
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败的提示消息
	 */
	ValidateException(Field field, String name, @Nullable Object value, String message) {
		this.field = Objects.requireNonNull(field).getName();
		this.name = Objects.requireNonNull(name);
		this.value = value;
		this.message = Objects.requireNonNull(message);
	}

	public String getField() {
		return field;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String getMessage() {
		return message;
	}
}