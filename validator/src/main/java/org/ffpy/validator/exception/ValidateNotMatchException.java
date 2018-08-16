package org.ffpy.validator.exception;

import java.lang.reflect.Field;

/**
 * 校验@NotMatch注解失败时抛出的异常
 */
public class ValidateNotMatchException extends ValidateException {
	// 不能匹配的正则表达式
	private String pattern;

	/**
	 * 构造器
	 *
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param pattern 不能匹配的正则表达式
	 * @param message 校验失败时的提示信息
	 */
	public ValidateNotMatchException(
			Field field, String name, Object value, String pattern, String message) {
		super(field, name, value, message);
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}
