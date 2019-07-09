package com.example.photop2.Service;

import com.example.photop2.Model.Photos;
import com.example.photop2.Repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class PhotosService {

private PhotosRepository photosRepository;

    @Autowired
    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
}

    public List<Photos> getAllPhotosByRating() { return photosRepository.findPhotosByRating(); }

    public Optional<Photos> getPhoto(int id) { return photosRepository.findById(id); }

    public void deletePhoto(int id){ photosRepository.deleteById(id);}

    public List<Photos> getPhotosByDate(Timestamp begin ,Timestamp end){
       return photosRepository.findPhotosByDateOfAdditionBetween(begin ,end);}

    public void addPhoto(Photos photo){photosRepository.save(photo);}

    public List<Photos> getPhotosByUser(int id){return photosRepository.findByUserId(id);}

    public List<Photos> getPhotosByTag(String tag){return photosRepository.findPhotosByTag(tag);}

    public void deleteAllByuserID(int id){photosRepository.deleteAllByuserId(id);}

    public Photos getByFilePath(String path){return photosRepository.findByfilePath(path);}
	

	

		
		
		
		
		
		
		
		
	
}
