package com.example.photop2.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.example.photop2.Model.*;
import com.example.photop2.Repository.UserRepository;
import com.example.photop2.Service.*;
import org.apache.catalina.User;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.photop2.Security.SecurityConstants.SECRET;
import static com.example.photop2.Security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService usersservice;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotosService photosService;
    @Autowired
    private RatingsService ratingsService;
    @Autowired
    private TagUsageService tagUsageService ;

    public UsersController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign")
    public void signUp(@Valid @RequestBody Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setBan_account(false);
        user.setModerator(false);
        user.setVisibility(true);
        user.setBan_on_adding_photos(false);
        user.setBan_on_adding_photos(false);
        userRepository.save(user);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Users>> getUser(@PathVariable int id) {
        Optional<Users> user = usersservice.getUserById(id);
        if (user.equals(null)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable int id,@RequestHeader("Authorization") String token) {
        Boolean isModerator = new GetToken(token).IsModertor();
        if (isModerator == true) {
            deleteUser(id);

        }
    }
    @Transactional
    @DeleteMapping
    public void deleteUser(@RequestHeader("Authorization") String token) {
        int id = new GetToken(token).getUserId();

           deleteUser(id);
        }


    @PutMapping
    public ResponseEntity<List<String>> updateUsuer( @RequestBody Users user,@RequestHeader("Authorization") String token) {
        int id = new GetToken(token).getUserId();
        Boolean isModerotor = new GetToken(token).IsModertor();
        Optional<Users> baza  = null ;
        List<String> errors = new LinkedList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

            if(isModerotor == false) {
                baza = usersservice.getUserById(id);
                if(baza.get()!=null){
                if (user.getName() != null) {
                    baza.get().setName(user.getName());
                }
                if (user.getSurname() != null) {
                    baza.get().setSurname(user.getSurname());
                }
                if (user.getAvatar() != null) {
                    baza.get().setAvatar(user.getAvatar());
                }
                if (user.getDescription() != null) {
                    baza.get().setDescription(user.getDescription());
                }
                if (user.isVisibility() != null) {
                    baza.get().setVisibility(user.isVisibility());
                }

                UserUpdate save = new UserUpdate(baza.get());
                Set<ConstraintViolation<UserUpdate>> violations = validator.validate(save);
                    for (ConstraintViolation<UserUpdate> violation : violations) {
                        errors.add(violation.getMessage());
                    }
                    if(errors.isEmpty()) {
                        usersservice.addUser(baza.get());
                        return new ResponseEntity(HttpStatus.OK);
                    }
                    else return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);

            }
            else {
                if (user.getUser_id() != null) {
                    baza = usersservice.getUserById(user.getUser_id());
                    if (baza.get() != null) {

                        if (user.isBan_on_adding_photos() != null) {
                            baza.get().setBan_on_adding_photos(user.isBan_on_adding_photos());//Moderator do porawy tak jak wyżej
                        }
                        if (user.isBan_to_rate() != null) {
                            baza.get().setBan_to_rate(user.isBan_to_rate());
                        }
                        if (user.isBan_account() != null) {
                            baza.get().setBan_account(user.isBan_account());
                        }
                        if (user.isModerator() != null) {
                            baza.get().setModerator(user.isModerator());
                        }
                        usersservice.addUser(baza.get());
                    }
                }
            }
            }
        return new ResponseEntity( HttpStatus.I_AM_A_TEAPOT);
    }



    public void deleteUser(int id) {

        if (id != 1) {
            List<Photos> photos = photosService.getPhotosByUser(id);
            if (photos.isEmpty()) {

                List<Ratings> ratings = ratingsService.getAllByuserId(id);
                if (ratings.isEmpty() != true) {
                    ratings.forEach(ratings1 -> {
                        ratings1.setUser_id(1);
                        ratingsService.addRating(ratings1);
                    });
                }
                usersservice.deleteUser(id);
            } else {
                photos.forEach(photos1 -> {
                    ratingsService.deleteByPhotoId(photos1.getPhoto_id());
                    tagUsageService.deleteAllByPhotoId(photos1.getPhoto_id());
                });
                List<Ratings> ratings = ratingsService.getAllByuserId(id);
                if (ratings.isEmpty() != true) {
                    ratings.forEach(ratings1 -> {
                        ratings1.setUser_id(1);
                        ratingsService.addRating(ratings1);
                    });

                    photosService.deleteAllByuserID(id);
                    usersservice.deleteUser(id);
                }
            }
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