package com.pinapp.challenge.Utils;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.pinapp.challenge.model.Client;

@Component
public class ClientValidator implements Validator {

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", messageSource.getMessage("field.required.error", new Object[]{"birthday"}, null));
        if(client.getBirthday() != null && client.getBirthday().isAfter(LocalDate.now())){
            errors.rejectValue("birthday", messageSource.getMessage("client.invalid.birthday", new Object[]{"birthday"}, null));
        }
    }


}
