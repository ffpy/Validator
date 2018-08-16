package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段必须为空。
 * <p>适用于以下情况：
 * <ol>
 *     <li>Object == null</li>
 *     <li>String.isEmpty()</li>
 *     <li>T[].length == 0</li>
 *     <li>Collection.isEmpty()</li>
 *     <li>Map.isEmpty()</li>
 * </ol>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Empty {

	/**
	 * 字段名称。
	 *
	 * @return 字段名称
	 */
	String value() default "";
}
