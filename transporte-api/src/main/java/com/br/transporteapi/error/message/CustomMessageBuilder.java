package com.br.transporteapi.error.message;

public class CustomMessageBuilder {

    private String message;
    private int httpStatusCode;
    private String uri;

    public CustomMessageBuilder() {
    }

    public CustomMessage build() {
        return new CustomMessage(this.message, this.httpStatusCode, this.uri);
    }

    public CustomMessageBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomMessageBuilder setHttpStatus(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public CustomMessageBuilder setUri(String uri) {
        this.uri = uri;
        return this;
    }

}
