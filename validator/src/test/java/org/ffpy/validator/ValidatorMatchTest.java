package org.ffpy.validator;

import org.ffpy.validator.annotation.Match;
import org.ffpy.validator.annotation.NotMatch;
import org.ffpy.validator.exception.ValidateException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorMatchTest {

    @Test
    public void testMatch() throws ValidateException {
        Bean1 bean = new Bean1("123");
        Validator.validate(bean);
    }

    @Test(expected = ValidateException.class)
    public void testMatchWithException() throws ValidateException {
        Bean1 bean = new Bean1("abc");
        Validator.validate(bean);
    }

    @Test
    public void testNotMatch() throws ValidateException {
        Bean2 bean = new Bean2("abc");
        Validator.validate(bean);
    }

    @Test(expected = ValidateException.class)
    public void testNotMatchWithException() throws ValidateException {
        Bean2 bean = new Bean2("123");
        Validator.validate(bean);
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