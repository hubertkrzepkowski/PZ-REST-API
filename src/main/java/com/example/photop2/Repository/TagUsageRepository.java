package com.example.photop2.Repository;




import com.example.photop2.Model.TagUsage;
import com.example.photop2.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TagUsageRepository extends JpaRepository<TagUsage,Integer> {

    //  @Query(value = "SELECT * FROM photos order by rating desc ", nativeQuery = true)
    //  List<Photos> findPhotosByRating();

    void deleteAllByphotId(int id);

      @Query(value = "select name from tags join tag_usage u on tags.tag_id = u.tag_id where u.photo_id =?1 ", nativeQuery = true)
      List<String> findAllByphotId(int id);


}
