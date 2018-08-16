package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段必须匹配指定正则表达式
 * 如果字段的值为null或者字段的toString()的返回值为null，匹配失败
 * 否则，会调用字段的toString()方法，然后进行匹配
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Match {

	/**
	 * 字段名称
	 */
	String value();

	/**
	 * 匹配的正则表达式
	 */
	String pattern();
}
