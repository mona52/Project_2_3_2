package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor

    public class LoginData {
        private final String login;
        private String password;
        private String status;

    }

