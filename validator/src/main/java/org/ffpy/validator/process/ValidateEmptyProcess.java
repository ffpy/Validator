package org.ffpy.validator.process;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.utils.ObjectUtils;

import java.util.Map;

/**
 * Empty注解的处理器。
 */
public class ValidateEmptyProcess implements ValidateProcess {
	@Override
	public void process(FieldData fieldData, AnnoAttrs attrs, Object bean) throws ValidateFailException {
		if (ObjectUtils.isNotEmpty(fieldData.getValue())) {
			throw ValidateFailException.newInstance(fieldData, attrs, "empty");
		}
	}
}
