package eter2.spring_project.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String region;

    @OneToMany(mappedBy = "company")
    private List<Post> posts;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return this.country; }
    public void setCountry(String country) { this.country = country; }
    public String getRegion() { return this.region; }
    public void setRegion(String region) { this.region = region; }
}
