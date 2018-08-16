package org.ffpy.validator;

import org.ffpy.validator.annotation.Empty;
import org.ffpy.validator.annotation.NotEmpty;
import org.ffpy.validator.exception.ValidateException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ValidatorEmptyTest {

    @Test
    public void testEmpty() throws ValidateException {
        Validator.validate(new Bean1_1());
        Validator.validate(new Bean1_2());
        Validator.validate(new Bean1_3());
        Validator.validate(new Bean1_4());
        Validator.validate(new Bean1_5());
    }

    @Test(expected = ValidateException.class)
    public void testEmptyWithException() throws ValidateException {
        Bean1_1 bean1 = new Bean1_1();
        Bean1_2 bean2 = new Bean1_2();
        Bean1_3 bean3 = new Bean1_3();
        Bean1_4 bean4 = new Bean1_4();
        Bean1_5 bean5 = new Bean1_5();

        bean1.field = "1";
        bean2.field = new Object[1];
        bean3.field = new ArrayList<>();
        bean3.field.add("1");
        bean4.field = new HashMap<>();
        bean4.field.put("1", "a");
        bean5.field = new Object();

        Validator.validate(bean1);
        Validator.validate(bean2);
        Validator.validate(bean3);
        Validator.validate(bean4);
        Validator.validate(bean5);
    }

    @Test
    public void testNotEmpty() throws ValidateException {
        Bean2_1 bean1 = new Bean2_1();
        Bean2_2 bean2 = new Bean2_2();
        Bean2_3 bean3 = new Bean2_3();
        Bean2_4 bean4 = new Bean2_4();
        Bean2_5 bean5 = new Bean2_5();

        bean1.field = "1";
        bean2.field = new Object[1];
        bean3.field = new ArrayList<>();
        bean3.field.add("1");
        bean4.field = new HashMap<>();
        bean4.field.put("1", "a");
        bean5.field = new Object();

        Validator.validate(bean1);
        Validator.validate(bean2);
        Validator.validate(bean3);
        Validator.validate(bean4);
        Validator.validate(bean5);
    }

    @Test(expected = ValidateException.class)
    public void testNotEmptyWithException() throws ValidateException {
        Validator.validate(new Bean2_1());
        Validator.validate(new Bean2_2());
        Validator.validate(new Bean2_3());
        Validator.validate(new Bean2_4());
        Validator.validate(new Bean2_5());
    }

    private class Bean1_1 {
        @Empty("myField")
        private String field;
    }

    private class Bean1_2 {
        @Empty("myField")
        private Object[] field;
    }

    private class Bean1_3 {
        @Empty("myField")
        private Collection<String> field;
    }

    private class Bean1_4 {
        @Empty("myField")
        private Map<String, String> field;
    }

    private class Bean1_5 {
        @Empty("myField")
        private Object field;
    }

    private class Bean2_1 {
        @NotEmpty("myField")
        private String field;
    }

    private class Bean2_2 {
        @NotEmpty("myField")
        private Object[] field;
    }

    private class Bean2_3 {
        @NotEmpty("myField")
        private Collection<String> field;
    }

    private class Bean2_4 {
        @NotEmpty("myField")
        private Map<String, String> field;
    }

    private class Bean2_5 {
        @NotEmpty("myField")
        private Object field;
    }
}