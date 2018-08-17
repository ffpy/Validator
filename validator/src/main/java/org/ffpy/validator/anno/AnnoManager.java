package org.ffpy.validator.anno;

import org.ffpy.validator.utils.AnnoUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注解管理器。
 */
public class AnnoManager {

	/**
	 * 获取字段上的校验注解。
	 *
	 * @param field 要处理的字段
	 * @return 字段上的校验注解列表
	 */
	public static List<Annotation> getValidatorAnnos(Field field) {
		// 获取field上的所有注解
		Annotation[] annos = field.getAnnotations();
		if (annos == null || annos.length == 0) return Collections.emptyList();
		// 校验注解列表
		List<Annotation> annoList = new ArrayList<>();
		// 是校验注解则加入到annoList
		for (Annotation anno : annos) {
			if (AnnoUtils.isValidatorAnno(anno)) annoList.add(anno);
		}
		return annoList;
	}

	private AnnoManager() {
	}
}
