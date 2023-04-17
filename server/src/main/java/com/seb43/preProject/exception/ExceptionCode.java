package com.seb43.preProject.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404,"Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    POST_NOT_FOUND(404,"Post not found"),
    NO_PERMESSION(403, "You have no authority to order such an action"),
    ALREADY_VOTED(403, "already voted"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    COMMENT_NOT_FOUND(404,"Comment not found"),
    ALREADY_COMPLETED(405,"Method Not Allowed");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
