package com.escolar.exception;

import org.springframework.http.HttpStatus;

import com.escolar.enums.ApiErrorCode;



public class RestApiError {
	
    private final HttpStatus httpStatusCode;
    private final ApiErrorCode apiCode;
    private final String message;
    private final String devMessage;
    private final String infoUrl;

    public RestApiError(HttpStatus httpStatusCode, ApiErrorCode apiCode, String message, String devMessage, String infoUrl) {
        this.httpStatusCode = httpStatusCode;
        this.apiCode = apiCode;
        this.message = message;
        this.devMessage = devMessage;
        this.infoUrl = infoUrl;
    }

    public HttpStatus getHttpStatusCode() {
        return this.httpStatusCode;
    }
 
    public ApiErrorCode getApiCode() {
        return this.apiCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDeveloperMessage() {
        return this.devMessage;
    }

    public String getInfoUrl() {
        return this.infoUrl;
    }
}