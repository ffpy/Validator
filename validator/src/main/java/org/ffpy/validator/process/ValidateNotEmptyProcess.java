package org.ffpy.validator.process;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.utils.ObjectUtils;

import java.util.Map;

/**
 * NotEmpty注解的处理器。
 */
public class ValidateNotEmptyProcess implements ValidateProcess {
	@Override
	public void process(FieldData fieldData, AnnoAttrs attrs, Object bean) throws ValidateFailException {
		if (ObjectUtils.isEmpty(fieldData.getValue())) {
			throw ValidateFailException.newInstance(fieldData, attrs, "empty");
		}
	}
}
