package com.message.app.common;

/**
 * The Enum ErrorMessageEnum.
 */
public enum MessageEnum {

	INVALID_USERNAME("Please Enter a valid UserName"),
	INVALID_PASSWORD("Please Enter a valid Password"),
	LOGIN_SUCCESS("Login Successfully");

	public String message;

	MessageEnum(String message) {
			this.message = message;
	}

}
