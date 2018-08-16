package org.ffpy.validator.sample;

import org.ffpy.validator.annotation.Match;
import org.ffpy.validator.annotation.NotEmpty;

public class Person {
	private String id;
	@NotEmpty("名字")
	private String name;
	@NotEmpty("手机号")
	@Match(value = "手机号", pattern = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$")
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
