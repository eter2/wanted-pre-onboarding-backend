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

    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public void setPosition(String position) { this.position = position; }
    public void setCompensation(Integer compensation) { this.compensation = compensation; }
    public void setDescription(String description) { this.description = description; }
    public void setSkills(String skills) { this.skills = skills; }

    public PostRequestDTO() {}
}
