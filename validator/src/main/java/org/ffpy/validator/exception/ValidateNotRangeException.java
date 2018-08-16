package org.ffpy.validator.exception;

/**
 * 校验@NotRange注解失败时抛出的异常
 */
public class ValidateNotRangeException extends ValidateException {
	// 最小值
	private double min;
	// 最大值
	private double max;

	/**
	 * 构造器
	 *
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败时的提示信息
	 * @param min     最小值
	 * @param max     最大值
	 */
	public ValidateNotRangeException(
			String field, String name, Object value, String message, double min, double max) {
		super(field, name, value, message);
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
}
