package org.ffpy.validator;

import org.ffpy.validator.manager.FieldManager;
import org.ffpy.validator.exception.ValidateException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

/**
 * 字段工具类
 */
class FieldUtils {

	/**
	 * 获取指定对象的所有带校验注解的字段。
	 *
	 * @param obj                                   处理的对象
	 * @param onListValidateAnnotationFieldListener 获取字段的回调接口
	 * @throws ValidateException 校验失败或出错
	 */
	public static void listWithValidateAnnotationFields(
			Object obj, OnListValidateAnnotationFieldListener onListValidateAnnotationFieldListener) throws ValidateException {
		Objects.requireNonNull(obj);
		Objects.requireNonNull(onListValidateAnnotationFieldListener);

		// 获取对象的所有带校验注解的字段
		Class<?> cls = obj.getClass();
		List<Field> fields = FieldManager.getHasValidateAnnotationFields(cls);

		for (Field field : fields) {
			// 设置访问权限
			if (!field.isAccessible()) field.setAccessible(true);
			try {
				// 回调处理
				onListValidateAnnotationFieldListener.forEach(field.get(obj), field);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private FieldUtils() {
		throw new AssertionError();
	}

	/**
	 * 遍历带校验注解的字段的回调接口
	 */
	interface OnListValidateAnnotationFieldListener {
		/**
		 * 回调函数。
		 *
		 * @param value 字段值
		 * @param field 字段
		 * @throws ValidateException 校验失败或出错
		 */
		void forEach(Object value, Field field) throws ValidateException;
	}
}
