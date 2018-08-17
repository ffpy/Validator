package org.ffpy.validator.anno.validate;

import org.ffpy.validator.anno.ValidatorAnno;

import java.lang.annotation.*;

/**
 * 表示字段必须为null。
 */
@Documented
@ValidatorAnno
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Null {

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
