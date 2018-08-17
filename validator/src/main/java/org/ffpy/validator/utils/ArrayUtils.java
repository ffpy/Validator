package org.ffpy.validator.utils;

/**
 * 数组工具类。
 */
public class ArrayUtils {

	/**
	 * 判断数组中是否存在指定元素。
	 * <p>
	 * a == b 或者 a.equals(b) 则视为相同元素。
	 *
	 * @param array 要处理的数组
	 * @param e     判断是否存在的元素
	 * @param <T>   数组元素类型
	 * @return 存在则返回true，不存在则返回false。
	 */
	public static <T> boolean contains(T[] array, T e) {
		if (array == null) return false;
		for (T t : array) {
			if ((t == e) || (t != null && t.equals(e))) return true;
		}
		return false;
	}
}
