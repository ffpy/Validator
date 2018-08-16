package org.ffpy.validator.sample;

import org.ffpy.validator.Validator;
import org.ffpy.validator.exception.ValidateException;

public class Sample {

	public static void main(String[] args) {
		sample1();
		sample2();
		sample3();
		sample4();
	}

	private static void sample1() {
		System.out.print("sample1: ");
		Person person = new Person();
		try {
			Validator.validate(person);
			System.out.println("校验通过");
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sample2() {
		System.out.print("sample2: ");
		Person person = new Person();
		person.setName("小明");
		try {
			Validator.validate(person);
			System.out.println("校验通过");
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sample3() {
		System.out.print("sample3: ");
		Person person = new Person();
		person.setName("小明");
		person.setPhone("123123123123213");
		try {
			Validator.validate(person);
			System.out.println("校验通过");
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void sample4() {
		System.out.print("sample4: ");
		Person person = new Person();
		person.setName("小明");
		person.setPhone("13848467941");
		try {
			Validator.validate(person);
			System.out.println("校验通过");
		} catch (ValidateException e) {
			System.out.println(e.getMessage());
		}
	}
}
