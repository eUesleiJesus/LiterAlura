package com.LiterAlura.LiterAlura.services;

public interface IDConverter {
    <T> T getData(String json, Class<T> tclass);
}
