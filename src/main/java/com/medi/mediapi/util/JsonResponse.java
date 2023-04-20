package com.medi.mediapi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.medi.mediapi.constant.ResultMessageCode;
import lombok.Builder;
import lombok.Data;
import org.json.simple.JSONObject;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonResponse implements Serializable {

	// 성공여부 ( true/false )
	@JsonProperty("success")
	private boolean success;

	// constant에 등록된 응답 유형
	@JsonProperty("message")
	private ResultMessageCode message;

	@JsonProperty("custom_message")
	private String custom_message;

	// 데이터
	@JsonProperty("data")
	private JSONObject data;

}
