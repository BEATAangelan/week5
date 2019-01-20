package com.example.yuekao.persenter;

import java.util.Map;

public interface IPersenter {
    void stagetRequest(String url, Class clazz);
    void stapostRequest(String url, Map<String, String> map, Class clazz);
}
