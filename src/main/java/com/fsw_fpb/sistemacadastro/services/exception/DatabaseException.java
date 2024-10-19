package com.fsw_fpb.sistemacadastro.services.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}