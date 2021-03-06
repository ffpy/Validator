package org.ffpy.validator;

import org.ffpy.validator.anno.validate.NotRange;
import org.ffpy.validator.exception.ValidateFailException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 性能测试
 */
public class ValidatorPerformanceTest {

    @Test
    public void test() throws ValidateFailException {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Bean bean = new Bean();
            Validator.validateThrow(bean);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - startTime) + "ms");
    }

    private class Bean {
        @NotRange(value = "myField0", min = 1, max = 10)
        private int field0 = 11;
        @NotRange(value = "myField1", min = 1, max = 10)
        private int field1 = 11;
        @NotRange(value = "myField2", min = 1, max = 10)
        private int field2 = 11;
        @NotRange(value = "myField3", min = 1, max = 10)
        private int field3 = 11;
        @NotRange(value = "myField4", min = 1, max = 10)
        private int field4 = 11;
        @NotRange(value = "myField5", min = 1, max = 10)
        private int field5 = 11;
        @NotRange(value = "myField6", min = 1, max = 10)
        private int field6 = 11;
        @NotRange(value = "myField7", min = 1, max = 10)
        private int field7 = 11;
        @NotRange(value = "myField8", min = 1, max = 10)
        private int field8 = 11;
        @NotRange(value = "myField9", min = 1, max = 10)
        private int field9 = 11;

        private int field10;
        private int field11;
        private int field12;
        private int field13;
        private int field14;
        private int field15;
        private int field16;
        private int field17;
        private int field18;
        private int field19;
        private int field20;
    }
}