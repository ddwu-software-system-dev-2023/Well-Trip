package com.project.welltrip.service;

import com.project.welltrip.domain.User;
import com.project.welltrip.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDtoValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        UserDto userDto = (UserDto) obj;
        User user = userDto.getUser();

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "FIRST_NAME_REQUIRED", "First name is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", "LAST_NAME_REQUIRED", "Last name is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userId", "EMAIL_REQUIRED", "Email address is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.phone", "PHONE_REQUIRED", "Phone number is required.");

        if (userDto.isNewUser()) {
            if (user.getPassword() == null || user.getPassword().length() < 1 ||
                    !user.getPassword().equals(userDto.getConfirmPassword())) {
                errors.reject("PASSWORD_MISMATCH",
                        "Passwords did not match or were not provided. Matching passwords are required.");
            }
        }
        else if (user.getPassword() != null && user.getPassword().length() > 0) {
            if (!user.getPassword().equals(userDto.getConfirmPassword())) {
                errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
            }
        }
    }
}