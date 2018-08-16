package org.ffpy.validator.exception;

/**
 * 校验@Empty注解失败时抛出的异常
 */
public class ValidateEmptyException extends ValidateException {

	/**
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败的提示消息
	 */
	public ValidateEmptyException(String field, String name, Object value, String message) {
		super(field, name, value, message);
	}
}
