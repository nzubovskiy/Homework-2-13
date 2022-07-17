package com.example.homework213.service;

import com.example.homework213.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class ValidatorService {
    public String validateName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new InvalidInputException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname) {
        String[] surnames = surname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            if (!StringUtils.isAlpha(surnames[i])) {
                throw new InvalidInputException();
            }
            surnames[i] = StringUtils.capitalize(surnames[i].toLowerCase(Locale.ROOT));
        }
        return String.join("-", surnames);
    }

}
