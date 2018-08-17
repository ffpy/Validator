package org.ffpy.validator;

import org.ffpy.validator.anno.validate.NotNull;
import org.ffpy.validator.anno.validate.Null;
import org.ffpy.validator.exception.ValidateFailException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorNullTest {

    @Test
    public void testNull() throws ValidateFailException {
        Bean1 bean = new Bean1();
        Validator.validateThrow(bean);
    }

    @Test
    public void testNullWithException() {
        ValidateFailException expectedException = null;
        try {
            Bean1 bean = new Bean1();
            bean.setField(new Object());
            Validator.validateThrow(bean);
        } catch (ValidateFailException e) {
            expectedException = e;
        }
        assertNotNull(expectedException);
        assertEquals("field", expectedException.getFieldData().getField());
        assertEquals("myField", expectedException.getFieldData().getName());
        assertEquals("myField必须为null", expectedException.getMessage());
    }

    @Test
    public void testNotNull() throws ValidateFailException {
        Bean2 bean = new Bean2();
        bean.setField(new Object());
        Validator.validateThrow(bean);
    }

    @Test
    public void testNotNullWithException() {
        ValidateFailException expectedException = null;
        try {
            Bean2 bean = new Bean2();
            Validator.validateThrow(bean);
        } catch (ValidateFailException e) {
            expectedException = e;
        }
        assertNotNull(expectedException);
        assertEquals("field", expectedException.getFieldData().getField());
        assertEquals("myField", expectedException.getFieldData().getName());
        assertEquals("myField不能为null", expectedException.getMessage());
    }

    private class Bean1 {
        @Null("myField")
        private Object field;

        void setField(Object field) {
            this.field = field;
        }
    }

    private class Bean2 {
        @NotNull("myField")
        private Object field;

        void setField(Object field) {
            this.field = field;
        }
    }
}