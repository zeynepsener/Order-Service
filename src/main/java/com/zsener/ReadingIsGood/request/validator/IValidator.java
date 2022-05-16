package com.zsener.ReadingIsGood.request.validator;

public interface IValidator<E> {

    void validate(E element);
}
