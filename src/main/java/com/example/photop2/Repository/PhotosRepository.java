package com.example.photop2.Repository;


import com.example.photop2.Model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PhotosRepository extends JpaRepository<Photos,Integer> {

    @Query(value = "SELECT * FROM photos order by rating desc ", nativeQuery = true)
    List<Photos> findPhotosByRating();

   // List<Photos> findPhotosByDate_of_additionBetween(Timestamp b ,Timestamp e);
    List<Photos> findPhotosByDateOfAdditionBetween(Timestamp b ,Timestamp e);

    List<Photos> findByUserId (int user_id);

    @Query(value = "select photos.photo_id,user_id,title,file_path,description,visibility,date_of_addition,rating,number_of_ratings from photos " +
            "inner join tag_usage u on photos.photo_id = u.photo_id inner join tags t on u.tag_id = t.tag_id where name =?1 \n", nativeQuery = true)
    List<Photos> findPhotosByTag(String tag);

    void deleteAllByuserId(int id);

    Photos findByfilePath(String path);

}
