package ru.job4j.begin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Begin {
    public String getString() {
        return "String";
    }

    public static void main(String[] args) {
        log.error(new Begin().getString());
    }
}
