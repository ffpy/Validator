package org.ffpy.validator.anno.validate;

import org.ffpy.validator.anno.ValidatorAnno;

import java.lang.annotation.*;

/**
 * 表示字段不能匹配指定正则表达式。
 * <p>如果字段的值为null或者字段的toString()的返回值为null，匹配失败。
 * <p>否则，会调用字段的toString()方法，然后进行匹配。
 */
@Documented
@ValidatorAnno
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotMatch2 {

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

	/**
	 * 不能匹配的正则表达式。
	 *
	 * @return 不能匹配的正则表达式
	 */
	String pattern();
}
