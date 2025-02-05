package com.pragma.user.adapters.driven.jpa.mysql.exception;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException() {
        super("Document already exists");
    }
}
