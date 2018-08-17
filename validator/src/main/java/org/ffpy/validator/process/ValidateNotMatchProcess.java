package org.ffpy.validator.process;

import org.ffpy.validator.anno.AnnoAttrs;
import org.ffpy.validator.exception.ValidateFailException;
import org.ffpy.validator.exception.ValidatorException;
import org.ffpy.validator.field.FieldData;
import org.ffpy.validator.utils.ObjectUtils;

import java.util.Map;

/**
 * NotMatch注解的处理器。
 */
public class ValidateNotMatchProcess implements ValidateProcess {
	@Override
	public void process(FieldData fieldData, AnnoAttrs attrs, Object bean) throws ValidateFailException {
		if (ObjectUtils.isMatch(fieldData.getValue(), attrs.get("pattern"))) {
			throw ValidateFailException.newInstance(fieldData, attrs, "empty");
		}
	}
}
