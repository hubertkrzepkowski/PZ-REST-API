package com.example.photop2.Validation;

import com.example.photop2.Model.Users;
import com.example.photop2.Repository.UserRepository;
import com.example.photop2.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UsersService usersService;

    public UniqueEmailValidator(){}

    public UniqueEmailValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {

      Users user = usersService.getUserByEmail(email);
        if(!email.isEmpty()){
            if(user == null) return true;

        }
        return false;
    }


}
