package com.example.demo.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author pdai
 */
public enum ResponseStatus {

    SUCCESS("200", "success"),
    FAIL("500", "failed"),

    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");

    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
            ));

    /**
     * response code
     */
    private final String responseCode;

    /**
     * description.
     */
    private final String description;

    ResponseStatus(String responseCode, String description) {
        this.responseCode = responseCode;
        this.description = description;
    }

    public static List<ResponseStatus> getHttpStatusAll() {
        return HTTP_STATUS_ALL;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getDescription() {
        return description;
    }
}
