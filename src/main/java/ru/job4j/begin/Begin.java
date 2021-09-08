package ru.job4j.begin;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Begin {
    public String getString() {
        return "String";
    }

    public static void main(String[] args) {
        log.error(new Begin().getString());
    }
}
