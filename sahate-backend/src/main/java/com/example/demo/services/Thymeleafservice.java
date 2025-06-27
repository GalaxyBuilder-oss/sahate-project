package com.example.demo.services;

import java.util.Map;

public interface ThymeleafService {
    String createContext(String template, Map<String,Object> variable);
}
