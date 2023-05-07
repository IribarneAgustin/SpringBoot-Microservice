package com.pinapp.challenge.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", messageSource.getMessage("field.required.error", new Object[]{"birthday"}, null));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", messageSource.getMessage("field.required.error", new Object[]{"age"}, null));
    }


}
