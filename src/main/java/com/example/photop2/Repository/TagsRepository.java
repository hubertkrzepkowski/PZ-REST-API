package com.example.photop2.Repository;




import com.example.photop2.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Integer> {

    //  @Query(value = "SELECT * FROM photos order by rating desc ", nativeQuery = true)
    //  List<Photos> findPhotosByRating();


}
