package org.ffpy.validator.anno.validate;

import org.ffpy.validator.anno.ValidatorAnno;

import java.lang.annotation.*;

/**
 * 表示字段必须大于或等于min，小于或等于max，即min &lt;= value &lt;= max。
 * <p>只适用于以下字段类型：
 * <ol>
 *     <li>byte、Byte</li>
 *     <li>short、Short</li>
 *     <li>int、Integer</li>
 *     <li>long、Long</li>
 *     <li>float、Float</li>
 *     <li>double、Double</li>
 * </ol>
 * <p>如果是其他的类型则会抛出异常。
 */
@Documented
@ValidatorAnno
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range2 {

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
	 * 最小值。
	 *
	 * @return 最小值
	 */
	double min();

	/**
	 * 最大值。
	 *
	 * @return 最大值
	 */
	double max();
}
