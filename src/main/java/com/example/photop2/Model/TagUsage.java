package com.example.photop2.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name="tag_usage")

public class TagUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_usage_id")
    private Integer tagUsageID;
    @Column(name="photo_id")
    private Integer photId;
    @Column(name="tag_id")
    private Integer tagId;

    public TagUsage (){}

    public TagUsage(Integer photId, Integer tagId) {
        this.photId = photId;
        this.tagId = tagId;
    }


    public Integer getTagUsageID() {
        return tagUsageID;
    }

    public void setTagUsageID(Integer tagUsageID) {
        this.tagUsageID = tagUsageID;
    }

    public Integer getPhotId() {
        return photId;
    }

    public void setPhotId(Integer photId) {
        this.photId = photId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }





}
