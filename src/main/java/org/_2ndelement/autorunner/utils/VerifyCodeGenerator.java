package org._2ndelement.autorunner.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

import static org._2ndelement.autorunner.Constants.CHARACTERS;
import static org._2ndelement.autorunner.Constants.CODE_LENGTH;

@Component
public class VerifyCodeGenerator {

    public String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }
}
