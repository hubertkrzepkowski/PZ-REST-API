package com.example.photop2.Service;

import com.example.photop2.Model.Photos;
import com.example.photop2.Model.Ratings;
import com.example.photop2.Repository.PhotosRepository;
import com.example.photop2.Repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class RatingsService {

    private RatingsRepository ratingsRepository;

    @Autowired
    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public void addRating(Ratings rating){ratingsRepository.save(rating);}

    public Ratings getRating(int photo,int user){ return ratingsRepository.findByPhotoIdAndUserId(photo,user);}

    //public void updateRating(int id ,int rating ){ratingsRepository.UpdateRating(id ,rating);}

    public void deleteByPhotoId(int id){ratingsRepository.deleteAllByphotoId(id);}

    public List<Ratings> getAllByuserId(int id){return ratingsRepository.getAllByuserId(id);}















}
