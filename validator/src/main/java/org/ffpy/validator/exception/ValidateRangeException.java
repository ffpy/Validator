package org.ffpy.validator.exception;

/**
 * 校验@Range注解失败时抛出的异常
 */
public class ValidateRangeException extends ValidateException {
	/** 最小值 */
	private double min;
	/** 最大值 */
	private double max;

	/**
	 * @param field   字段名
	 * @param name    字段名称
	 * @param value   字段值
	 * @param message 校验失败时的提示信息
	 * @param min     最小值
	 * @param max     最大值
	 */
	public ValidateRangeException(
			String field, String name, Object value, String message, double min, double max) {
		super(field, name, value, message);
		this.min = min;
		this.max = max;
	}

	/**
	 * 获取最小值。
	 *
	 * @return 最小值
	 */
	public double getMin() {
		return min;
	}

	/**
	 * 获取最大值。
	 *
	 * @return 最大值
	 */
	public double getMax() {
		return max;
	}
}
