package com.fsw_fpb.sistemacadastro.services.exception;

public class CPFExistsException extends RuntimeException {
    public CPFExistsException(String message) {
        super(message);
    }
}
