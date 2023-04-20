package com.medi.mediapi.util;

public class JsonResponse {

	private static final String DEFAULT_FAIL_MESSAGE = "시스템 오류가 발생하였습니다.\n문제가 계속될 경우 관리자에게 문의해주세요.";
	private boolean success = false;
	private String message = "";
	private Object data = null;

	public JsonResponse(boolean success) {
		this.success = success;
		this.message = getMessage();
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public JsonResponse setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		if (!this.success && "".equals(message)) {
			this.message = DEFAULT_FAIL_MESSAGE;
		}
		return message;
	}

	/**
	 * 메시지를 입력하지 않고 success가 false인경우 기본 에러메시지가 들어간다.
	 * @param message 상태메세지
	 */
	public JsonResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public JsonResponse setData(Object data) {
		this.data = data;
		return this;
	}
}
