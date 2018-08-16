package org.ffpy.validator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Validator测试套件
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ValidatorNullTest.class, ValidatorEmptyTest.class,
		ValidatorMatchTest.class, ValidatorRangeTest.class, ValidatorThreadTest.class})
public class ValidatorSuiteTest {
}
