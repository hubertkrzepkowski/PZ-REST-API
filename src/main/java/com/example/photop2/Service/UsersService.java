package com.example.photop2.Service;

import com.example.photop2.Model.Ratings;
import com.example.photop2.Model.Tags;
import com.example.photop2.Model.Users;
import com.example.photop2.Repository.RatingsRepository;
import com.example.photop2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {




        private UserRepository userRepository;

        @Autowired
        public UsersService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public void addUser(Users user){userRepository.save(user);}

        public Optional<Users> getUserById(int id) { return userRepository.findById(id); }

        public void deleteUser(int id){ userRepository.deleteById(id);}

        public Users getUserbyLogin(String login){return userRepository.findByLogin(login);}
        public Users getUserByEmail(String email){return userRepository.findByEmail(email);}



        }




