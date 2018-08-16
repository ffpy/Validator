package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段必须为null
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Null {

	/**
	 * 字段名称
	 */
	String value();
}
