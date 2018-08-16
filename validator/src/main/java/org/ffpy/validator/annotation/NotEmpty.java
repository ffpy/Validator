package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段不能为空
 * 适用于以下情况：
 * 1. Object != null
 * 2. !String.isEmpty()
 * 3. T[].length != 0
 * 4. !Collection.isEmpty()
 * 5. !Map.isEmpty()
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotEmpty {

	/**
	 * 字段名称
	 */
	String value() default "";
}
