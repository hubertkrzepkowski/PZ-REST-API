package com.example.photop2.Repository;



import com.example.photop2.Model.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Integer> {

  //  @Query(value = "SELECT * FROM photos order by rating desc ", nativeQuery = true)
  //  List<Photos> findPhotosByRating();

  //  @Query(value = "UPDATE ratings set rating=?2 where rating_id=?1", nativeQuery = true)
  //  void UpdateRating(int id ,int rating);

    Ratings findByPhotoIdAndUserId(int photo,int user);

    void deleteAllByphotoId(int id);

    List<Ratings> getAllByuserId(int id);
}
