package org.ffpy.validator.utils;

/**
 * 字符串工具类。
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空。
	 * <p>cs == null 或 cs.length() == 0 则为空。
	 *
	 * @param cs 要判断的字符串
	 * @return 为空返回true，非空返回false。
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * 判断字符串是否非空。
	 * <p>cs =! null 并且 cs.length() != 0 则非空。
	 *
	 * @param cs 要判断的字符串
	 * @return 非空返回true，为空返回false。
	 */
	public static boolean isNotEmpty(CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * 浮点数转字符串形式，把字符串末尾的".0"去掉。
	 * <p>例如把"1.0"改为"1"。
	 *
	 * @param v 要转换的浮点数
	 * @return 浮点数的字符串形式
	 */
	public static String float2String(Float v) {
		String s = String.valueOf(v);
		// 如果末尾有".0"，则去掉
		if (s.endsWith(".0")) s = s.substring(0, s.length() - 2);
		return s;
	}

	/**
	 * 浮点数转字符串形式，把字符串末尾的".0"去掉。
	 * <p>例如把"1.0"改为"1"。
	 *
	 * @param v 要转换的浮点数
	 * @return 浮点数的字符串形式
	 */
	public static String double2String(Double v) {
		String s = String.valueOf(v);
		// 如果末尾有".0"，则去掉
		if (s.endsWith(".0")) s = s.substring(0, s.length() - 2);
		return s;
	}

	private StringUtils() {
		throw new AssertionError();
	}
}
