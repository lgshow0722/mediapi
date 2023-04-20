package com.medi.mediapi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonResponse implements Serializable {

//	private static final String DEFAULT_FAIL_MESSAGE = "시스템 오류가 발생하였습니다.\n문제가 계속될 경우 관리자에게 문의해주세요.";

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private Object data;

}
