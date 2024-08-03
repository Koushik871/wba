package com.dxc.wba.xref.sso;

public class UsernameAccessException extends RuntimeException {
    private final String jsonMessage;

    public UsernameAccessException(String message, String jsonMessage) {
        super(message);
        this.jsonMessage = jsonMessage;
    }

    public String getJsonMessage() {
        return jsonMessage;
    }
}