package org.ffpy.validator.process;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.field.FieldData;

/**
 * 校验处理器的接口。
 */
public interface ValidateProcess {

	/**
	 * 处理过程。
	 * <p>
	 * 校验成功不用抛出异常，校验失败则应抛出ValidateFailException。
	 *
	 * @param fieldData 字段信息
	 * @param attrs     校验注解的属性
	 * @param bean      校验的bean
	 * @throws ValidateFailException 校验失败
	 */
	void process(FieldData fieldData, AnnoAttrs attrs, Object bean) throws ValidateFailException;
}
