package eter2.spring_project.dto;

import eter2.spring_project.entity.Post;

public class PostResponseDTO {
    private Long id;
    private String name;
    private String country;
    private String region;
    private String position;
    private Integer compensation;
    private String skills;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.name = post.getCompany().getName();
        this.country = post.getCompany().getCountry();
        this.region = post.getCompany().getRegion();
        this.position = post.getPosition();
        this.compensation = post.getCompensation();
        this.skills = post.getSkills();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getPosition() {
        return position;
    }

    public Integer getCompensation() {
        return compensation;
    }

    public String getSkills() {
        return skills;
    }
}
