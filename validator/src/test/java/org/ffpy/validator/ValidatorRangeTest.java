package org.ffpy.validator;

import org.ffpy.validator.anno.validate.NotRange;
import org.ffpy.validator.anno.validate.Range;
import org.ffpy.validator.exception.ValidateFailException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorRangeTest {

    @Test
    public void testRange() throws ValidateFailException {
        Validator.validateThrow(new Bean1_1(2));
        Validator.validateThrow(new Bean1_2(2));
        Validator.validateThrow(new Bean1_3(2));
        Validator.validateThrow(new Bean1_4(2));
    }

    @Test(expected = ValidateFailException.class)
    public void testRangeWithException() throws ValidateFailException {
        Validator.validateThrow(new Bean1_1(0));
        Validator.validateThrow(new Bean1_1(11));
        Validator.validateThrow(new Bean1_2(0));
        Validator.validateThrow(new Bean1_2(11));
        Validator.validateThrow(new Bean1_3(0));
        Validator.validateThrow(new Bean1_3(11));
        Validator.validateThrow(new Bean1_4(0));
        Validator.validateThrow(new Bean1_4(11));
    }

    @Test
    public void testNotRange() throws ValidateFailException {
        Validator.validateThrow(new Bean2_1(0));
        Validator.validateThrow(new Bean2_1(11));
        Validator.validateThrow(new Bean2_2(0));
        Validator.validateThrow(new Bean2_2(11));
        Validator.validateThrow(new Bean2_3(0));
        Validator.validateThrow(new Bean2_3(11));
        Validator.validateThrow(new Bean2_4(0));
        Validator.validateThrow(new Bean2_4(11));
    }

    @Test(expected = ValidateFailException.class)
    public void testNotRangeWithException() throws ValidateFailException {
        Validator.validateThrow(new Bean2_1(2));
        Validator.validateThrow(new Bean2_2(2));
        Validator.validateThrow(new Bean2_3(2));
        Validator.validateThrow(new Bean2_4(2));
    }

    private class Bean1_1 {
        @Range(value = "myField", min = 1, max = 10)
        private int field;

        public Bean1_1(int field) {
            this.field = field;
        }
    }

    private class Bean1_2 {
        @Range(value = "myField", min = 1, max = 10)
        private long field;

        public Bean1_2(long field) {
            this.field = field;
        }
    }

    private class Bean1_3 {
        @Range(value = "myField", min = 1, max = 10)
        private float field;

        public Bean1_3(float field) {
            this.field = field;
        }
    }

    private class Bean1_4 {
        @Range(value = "myField", min = 1, max = 10)
        private double field;

        public Bean1_4(double field) {
            this.field = field;
        }
    }

    private class Bean2_1 {
        @NotRange(value = "myField", min = 1, max = 10)
        private int field;

        public Bean2_1(int field) {
            this.field = field;
        }
    }

    private class Bean2_2 {
        @NotRange(value = "myField", min = 1, max = 10)
        private long field;

        public Bean2_2(long field) {
            this.field = field;
        }
    }

    private class Bean2_3 {
        @NotRange(value = "myField", min = 1, max = 10)
        private float field;

        public Bean2_3(float field) {
            this.field = field;
        }
    }

    private class Bean2_4 {
        @NotRange(value = "myField", min = 1, max = 10)
        private double field;

        public Bean2_4(double field) {
            this.field = field;
        }
    }
}