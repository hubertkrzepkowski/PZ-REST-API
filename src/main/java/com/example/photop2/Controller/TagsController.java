package com.example.photop2.Controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.photop2.Model.Tags;
import com.example.photop2.Service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.photop2.Security.SecurityConstants.SECRET;
import static com.example.photop2.Security.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsservice;

    @GetMapping
    public List<Tags> getAllTags(){return tagsservice.getAllTags(); }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Tags>> getTag(@PathVariable int id) {
        Optional<Tags> tag = tagsservice.getTag(id);
        if (tag.equals(null)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tag, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteTag(@PathVariable int id,@RequestHeader("Authorization") String token) {
        Boolean isModerator = new GetToken(token).IsModertor();

        Optional<Tags> tag = tagsservice.getTag(id);
        if(tag!=null) {
            if (isModerator == true) tagsservice.deleteTag(id);
        }
    }

    @PostMapping
    public void addTag(@Valid @RequestBody Tags tag){
        tagsservice.addTag(tag);
    }

   // @PutMapping
   // public void updateTag(@Valid @RequestBody Tags tag){
     //   tagsservice.addTag(tag);
   // }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
