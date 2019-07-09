package com.example.photop2.Model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private int tag_id;
    @Column(name="name")
    @Size(min=2, max=30,message = "Tag musi mieć nazwę z przedziału 2 - 30")
    private String name;
    @Column(name="discripton")
    @Size( max=300,message = "Opis moze miec maksymalnie 300 znakow ")
    private String discripton;

    public Tags(){}

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscripton() {
        return discripton;
    }

    public void setDiscripton(String discripton) {
        this.discripton = discripton;
    }




}
