package org.ffpy.validator.field;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 字段信息。
 */
public class FieldData {
	/** 字段名 */
	private String field;
	/** 字段名称 */
	private String name;
	/** 字段值 */
	private Object value;
	/** 字段上的校验注解列表 */
	private List<Annotation> annos;

	/**
	 * @param field 字段名
	 * @param name  字段名称
	 * @param value 字段值
	 * @param annos 字段上的校验注解列表
	 */
	public FieldData(String field, String name, Object value, List<Annotation> annos) {
		this.field = field;
		this.name = name;
		this.value = value;
		this.annos = annos;
	}

	/**
	 * 获取字段名
	 *
	 * @return 字段名
	 */
	public String getField() {
		return field;
	}

	/**
	 * 获取字段名称
	 *
	 * @return 字段名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取字段值
	 *
	 * @return 字段值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 获取字段上的校验注解列表
	 *
	 * @return 字段上的校验注解列表
	 */
	public List<Annotation> getAnnos() {
		return annos;
	}

	@Override
	public String toString() {
		return "FieldData{" +
				"field='" + field + '\'' +
				", name='" + name + '\'' +
				", value=" + value +
				", annos=" + annos +
				'}';
	}
}
