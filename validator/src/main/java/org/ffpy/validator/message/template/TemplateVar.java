package org.ffpy.validator.message.template;

/**
 * 模板保留属性。
 */
public class TemplateVar {
	/** 保留属性 */
	public static final String[] VARS = {"field", "name", "value"};

	/** 字段名 */
	public static final String FIELD = VARS[0];
	/** 字段名称 */
	public static final String NAME = VARS[1];
	/** 字段值 */
	public static final String VALUE = VARS[2];

	private TemplateVar() {
	}
}
