package org.ffpy.validator.process;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.field.FieldData;

import java.util.Map;

/**
 * Null注解的处理器。
 */
public class ValidateNullProcess implements ValidateProcess {
	@Override
	public void process(FieldData fieldData, AnnoAttrs attrs, Object bean) throws ValidateFailException {
		if (fieldData.getValue() != null) {
			throw ValidateFailException.newInstance(fieldData, attrs, "null");
		}
	}
}
