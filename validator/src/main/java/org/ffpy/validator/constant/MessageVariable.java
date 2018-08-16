package org.ffpy.validator.constant;

/**
 * 提示信息中可使用的变量名
 */
public class MessageVariable {
	// 字段名称
    public static final String NAME = "${name}";
    // 字段名
    public static final String FIELD = "${field}";
    // (不能)匹配的正则表达式
    public static final String PATTERN = "${pattern}";
    // 最小值
    public static final String MIN = "${min}";
    // 最大值
    public static final String MAX = "${max}";

    private MessageVariable() {
		throw new AssertionError();
    }
}
