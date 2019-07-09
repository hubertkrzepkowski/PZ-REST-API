package com.example.photop2.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.photop2.Model.Ratings;
import com.example.photop2.Service.RatingsService;
import com.example.photop2.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.photop2.Security.SecurityConstants.SECRET;
import static com.example.photop2.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;
    private UsersService usersService;

    @PutMapping
    public void addRating(@Valid @RequestBody Ratings rating,@RequestHeader("Authorization") String token){
        int id = new GetToken(token).getUserId();

        Ratings baza = ratingsService.getRating(rating.getPhoto_id(),id);

        if (baza == null) {
            rating.setUser_id(id);
            ratingsService.addRating(rating);
        }
        else{
           baza.setRating(rating.getRating());
            ratingsService.addRating(baza);
            }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }


}
