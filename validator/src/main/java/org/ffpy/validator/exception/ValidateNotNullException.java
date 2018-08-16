package org.ffpy.validator.exception;

import java.lang.reflect.Field;

/**
 * 校验@NotNull注解失败时抛出的异常
 */
public class ValidateNotNullException extends ValidateException {

	/**
	 * 构造器
	 *
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败时的提示信息
	 */
	public ValidateNotNullException(Field field, String name, Object value, String message) {
		super(field, name, value, message);
	}
}
