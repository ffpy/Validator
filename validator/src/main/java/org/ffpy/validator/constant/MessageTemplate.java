package org.ffpy.validator.constant;

/**
 * 提示信息模板
 */
public class MessageTemplate {
    public static final String NULL = MessageVariable.NAME + "必须为Null";
    public static final String NOT_NULL = MessageVariable.NAME + "不能为Null";
    public static final String EMPTY = MessageVariable.NAME + "必须为空";
    public static final String NOT_EMPTY = MessageVariable.NAME + "不能为空";
    public static final String MATCH = MessageVariable.NAME + "必须匹配" +
            MessageVariable.PATTERN;
    public static final String NOT_MATCH = MessageVariable.NAME + "不能匹配" +
            MessageVariable.PATTERN;
    public static final String RANGE = MessageVariable.NAME + "必须在[" +
            MessageVariable.MIN + ", " + MessageVariable.MAX + "]范围之内";

    public static final String NOT_RANGE = MessageVariable.NAME + "必须在[" +
            MessageVariable.MIN + ", " + MessageVariable.MAX + "]范围之外";

    private MessageTemplate() {
        throw new AssertionError();
    }
}
