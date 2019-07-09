package com.example.photop2.Service;

import com.example.photop2.Model.Tags;
import com.example.photop2.Repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class TagsService {

    private TagsRepository tagsRepository;

    @Autowired
    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<Tags> getAllTags() { return tagsRepository.findAll(); }

    public Optional<Tags> getTag(int id) { return tagsRepository.findById(id); }

    public void deleteTag(int id){ tagsRepository.deleteById(id);}


    public void addTag(Tags tag){tagsRepository.save(tag);}

















}
