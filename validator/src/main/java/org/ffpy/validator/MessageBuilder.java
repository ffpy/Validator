package org.ffpy.validator;

import org.ffpy.validator.constant.MessageVariable;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 校验失败的提示信息的建造者
 */
class MessageBuilder {
	// 字段名
	private String field;
	// 字段名称
	private String name;
	// (不能)匹配的正则表达式
	private String pattern;
	// 最小值
	private Double min;
	// 最大值
	private Double max;

	/**
	 * 根据指定信息创建一个MessageBuilder实例的静态工厂方法
	 *
	 * @param field 字段
	 * @param name  字段名称
	 * @return 对应信息的MessageBuilder实例
	 */
	public static MessageBuilder of(Field field, String name) {
		MessageBuilder builder = new MessageBuilder();
		builder.field = Objects.requireNonNull(field).getName();
		builder.name = Objects.requireNonNull(name);
		return builder;
	}

	/**
	 * 设置${pattern}变量的值
	 *
	 * @param pattern (不能)匹配的正则表达式
	 * @return this
	 */
	public MessageBuilder pattern(String pattern) {
		this.pattern = pattern;
		return this;
	}

	/**
	 * 设置${min}变量的值
	 *
	 * @param min 最小值
	 * @return this
	 */
	public MessageBuilder min(double min) {
		this.min = min;
		return this;
	}

	/**
	 * 设置${max}变量的值
	 *
	 * @param max 最大值
	 * @return this
	 */
	public MessageBuilder max(double max) {
		this.max = max;
		return this;
	}

	/**
	 * 建造校验失败的提醒信息
	 *
	 * @param template 信息模板
	 * @return 校验失败的提醒信息
	 */
	public String build(String template) {
		String s = Objects.requireNonNull(template);
		// 把${field}替换为具体的值
		if (field != null) s = s.replace(MessageVariable.FIELD, field);
		// 把${name}替换为具体的值
		if (name != null) s = s.replace(MessageVariable.NAME, name);
		// 把${pattern}替换为具体的值
		if (pattern != null) s = s.replace(MessageVariable.PATTERN, pattern);
		// 把${min}替换为具体的值
		if (min != null) s = s.replace(MessageVariable.MIN, double2String(min));
		// 把${max}替换为具体的值
		if (max != null) s = s.replace(MessageVariable.MAX, double2String(max));
		return s;
	}

	/**
	 * 浮点数转字符串形式，把字符串末尾的".0"去掉
	 * 例如把"1.0"改为"1"
	 *
	 * @param d 要转换的浮点数
	 * @return 浮点数的字符串形式
	 */
	private String double2String(double d) {
		String s = String.valueOf(d);
		// 如果末尾有".0"，则去掉
		if (s.endsWith(".0")) s = s.substring(0, s.length() - 2);
		return s;
	}

	private MessageBuilder() {
	}
}
