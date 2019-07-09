package com.example.photop2.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.example.photop2.Model.*;
import com.example.photop2.Service.PhotosService;
import com.example.photop2.Service.RatingsService;
import com.example.photop2.Service.TagUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.photop2.Security.SecurityConstants.SECRET;
import static com.example.photop2.Security.SecurityConstants.TOKEN_PREFIX;


@RestController
@RequestMapping("/photos")
public class PhotosController {
	
	@Autowired
	private PhotosService photosservice;
	@Autowired
    private RatingsService ratingsService;
	@Autowired
    private TagUsageService tagUsageService ;

	@GetMapping
	public List<Photos> getAllPhotos(){return photosservice.getAllPhotosByRating(); }

	@GetMapping(value = "/{b}/{e}")
	public List<Photos> getPhotosByDate(@PathVariable String b ,@PathVariable String e){
		b = b + " " + "00:00:00.0000";
		e = e + " " + "00:00:00.0000";
		Timestamp begin = Timestamp.valueOf(b);
		Timestamp end = Timestamp.valueOf(e);

		return photosservice.getPhotosByDate(begin,end);
	}

	@GetMapping(value = "/{user_id}")
	public List<Photos> getPhotosByUser(@PathVariable int user_id){

		return photosservice.getPhotosByUser(user_id);
	}

	@GetMapping(value = "/tag/{tag}")
	public List<Photos> getPhotosByTag(@PathVariable String tag){

		return photosservice.getPhotosByTag(tag);
	}


	@GetMapping(value = "/photo/{id}")
	public ResponseEntity<Optional<Photos>> getPhoto(@PathVariable int id,@RequestHeader("Authorization") String token) {
		Optional<Photos> photo = photosservice.getPhoto(id);
        PhotosWithUserRating photosWithUserRating = new PhotosWithUserRating(photo.get());
		int idjwt = new GetToken(token).getUserId();
		Ratings rating = ratingsService.getRating(id,idjwt);
		List<String>  tags = tagUsageService.getAllTagsForPhoto(id);
		if(tags!=null){photosWithUserRating.setTags(tags);}
		if(rating!=null) {
		    photosWithUserRating.setCurrentUserRating(rating.getRating());
           // photosWithUserRating.setPhoto_id(rating.getPhoto_id());//chyba niepotrzebne

		}
		if (photo.equals(null)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(photosWithUserRating, HttpStatus.OK);
	}
    @Transactional
	@DeleteMapping(value = "/{id}")
	public void deletePhoto(@PathVariable int id,@RequestHeader("Authorization") String token) {
		int idjwt = new GetToken(token).getUserId();
		Boolean isModerator = new GetToken(token).IsModertor() ;
		Optional<Photos> photo  = photosservice.getPhoto(id);

		if (!photo.equals(null)) {
		if(photo.get().getUser_id()==idjwt||isModerator == true) {
		    ratingsService.deleteByPhotoId(id);
		    tagUsageService.deleteAllByPhotoId(id);
			photosservice.deletePhoto(id);
		}
		}

		}

	@PostMapping
    public void addPhoto(@Valid @RequestBody PhotoWithTags photoWithTags, @RequestHeader("Authorization") String token){
		int id = new GetToken(token).getUserId();
		photoWithTags.setUser_id(id);
		photoWithTags.setVisibility(true);
		photoWithTags.setRating(0.00);
		Photos photo = new Photos(photoWithTags);
		photosservice.addPhoto(photo);
		Photos photodb =photosservice.getByFilePath(photoWithTags.getFile_path());
		photoWithTags.getTags().forEach(tag->{
		    TagUsage tagUsage = new TagUsage(photodb.getPhoto_id(),tag);
		    tagUsageService.addtagUsage(tagUsage);
		    });


		}

	@PutMapping
    public void updatePhoto(@Valid @RequestBody Photos photo,@RequestHeader("Authorization") String token){
		int id = new GetToken(token).getUserId();

		if(photo.getPhoto_id()!=null){
			Optional<Photos> baza = photosservice.getPhoto(photo.getPhoto_id());
			if(baza.get().getUser_id()==id){
				if(photo.getTitle()!=null){baza.get().setTitle(photo.getTitle()); }
				if(photo.getDescription()!=null){baza.get().setDescription(photo.getDescription());}
				if (photo.isVisibility()!=null){baza.get().setVisibility(photo.isVisibility());}
			}

			photosservice.addPhoto(baza.get());
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
