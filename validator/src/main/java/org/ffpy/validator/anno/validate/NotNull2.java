package org.ffpy.validator.anno.validate;

import org.ffpy.validator.anno.ValidatorAnno;

import java.lang.annotation.*;

/**
 * 表示字段不能为null。
 */
@Documented
@ValidatorAnno
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull2 {

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
