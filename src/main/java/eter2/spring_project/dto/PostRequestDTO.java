package eter2.spring_project.dto;

public class PostRequestDTO {
    private Long companyId;
    private String position;
    private Integer compensation;
    private String description;
    private String skills;

    public Long getCompanyId() { return companyId; }
    public String getPosition() {  return position; }
    public Integer getCompensation() { return compensation; }
    public String getDescription() { return description; }
    public String getSkills() { return skills; }

    public PostRequestDTO(Long companyId, String position, Integer compensation, String description, String skills) {
        this.companyId = companyId;
        this.position = position;
        this.compensation = compensation;
        this.description = description;
        this.skills = skills;
    }
}
