package com.ruben.catalogservice.Service.Validators.Exceptions;


import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class BookValidationException extends ValidationException {

    private static final long serialVersionUID = -8340774064473719970L;

    public BookValidationException(final ValidationResult validationResult) {
        super(validationResult);
    }

}