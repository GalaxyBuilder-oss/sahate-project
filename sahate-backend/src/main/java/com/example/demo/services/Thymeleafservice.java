package com.dapa.dapa.service;

import java.util.Map;

public interface ThymeleafService {
    String createContext(String template, Map<String,Object> variable);
}
