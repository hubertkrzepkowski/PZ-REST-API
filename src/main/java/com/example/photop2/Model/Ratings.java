package com.example.photop2.Model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Positive;


@Entity
    @Table(name="ratings")

    public class Ratings {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="rating_id")
        private int rating_id;
        @Column(name="user_id")
        private int userId;
        @Column(name="photo_id")
        private int photoId;
        @DecimalMax(value = "5" ,message = "qqq")
        @Positive
        @Column(name="rating")
        private Integer rating;

        public Ratings(){}

        public String toString(Ratings r){
            String s = r.getPhoto_id()+""+r.getUser_id()+""+r.getRating()+""+r.getRating_id();
            return s;
        }

        public int getRating_id() {
            return rating_id;
        }

        public void setRating_id(int rating_id) {
            this.rating_id = rating_id;
        }

        public int getUser_id() {
            return userId;
        }

        public void setUser_id(int user_id) {
            this.userId = user_id;
        }

        public int getPhoto_id() {
            return photoId;
        }

        public void setPhoto_id(int photo_id) {
            this.photoId = photo_id;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }




}
