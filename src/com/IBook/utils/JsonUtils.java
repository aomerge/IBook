package com.IBook.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.IBook.model.BooksModel;

public class JsonUtils {
    public static BooksModel jsonToBooks(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, BooksModel.class);
    }
}
