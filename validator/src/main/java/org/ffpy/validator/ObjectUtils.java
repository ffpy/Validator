package org.ffpy.validator;

import java.util.Collection;
import java.util.Map;

/**
 * 对象工具类
 */
class ObjectUtils {

	/**
	 * 判断对象是否为空
	 * 适用于以下情况：
	 * 1. Object == null
	 * 2. String.isEmpty()
	 * 3. T[].length == 0
	 * 4. Collection.isEmpty()
	 * 5. Map.isEmpty()
	 *
	 * @param obj 要判断的对象
	 * @return 空返回true，非空返回false
	 */
	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {                              // null
			isEmpty = true;
		} else if (obj instanceof String) {             // String
			isEmpty = ((String) obj).isEmpty();
		} else if (obj instanceof Object[]) {           // Object[]
			isEmpty = ((Object[]) obj).length == 0;
		} else if (obj instanceof byte[]) {                // byte[]
			isEmpty = ((byte[]) obj).length == 0;
		} else if (obj instanceof short[]) {            // short[]
			isEmpty = ((short[]) obj).length == 0;
		} else if (obj instanceof int[]) {                // int[]
			isEmpty = ((int[]) obj).length == 0;
		} else if (obj instanceof long[]) {                // long[]
			isEmpty = ((long[]) obj).length == 0;
		} else if (obj instanceof float[]) {            // float[]
			isEmpty = ((float[]) obj).length == 0;
		} else if (obj instanceof double[]) {           // double[]
			isEmpty = ((double[]) obj).length == 0;
		} else if (obj instanceof boolean[]) {          // boolean[]
			isEmpty = ((boolean[]) obj).length == 0;
		} else if (obj instanceof char[]) {                // char[]
			isEmpty = ((char[]) obj).length == 0;
		} else if (obj instanceof Collection) {         // Collection
			isEmpty = ((Collection) obj).isEmpty();
		} else if (obj instanceof Map) {                // Map
			isEmpty = ((Map) obj).isEmpty();
		}
		return isEmpty;
	}

	/**
	 * 判断对象是否为非空
	 * 适用于以下情况：
	 * 1. Object != null
	 * 2. !String.isEmpty()
	 * 3. T[].length != 0
	 * 4. !Collection.isEmpty()
	 * 5. !Map.isEmpty()
	 *
	 * @param obj 要判断的对象
	 * @return 非空返回true，空返回false
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * 判断对象是否匹配指定的正则表达式
	 * 如果obj为null或者obj.toString()的返回值为null，匹配失败
	 * 否则判断obj.toString()的返回值是否匹配指定的正则表达式
	 *
	 * @param obj     要判断的对象
	 * @param pattern 匹配的正则表达式
	 * @return 匹配返回true，不匹配返回false
	 */
	public static boolean isMatch(Object obj, String pattern) {
		if (obj == null) return false;
		String s = obj.toString();
		return s != null && s.matches(pattern);
	}

	/**
	 * 判断对象是否不匹配指定的正则表达式
	 * 如果obj为null或者obj.toString()的返回值为null，匹配失败
	 * 否则判断obj.toString()的返回值是否匹配指定的正则表达式
	 *
	 * @param obj     要判断的对象
	 * @param pattern 不能匹配的正则表达式
	 * @return 不匹配返回true，匹配返回false
	 */
	public static boolean isNotMatch(Object obj, String pattern) {
		return !isMatch(obj, pattern);
	}

	/**
	 * 判断对象的值是否在指定范围内[min, max]，包括min和max
	 * 只适用于以下字段类型：
	 * 1. byte、Byte
	 * 2. short、Short
	 * 3. int、Integer
	 * 4. long、Long
	 * 5. float、Float
	 * 6. double、Double
	 * 如果是其他的类型则会抛出异常
	 *
	 * @param value 要判断的对象
	 * @param min   最小值
	 * @param max   最大值
	 * @return 在范围内返回true，不在范围内返回false
	 */
	public static boolean isInRange(Object value, double min, double max) {
		if (value instanceof Byte) {
			byte n = (byte) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}
		if (value instanceof Short) {
			short n = (short) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}
		if (value instanceof Integer) {
			int n = (int) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}
		if (value instanceof Long) {
			long n = (long) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}
		if (value instanceof Float) {
			float n = (float) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}
		if (value instanceof Double) {
			double n = (double) value;
			return Double.compare(n, min) >= 0 && Double.compare(n, max) <= 0;
		}

		throw new IllegalArgumentException("The type of " + value.getClass() + " is not supported");
	}

	/**
	 * 判断对象的值是否不在指定范围内[min, max]，包括min和max
	 * 只适用于以下字段类型：
	 * 1. byte、Byte
	 * 2. short、Short
	 * 3. int、Integer
	 * 4. long、Long
	 * 5. float、Float
	 * 6. double、Double
	 * 如果是其他的类型则会抛出异常
	 *
	 * @param value 要判断的对象
	 * @param min   最小值
	 * @param max   最大值
	 * @return 不在范围内返回true，在范围内返回false
	 */
	public static boolean isNotInRange(Object value, double min, double max) {
		return !isInRange(value, min, max);
	}

	private ObjectUtils() {
		throw new AssertionError();
	}
}
