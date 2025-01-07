package com.JavaSpringBoot.Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int categoryId;

    @Column(unique = true, nullable = false)
    private String tag;

    @ManyToMany(mappedBy = "categoryList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Match> matchList = new ArrayList<>();

    public Category() {}

    public Category(int categoryId, String tag, List<Match> matchList) {
        this.categoryId = categoryId;
        this.tag = tag;
        this.matchList = matchList;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}
