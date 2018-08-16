package org.ffpy.validator;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ObjectUtilsTest {

	@Test
	public void testIsEmpty() {
		assertTrue(ObjectUtils.isEmpty(null));
		assertTrue(ObjectUtils.isEmpty(""));
		assertTrue(ObjectUtils.isEmpty(new Object[0]));
		assertTrue(ObjectUtils.isEmpty(new byte[0]));
		assertTrue(ObjectUtils.isEmpty(new short[0]));
		assertTrue(ObjectUtils.isEmpty(new int[0]));
		assertTrue(ObjectUtils.isEmpty(new long[0]));
		assertTrue(ObjectUtils.isEmpty(new float[0]));
		assertTrue(ObjectUtils.isEmpty(new double[0]));
		assertTrue(ObjectUtils.isEmpty(new boolean[0]));
		assertTrue(ObjectUtils.isEmpty(new char[0]));
		assertTrue(ObjectUtils.isEmpty(new ArrayList<>()));
		assertTrue(ObjectUtils.isEmpty(new HashSet<>()));
		assertTrue(ObjectUtils.isEmpty(new HashMap<>()));

		assertFalse(ObjectUtils.isEmpty(new Object()));
		assertFalse(ObjectUtils.isEmpty("1"));
		assertFalse(ObjectUtils.isEmpty(new Object[]{1}));
		assertFalse(ObjectUtils.isEmpty(new byte[]{1}));
		assertFalse(ObjectUtils.isEmpty(new short[]{1}));
		assertFalse(ObjectUtils.isEmpty(new int[]{1}));
		assertFalse(ObjectUtils.isEmpty(new long[]{1}));
		assertFalse(ObjectUtils.isEmpty(new float[]{1}));
		assertFalse(ObjectUtils.isEmpty(new double[]{1}));
		assertFalse(ObjectUtils.isEmpty(new boolean[]{false}));
		assertFalse(ObjectUtils.isEmpty(new char[]{1}));

		List<Object> list = new ArrayList<>();
		list.add(new Object());
		assertFalse(ObjectUtils.isEmpty(list));

		Set<Object> set = new HashSet<>();
		set.add(new Object());
		assertFalse(ObjectUtils.isEmpty(set));

		Map<String, Object> map = new HashMap<>();
		map.put("1", new Object());
		assertFalse(ObjectUtils.isEmpty(map));
	}

	@Test
	public void testIsNotEmpty() {
		assertFalse(ObjectUtils.isNotEmpty(null));
		assertFalse(ObjectUtils.isNotEmpty(""));
		assertFalse(ObjectUtils.isNotEmpty(new Object[0]));
		assertFalse(ObjectUtils.isNotEmpty(new byte[0]));
		assertFalse(ObjectUtils.isNotEmpty(new short[0]));
		assertFalse(ObjectUtils.isNotEmpty(new int[0]));
		assertFalse(ObjectUtils.isNotEmpty(new long[0]));
		assertFalse(ObjectUtils.isNotEmpty(new float[0]));
		assertFalse(ObjectUtils.isNotEmpty(new double[0]));
		assertFalse(ObjectUtils.isNotEmpty(new boolean[0]));
		assertFalse(ObjectUtils.isNotEmpty(new char[0]));
		assertFalse(ObjectUtils.isNotEmpty(new ArrayList<>()));
		assertFalse(ObjectUtils.isNotEmpty(new HashSet<>()));
		assertFalse(ObjectUtils.isNotEmpty(new HashMap<>()));

		assertTrue(ObjectUtils.isNotEmpty(new Object()));
		assertTrue(ObjectUtils.isNotEmpty("1"));
		assertTrue(ObjectUtils.isNotEmpty(new Object[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new byte[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new short[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new int[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new long[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new float[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new double[]{1}));
		assertTrue(ObjectUtils.isNotEmpty(new boolean[]{false}));
		assertTrue(ObjectUtils.isNotEmpty(new char[]{1}));

		List<Object> list = new ArrayList<>();
		list.add(new Object());
		assertTrue(ObjectUtils.isNotEmpty(list));

		Set<Object> set = new HashSet<>();
		set.add(new Object());
		assertTrue(ObjectUtils.isNotEmpty(set));

		Map<String, Object> map = new HashMap<>();
		map.put("1", new Object());
		assertTrue(ObjectUtils.isNotEmpty(map));
	}

	@Test
	public void testIsMatch() {
		assertTrue(ObjectUtils.isMatch("abc", "\\w+"));
		assertTrue(ObjectUtils.isMatch("123", "\\d+"));
		assertTrue(ObjectUtils.isMatch(123, "\\d+"));
		assertTrue(ObjectUtils.isMatch(123.0, "[\\d.]+"));

		assertFalse(ObjectUtils.isMatch("abc", "\\W+"));
		assertFalse(ObjectUtils.isMatch("123", "\\D+"));
		assertFalse(ObjectUtils.isMatch(123, "\\D+"));
		assertFalse(ObjectUtils.isMatch(123.0, "[\\W.]+"));
	}

	@Test
	public void testIsNotMatch() {
		assertFalse(ObjectUtils.isNotMatch("abc", "\\w+"));
		assertFalse(ObjectUtils.isNotMatch("123", "\\d+"));
		assertFalse(ObjectUtils.isNotMatch(123, "\\d+"));
		assertFalse(ObjectUtils.isNotMatch(123.0, "[\\d.]+"));

		assertTrue(ObjectUtils.isNotMatch("abc", "\\W+"));
		assertTrue(ObjectUtils.isNotMatch("123", "\\D+"));
		assertTrue(ObjectUtils.isNotMatch(123, "\\D+"));
		assertTrue(ObjectUtils.isNotMatch(123.0, "[\\W.]+"));
	}

	@Test
	public void testIsInRange() {
		assertTrue(ObjectUtils.isInRange(5, 1, 10));
		assertTrue(ObjectUtils.isInRange(1, 1, 10));
		assertTrue(ObjectUtils.isInRange(10, 1, 10));
		assertTrue(ObjectUtils.isInRange(1.0, 1, 10));
		assertTrue(ObjectUtils.isInRange(5.0, 1, 10));
		assertTrue(ObjectUtils.isInRange(10.0, 1, 10));

		assertFalse(ObjectUtils.isInRange(0, 1, 10));
		assertFalse(ObjectUtils.isInRange(11, 1, 10));
		assertFalse(ObjectUtils.isInRange(0.0, 1, 10));
		assertFalse(ObjectUtils.isInRange(11.0, 1, 10));
	}

	@Test
	public void testIsNotInRange() {
		assertFalse(ObjectUtils.isNotInRange(5, 1, 10));
		assertFalse(ObjectUtils.isNotInRange(1, 1, 10));
		assertFalse(ObjectUtils.isNotInRange(10, 1, 10));
		assertFalse(ObjectUtils.isNotInRange(1.0, 1, 10));
		assertFalse(ObjectUtils.isNotInRange(5.0, 1, 10));
		assertFalse(ObjectUtils.isNotInRange(10.0, 1, 10));

		assertTrue(ObjectUtils.isNotInRange(0, 1, 10));
		assertTrue(ObjectUtils.isNotInRange(11, 1, 10));
		assertTrue(ObjectUtils.isNotInRange(0.0, 1, 10));
		assertTrue(ObjectUtils.isNotInRange(11.0, 1, 10));
	}
}