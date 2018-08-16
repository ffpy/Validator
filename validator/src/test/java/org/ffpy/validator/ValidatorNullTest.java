package org.ffpy.validator;

import org.ffpy.validator.annotation.NotNull;
import org.ffpy.validator.annotation.Null;
import org.ffpy.validator.exception.ValidateException;
import org.ffpy.validator.exception.ValidateNotNullException;
import org.ffpy.validator.exception.ValidateNullException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorNullTest {

    @Test
    public void testNull() throws ValidateException {
        Bean1 bean = new Bean1();
        Validator.validate(bean);
    }

    @Test
    public void testNullWithException() {
        ValidateException expectedException = null;
        try {
            Bean1 bean = new Bean1();
            bean.setField(new Object());
            Validator.validate(bean);
        } catch (ValidateException e) {
            expectedException = e;
        }
        assertNotNull(expectedException);
        assertEquals(expectedException.getClass(), ValidateNullException.class);
        assertEquals("field", expectedException.getField());
        assertEquals("myField", expectedException.getName());
        assertEquals("myField必须为Null", expectedException.getMessage());
    }

    @Test
    public void testNotNull() throws ValidateException {
        Bean2 bean = new Bean2();
        bean.setField(new Object());
        Validator.validate(bean);
    }

    @Test
    public void testNotNullWithException() {
        ValidateException expectedException = null;
        try {
            Bean2 bean = new Bean2();
            Validator.validate(bean);
        } catch (ValidateException e) {
            expectedException = e;
        }
        assertNotNull(expectedException);
        assertEquals(expectedException.getClass(), ValidateNotNullException.class);
        assertEquals("field", expectedException.getField());
        assertEquals("myField", expectedException.getName());
        assertEquals("myField不能为Null", expectedException.getMessage());
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