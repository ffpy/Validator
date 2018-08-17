package org.ffpy.validator.anno.validate;

import org.ffpy.validator.anno.ValidatorAnno;

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
@Documented
@ValidatorAnno
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Empty2 {

	/**
	 * 字段名称。
	 *
	 * @return 字段名称
	 */
	String value() default "";

	/**
	 * 分组名。
	 *
	 * @return 分组名
	 */
	String[] group() default {};
}
