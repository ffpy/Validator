package org.ffpy.validator.annotation;

import java.lang.annotation.*;

/**
 * 表示字段必须大于或等于min，小于或等于max，即min <= value <= max
 * 只适用于以下字段类型：
 * 1. byte、Byte
 * 2. short、Short
 * 3. int、Integer
 * 4. long、Long
 * 5. float、Float
 * 6. double、Double
 * 如果是其他的类型则会抛出异常
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Range {

	/**
	 * 字段名称
	 */
	String value();

	/**
	 * 最小值
	 */
	double min();

	/**
	 * 最大值
	 */
	double max();
}
