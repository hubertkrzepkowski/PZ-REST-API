package com.example.photop2.Repository;




import com.example.photop2.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    //  @Query(value = "SELECT * FROM photos order by rating desc ", nativeQuery = true)
    //  List<Photos> findPhotosByRating();
     Users findByLogin(String login);
     Users findByEmail(String email);



}
