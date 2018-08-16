package org.ffpy.validator.constant;

/**
 * 校验注解枚举类
 */
public enum AnnotationEnum {
	/** 代表@{@link org.ffpy.validator.annotation.Null} */
    NULL,
	/** 代表@{@link org.ffpy.validator.annotation.NotNull} */
    NOT_NULL,
	/** 代表@{@link org.ffpy.validator.annotation.Empty} */
    EMPTY,
	/** 代表@{@link org.ffpy.validator.annotation.NotEmpty} */
    NOT_EMPTY,
	/** 代表@{@link org.ffpy.validator.annotation.Match} */
    MATCH,
	/** 代表@{@link org.ffpy.validator.annotation.NotMatch} */
    NOT_MATCH,
	/** 代表@{@link org.ffpy.validator.annotation.Range} */
    RANGE,
	/** 代表@{@link org.ffpy.validator.annotation.NotRange} */
    NOT_RANGE,
}
