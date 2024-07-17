package com.LiterAlura.LiterAlura.services;

import com.LiterAlura.LiterAlura.modules.IDAuthor;
import com.LiterAlura.LiterAlura.modules.IDBook;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterData implements IDConverter {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> tclass) {
        T result = null;
        try {
            if (tclass == IDBook.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0);
                result = mapper.treeToValue(s, tclass);
            }else if (tclass == IDAuthor.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0).get("authors").get(0);
                result = mapper.treeToValue(s, tclass);
            }else {
                result = mapper.readValue(json, tclass);
            }
        }catch (JsonProcessingException e){
            e.getStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
