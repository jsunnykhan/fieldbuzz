package com.nullstdio.fieldbuzz.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nullstdio.fieldbuzz.models.CvFile;
import com.nullstdio.fieldbuzz.models.CvFileDataModel;

public class UserResponse {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("full_address")
    @Expose
    private String fullAddress;
    @SerializedName("name_of_university")
    @Expose
    private String nameOfUniversity;
    @SerializedName("graduation_year")
    @Expose
    private Integer graduationYear;
    @SerializedName("cgpa")
    @Expose
    private Double cgpa;
    @SerializedName("applying_in")
    @Expose
    private String applyingIn;
    @SerializedName("expected_salary")
    @Expose
    private Integer expectedSalary;
    @SerializedName("field_buzz_reference")
    @Expose
    private String fieldBuzzReference;
    @SerializedName("experience_in_months")
    @Expose
    private Integer experienceInMonths;
    @SerializedName("current_work_place_name")
    @Expose
    private String currentWorkPlaceName;
    @SerializedName("github_project_url")
    @Expose
    private String githubProjectUrl;
    @SerializedName("cv_file")
    @Expose
    private CvFileDataModel cvFile;

    public UserResponse() {
    }

    public UserResponse(String name, String email, String phone, String fullAddress,
                          String nameOfUniversity, Integer graduationYear, Double cgpa,
                          String applyingIn, Integer expectedSalary,
                          String fieldBuzzReference, Integer experienceInMonths,
                          String currentWorkPlaceName,
                          String githubProjectUrl, CvFileDataModel cvFile) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.fullAddress = fullAddress;
        this.nameOfUniversity = nameOfUniversity;
        this.graduationYear = graduationYear;
        this.cgpa = cgpa;
        this.applyingIn = applyingIn;
        this.expectedSalary = expectedSalary;
        this.fieldBuzzReference = fieldBuzzReference;
        this.experienceInMonths = experienceInMonths;
        this.currentWorkPlaceName = currentWorkPlaceName;
        this.githubProjectUrl = githubProjectUrl;
        this.cvFile = cvFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getNameOfUniversity() {
        return nameOfUniversity;
    }

    public void setNameOfUniversity(String nameOfUniversity) {
        this.nameOfUniversity = nameOfUniversity;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public String getApplyingIn() {
        return applyingIn;
    }

    public void setApplyingIn(String applyingIn) {
        this.applyingIn = applyingIn;
    }

    public Integer getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Integer expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getFieldBuzzReference() {
        return fieldBuzzReference;
    }

    public void setFieldBuzzReference(String fieldBuzzReference) {
        this.fieldBuzzReference = fieldBuzzReference;
    }

    public Integer getExperienceInMonths() {
        return experienceInMonths;
    }

    public void setExperienceInMonths(Integer experienceInMonths) {
        this.experienceInMonths = experienceInMonths;
    }

    public String getCurrentWorkPlaceName() {
        return currentWorkPlaceName;
    }

    public void setCurrentWorkPlaceName(String currentWorkPlaceName) {
        this.currentWorkPlaceName = currentWorkPlaceName;
    }

    public String getGithubProjectUrl() {
        return githubProjectUrl;
    }

    public void setGithubProjectUrl(String githubProjectUrl) {
        this.githubProjectUrl = githubProjectUrl;
    }

    public CvFileDataModel getCvFile() {
        return cvFile;
    }

    public void setCvFile(CvFileDataModel cvFile) {
        this.cvFile = cvFile;
    }

}