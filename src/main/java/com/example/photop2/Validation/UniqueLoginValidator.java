package com.example.photop2.Validation;

import com.example.photop2.Model.Users;
import com.example.photop2.Repository.UserRepository;
import com.example.photop2.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    @Autowired
    private UsersService usersService;

    public UniqueLoginValidator(){}

   public UniqueLoginValidator(UsersService usersService) {
        this.usersService = usersService;
    }


    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        Users user = usersService.getUserbyLogin(login);
        if(!login.isEmpty()) {
            if (user == null) return true;
        }
        return false;
    }

}
