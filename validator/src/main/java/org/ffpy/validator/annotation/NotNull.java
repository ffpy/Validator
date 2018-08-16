package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段不能为null
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {

	/**
	 * 字段名称
	 */
	String value() default "";
}
