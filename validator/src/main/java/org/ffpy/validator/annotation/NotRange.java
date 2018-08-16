package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段必须小于min或大于max，即value &lt; min 或 value &gt; max。
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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotRange {

	/**
	 * 字段名称。
	 *
	 * @return 字段名称
	 */
	String value() default "";

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
