package org.ffpy.validator;

import org.ffpy.validator.anno.validate.Match;
import org.ffpy.validator.anno.validate.NotMatch;
import org.ffpy.validator.exception.ValidateFailException;
import org.junit.Test;

public class ValidatorMatchTest {

    @Test
    public void testMatch() throws ValidateFailException {
        Bean1 bean = new Bean1("123");
        Validator.validateThrow(bean);
    }

    @Test(expected = ValidateFailException.class)
    public void testMatchWithException() throws ValidateFailException {
        Bean1 bean = new Bean1("abc");
        Validator.validateThrow(bean);
    }

    @Test
    public void testNotMatch() throws ValidateFailException {
        Bean2 bean = new Bean2("abc");
        Validator.validateThrow(bean);
    }

    @Test(expected = ValidateFailException.class)
    public void testNotMatchWithException() throws ValidateFailException {
        Bean2 bean = new Bean2("123");
        Validator.validateThrow(bean);
    }

    private class Bean1 {
        @Match(value = "myField", pattern = "\\d+")
        private String field;

        public Bean1(String field) {
            this.field = field;
        }
    }

    private class Bean2 {
        @NotMatch(value = "myField", pattern = "\\d+")
        private String field;

        public Bean2(String field) {
            this.field = field;
        }
    }
}