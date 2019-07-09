package com.example.photop2.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name="photos")

public class Photos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="photo_id")
	private Integer photo_id;
	@Column(name="user_id")
	private Integer userId;
	@Column(name="title")
	@Size(min=2, max=30,message = "Nazwa musi być z przedziału 2 - 30")
	private String title;
	@Column(name="file_path")
	private String filePath;
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


public Photos() {}

    public Photos(PhotoWithTags photoWithTags) {
        this.userId = photoWithTags.getUser_id();
        this.title = photoWithTags.getTitle();
        this.filePath = photoWithTags.getFile_path();
        this.description = photoWithTags.getDescription();
        this.visibility = photoWithTags.isVisibility();
        this.dateOfAddition = photoWithTags.getdateOfAddition();
        this.rating = photoWithTags.getRating();
        this.number_of_ratings =photoWithTags.getNumber_of_ratings();
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
	return filePath;
}
public void setFile_path(String file_path) {
	this.filePath = file_path;
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
}

