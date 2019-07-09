package com.example.photop2.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity


public class PhotosWithUserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_id")
    private Integer photo_id;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="title")
    @Size(min=2, max=30,message = "Nazwa musi być z przedziału 2 - 30")
    private String title;



    private Integer currentUserRating ;
    @Column(name="file_path")
    private String file_path;
    @Column(name="description")
    @Size(max=300,message = "Za długi opis")
    private String description;
    @Column(name="visibility")
    private Boolean visibility;
    @CreationTimestamp
    @Column(name="date_of_addition")
    private Timestamp dateOfAddition ;
    @Column(name="rating")
    private Double rating ;
    @Column(name="number_of_ratings")
    private Integer number_of_ratings;
    @Column(name = "tags")
    @ElementCollection(targetClass=Integer.class)
    private List<String> tags ;


    public PhotosWithUserRating(Photos photo) {
        this.photo_id=photo.getPhoto_id();
        this.userId = photo.getUser_id();
        this.title = photo.getTitle();
        this.currentUserRating = null;
        this.file_path = photo.getFile_path();
        this.description = photo.getDescription();
        this.visibility = photo.isVisibility();
        this.dateOfAddition = photo.getdateOfAddition();
        this.rating = photo.getRating();
        this.number_of_ratings = photo.getNumber_of_ratings();
        this.tags= null ;
    }
    


    public PhotosWithUserRating() {

    }
    public Integer getPhoto_id() {
        return photo_id;
    }
    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }
    public Integer getUser_id() {
        return userId;
    }
    public void setUser_id(Integer user_id) {
        this.userId = user_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFile_path() {
        return file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean isVisibility() {
        return visibility;
    }
    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
    public Timestamp getdateOfAddition() {
        return dateOfAddition;
    }
    public void setdateOfAddition(Timestamp dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public Integer getNumber_of_ratings() {
        return number_of_ratings;
    }
    public void setNumber_of_ratings(Integer number_of_ratings) {
        this.number_of_ratings = number_of_ratings;
    }
    public Integer getCurrentUserRating() {
        return currentUserRating;
    }
    public void setCurrentUserRating(Integer currentUserRating) {
        this.currentUserRating = currentUserRating;
    }
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}

