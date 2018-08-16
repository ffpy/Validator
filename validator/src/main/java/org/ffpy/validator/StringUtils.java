package org.ffpy.validator;

/**
 * 字符串工具类
 */
class StringUtils {

	/**
	 * 判断字符串是否为空。
	 * <p>cs == null 或 cs.length() == 0 则为空。
	 *
	 * @param cs 要判断的字符串
	 * @return 为空返回true，非空返回false
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 判断字符串是否非空。
	 * <p>cs =! null 并且 cs.length() != 0 则非空。
	 *
	 * @param cs 要判断的字符串
	 * @return 非空返回true，为空返回false
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	private StringUtils() {
		throw new AssertionError();
	}
}
