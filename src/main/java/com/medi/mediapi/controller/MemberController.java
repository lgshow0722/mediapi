package com.medi.mediapi.controller;

import com.medi.mediapi.constant.ResultMessageCode;
import com.medi.mediapi.exception.ApiException;
import com.medi.mediapi.util.JsonResponse;
import com.medi.mediapi.util.UrlConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Slf4j
@RestController
// @CrossOrigin(value = {"http://localhost:3000", "http://183.111.252.163"}, maxAge = 3600)
@RequestMapping("/member")
public class MemberController {

    private final String storeDevUrl = "http://new.devmedi.com";

    private final UrlConnectionUtil urlConfig = new UrlConnectionUtil();

    @GetMapping("")
    public ResponseEntity<JsonResponse> index(
            @RequestParam(value = "hash", required = true) String hash) throws IOException, ParseException {

        String baseDir = "/external/rest/api/member_info";
        String fullUrl = storeDevUrl + baseDir + "?hash=" + hash;

        HttpURLConnection urlCon = urlConfig.UrlConnection(fullUrl);

        JsonResponse response = JsonResponse.builder().build();

        int responseCode = urlCon.getResponseCode();

        if(responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer inputBuffer = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                inputBuffer.append(inputLine);
            }
            in.close();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObjectOri = (JSONObject) jsonParser.parse(inputBuffer.toString());

            if(jsonObjectOri.size()>0) {
                boolean blSuccess = (boolean) jsonObjectOri.get("success");
                String strMessage = (String) jsonObjectOri.get("message");
                JSONObject objData = (JSONObject) jsonObjectOri.get("data");
                if(!blSuccess) {
                    throw new ApiException(ResultMessageCode.ETC, strMessage);
                }
                response.setSuccess(true);
                response.setData(objData);
            } else {
                throw new ApiException(ResultMessageCode.DATA_EMPTY);
            }
        } else {
            throw new ApiException(ResultMessageCode.RESCODE);
        }
        throw new ApiException(ResultMessageCode.RESCODE);
        //return ResponseEntity.ok(response);
    }
}
