package org.ffpy.validator;

import com.sun.istack.internal.Nullable;
import org.ffpy.validator.anno.validate.*;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.exception.ValidatorException;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.field.ValidatorFieldManager;
import org.ffpy.validator.process.*;
import org.ffpy.validator.utils.AnnoUtils;
import org.ffpy.validator.utils.ArrayUtils;
import org.ffpy.validator.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 校验器。
 */
public class Validator {
	/** 校验注解和处理器的映射 */
	private static final Map<Class<? extends Annotation>, ValidateProcess> handleMapping =
			new ConcurrentHashMap<>();

	static {
		// 初始化
		registerDefaultAnno();
		aliasDefaultAnno();
	}

	/**
	 * 注册内置注解。
	 */
	private static void registerDefaultAnno() {
		register(Null.class, new ValidateNullProcess());
		register(NotNull.class, new ValidateNotNullProcess());
		register(Empty.class, new ValidateEmptyProcess());
		register(NotEmpty.class, new ValidateNotEmptyProcess());
		register(Match.class, new ValidateMatchProcess());
		register(NotMatch.class, new ValidateNotMatchProcess());
		register(Range.class, new ValidateRangeProcess());
		register(NotRange.class, new ValidateNotRangeProcess());
	}

	/**
	 * 设置内置注解的别名。
	 */
	private static void aliasDefaultAnno() {
		alias(Null.class, Null2.class);
		alias(NotNull.class, NotNull2.class);
		alias(Empty.class, Empty2.class);
		alias(NotEmpty.class, NotEmpty2.class);
		alias(Match.class, Match2.class);
		alias(NotMatch.class, NotMatch2.class);
		alias(Range.class, Range2.class);
		alias(NotRange.class, NotRange2.class);
	}

	/**
	 * 校验bean。
	 *
	 * @param bean 要校验的bean
	 * @return 校验成功返回null，校验失败返回提示信息。
	 */
	public static String validate(Object bean) {
		return validate(bean, null);
	}

	/**
	 * 校验bean。
	 *
	 * @param bean  要校验的bean
	 * @param group 指定要校验的分组
	 * @return 校验成功返回null，校验失败返回提示信息。
	 */
	public static String validate(Object bean, @Nullable String group) {
		try {
			validateThrow(bean, group);
		} catch (ValidateFailException e) {
			return e.getMessage();
		}
		return null;
	}

	/**
	 * 校验bean。
	 *
	 * @param bean 要校验的bean
	 * @throws ValidateFailException 校验失败
	 */
	public static void validateThrow(Object bean) throws ValidateFailException {
		validateThrow(bean, null);
	}

	/**
	 * 校验bean。
	 *
	 * @param bean  要校验的bean
	 * @param group 指定要校验的分组
	 * @throws ValidateFailException 校验失败
	 */
	public static void validateThrow(Object bean, @Nullable String group) throws ValidateFailException {
		List<FieldData> fieldDatas = ValidatorFieldManager.getFieldDatas(bean);
		for (FieldData fieldData : fieldDatas) {
			validateField(fieldData, group, bean);
		}
	}

	/**
	 * 注册校验注解和对应的处理器。
	 *
	 * @param annoClass 校验注解类型
	 * @param process   校验的处理器
	 */
	public static void register(Class<? extends Annotation> annoClass, ValidateProcess process) {
		checkValidatorAnno(annoClass);
		handleMapping.put(annoClass, process);
	}

	/**
	 * 给校验注解设置别名注解，别名注解与原注解使用同一个校验处理器。
	 *
	 * @param originalAnnoClass 原注解类型
	 * @param aliasAnnoClass    别名注解类型
	 */
	public static void alias(Class<? extends Annotation> originalAnnoClass,
							 Class<? extends Annotation> aliasAnnoClass) {
		ValidateProcess process = handleMapping.get(originalAnnoClass);
		if (process == null)
			throw new ValidatorException(originalAnnoClass.getName() + "没有注册，设置别名失败");
		checkValidatorAnno(aliasAnnoClass);
		handleMapping.put(aliasAnnoClass, process);
	}

	/**
	 * 校验字段。
	 *
	 * @param fieldData 字段信息
	 * @param group     指定要校验的分组
	 * @param bean      校验的bean
	 * @throws ValidateFailException 校验失败
	 */
	private static void validateField(FieldData fieldData, String group, Object bean) throws ValidateFailException {
		for (Annotation anno : fieldData.getAnnos()) {
			ValidateProcess process = handleMapping.get(AnnoUtils.getAnnotationType(anno));
			// 校验注解没有注册对应的处理器，跳过
			if (process == null) continue;
			// 如果没有指定分组就全部校验
			// 如果注解有指定分组，就校验，否则不校验
			if (StringUtils.isEmpty(group) || ArrayUtils.contains(AnnoUtils.getGroup(anno), group)) {
				process.process(fieldData, AnnoUtils.getAnnoAttrs(anno), bean);
			}
		}
	}

	/**
	 * 检查注解是不是校验注解类型。
	 *
	 * @param annotationType 要检查的注解类型
	 */
	private static void checkValidatorAnno(Class<? extends Annotation> annotationType) {
		if (!AnnoUtils.isValidatorAnno(annotationType))
			throw new ValidatorException(annotationType.getName() + "不是校验注解类型");
	}

	private Validator() {
	}
}
