package org.ffpy.validator.field;

import org.ffpy.validator.anno.AnnoManager;
import org.ffpy.validator.exception.ValidatorException;
import org.ffpy.validator.utils.AnnoUtils;
import org.ffpy.validator.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 校验字段管理器。
 */
public class ValidatorFieldManager {

	/**
	 * 获取bean上的带有校验注解的字段的字段信息。
	 *
	 * @param bean 要处理的bean
	 * @return 带有校验注解的字段列表
	 */
	public static List<FieldData> getFieldDatas(Object bean) {
		List<FieldData> fieldDatas = new ArrayList<>();
		// 获取bean上的字段
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 设置访问权限
			if (!field.isAccessible()) field.setAccessible(true);
			FieldData fieldData = getFieldData(field, bean);
			// 字段上没有校验注解则跳过
			if (fieldData == null) continue;
			fieldDatas.add(fieldData);
		}
		return fieldDatas;
	}

	/**
	 * 获取校验字段上的字段信息。
	 *
	 * @param field 要处理的字段
	 * @param bean 字段的Bean
	 * @return 字段信息。如果字段上没有校验注解就返回null。
	 */
	public static FieldData getFieldData(Field field, Object bean) {
		// 获取字段上的校验注解
		List<Annotation> validatorAnnos = AnnoManager.getValidatorAnnos(field);
		// 字段上没有校验注解则跳过
		if (validatorAnnos == null || validatorAnnos.isEmpty()) return null;
		// 获取字段名
		String fieldName = field.getName();
		// 获取字段名称
		String name = getName(field, validatorAnnos);
		try {
			// 获取字段值
			Object value = field.get(bean);
			// 添加到字段信息列表上
			return new FieldData(fieldName, name, value, validatorAnnos);
		} catch (IllegalAccessException e) {
			throw new ValidatorException("读取字段" + field.getName() + "失败", e);
		}
	}

	/**
	 * 获取字段的名称。
	 *
	 * @param field 字段
	 * @param annos 字段上的校验注解列表
	 * @return 字段名称
	 */
	private static String getName(Field field, List<Annotation> annos) {
		// 字段名称
		String name = "";
		// 获取校验注解上设置的字段名称
		for (Annotation anno : annos) {
			name = AnnoUtils.getValue(anno);
			if (StringUtils.isNotEmpty(name)) break;
		}
		// 字段上所有的校验注解都没有设置字段名称，使用默认值
		if (StringUtils.isEmpty(name))
			name = field.getName();
		return name;
	}

	private ValidatorFieldManager() {
	}
}
