package org.ffpy.validator.anno;

import java.lang.annotation.*;

/**
 * 用来标识校验注解。
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatorAnno {
}
