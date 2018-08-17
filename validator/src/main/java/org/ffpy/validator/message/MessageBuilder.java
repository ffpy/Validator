package org.ffpy.validator.message;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidatorException;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.message.template.TemplateManager;
import org.ffpy.validator.message.template.TemplateVar;
import org.ffpy.validator.utils.ArrayUtils;
import org.ffpy.validator.utils.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 校验失败的提示信息的建造器。
 */
public class MessageBuilder {
	private Map<String, Object> attrs = new HashMap<>();

	/**
	 * 根据指定信息创建一个MessageBuilder实例。
	 *
	 * @param fieldData 字段信息
	 * @return 对应信息的MessageBuilder实例
	 */
	public static MessageBuilder of(FieldData fieldData) {
		MessageBuilder builder = new MessageBuilder();
		builder.attrs.put(TemplateVar.FIELD, fieldData.getField());
		builder.attrs.put(TemplateVar.NAME, fieldData.getName());
		builder.attrs.put(TemplateVar.VALUE, fieldData.getValue());
		return builder;
	}

	/**
	 * 获取属性值。
	 *
	 * @param name 属性名
	 * @return 属性值，属性不存在则返回null
	 */
	public Object getAttr(String name) {
		return attrs.get(name);
	}

	/**
	 * 添加属性。
	 *
	 * @param name  属性名
	 * @param value 属性值
	 * @return 自身实例
	 */
	public MessageBuilder addAttr(String name, Object value) {
		Objects.requireNonNull(name, "name不能为null");
		if (isRetainAttr(name))
			throw new ValidatorException(name + "是保留属性，不能添加");
		attrs.put(name, value);
		return this;
	}

	/**
	 * 添加多个属性。
	 * <p>
	 * 如果是保留属性则会忽略
	 *
	 * @param map 属性集合
	 * @return 自身实例
	 */
	public MessageBuilder addAttrs(Map<String, Object> map) {
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			if (isRetainAttr(it.next())) it.remove();
		}
		attrs.putAll(map);
		return this;
	}

	/**
	 * 添加注解属性。
	 * <p>
	 * 如果是保留属性则会忽略
	 *
	 * @param annoAttrs 注解属性
	 * @return 自身实例
	 */
	public MessageBuilder addAttrs(AnnoAttrs annoAttrs) {
		addAttrs(annoAttrs.getAttrs());
		return this;
	}

	/**
	 * 建造校验失败的提醒信息。
	 *
	 * @param template 信息模板
	 * @return 校验失败的提醒信息
	 */
	public String buildWith(String template) {
		String s = Objects.requireNonNull(template);
		for (Map.Entry<String, Object> e : attrs.entrySet()) {
			String valueStr;
			if (e.getValue() instanceof Float)
				valueStr = StringUtils.float2String((Float) e.getValue());
			else if (e.getValue() instanceof Double)
				valueStr = StringUtils.double2String((Double) e.getValue());
			else
				valueStr = String.valueOf(e.getValue());
			s = s.replaceAll("(?<!\\$)\\$\\{" + e.getKey() + "\\}", valueStr);
		}
		return s;
	}

	/**
	 * 建造校验失败的提醒信息。
	 *
	 * @param templateId 信息模板ID
	 * @return 校验失败的提醒信息
	 */
	public String build(String templateId) {
		return buildWith(TemplateManager.getTemplate(templateId));
	}

	/**
	 * 判断是不是保留属性。
	 *
	 * @return 是则返回true，否则返回false
	 */
	private static boolean isRetainAttr(String name) {
		// TemplateVar中的字段为保留属性
		return ArrayUtils.contains(TemplateVar.VARS, name);
	}

	private MessageBuilder() {
	}
}
