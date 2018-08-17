package org.ffpy.validator.anno;

import org.ffpy.validator.exception.ValidatorException;

import java.util.Map;

/**
 * 注解属性。
 */
public class AnnoAttrs {
	/** 注解属性Map */
	private final Map<String, Object> attrs;

	/**
	 * @param attrs 注解属性Map
	 */
	public AnnoAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

	/**
	 * 获取属性值。属性不存在会抛出异常。
	 *
	 * @param name 属性名
	 * @param <T>  属性值类型
	 * @return 属性值
	 */
	public <T> T get(String name) {
		@SuppressWarnings("unchecked") T o = (T) attrs.get(name);
		if (o == null)
			throw new ValidatorException(name + "属性不存在");
		return o;
	}

	/**
	 * 判断是否存在指定属性。
	 *
	 * @param name 属性名
	 * @return 存在则返回true，否则返回false
	 */
	public boolean contains(String name) {
		return attrs.containsKey(name);
	}

	/**
	 * 获取注解属性Map。
	 *
	 * @return 注解属性Map
	 */
	public Map<String, Object> getAttrs() {
		return attrs;
	}
}
