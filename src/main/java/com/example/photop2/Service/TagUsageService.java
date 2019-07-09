package com.example.photop2.Service;


import com.example.photop2.Model.TagUsage;
import com.example.photop2.Repository.TagUsageRepository;
import com.example.photop2.Repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagUsageService {

    private TagUsageRepository tagUsageRepository;

    @Autowired
    public TagUsageService(TagUsageRepository tagUsageRepository) { this.tagUsageRepository = tagUsageRepository; }

    public void addtagUsage(TagUsage tagUsage){tagUsageRepository.save(tagUsage);}

    public void deleteAllByPhotoId(int id){tagUsageRepository.deleteAllByphotId(id);}

    public List<String> getAllTagsForPhoto(int id){return tagUsageRepository.findAllByphotId(id);}
}
