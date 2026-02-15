package com.example.enrollment;

import java.io.Serializable;

public class EnrollmentData implements Serializable {
    private static final long serialVersionUID = 1L;

    // SECTION 1: Personal Information
    private String applicantStatus;
    private String termYear;
    private String program1;
    private String program2;
    private String academicLevel;
    private String firstName;
    private String middleName;
    private String middleInitial;
    private String lastName;
    private String extension;
    private String dob; // date of birth
    private Integer age;
    private String pob; // place of birth
    private String sex;
    private String civilStatus;
    private String nationality;
    private String citizenship;
    private String religion;
    private String monthlyIncome;
    private Boolean internationalStudent;
    private Boolean indigenous;
    private Boolean _4ps;

    // SECTION 2: Contact Information
    private String street;
    private String city;
    private String province;
    private String zip;
    private String mobile;
    private String landline;
    private String email;
    private String ecName; // Emergency Contact Name
    private String ecRelationship; // Emergency Contact Relationship
    private String ecMobile; // Emergency Contact Mobile

    // IDINAGDAG: SECTION 3: Family Information
    private String fatherName;
    private String fatherOccupation;
    private String fatherContact;
    private String fatherAddress;
    private String motherName;
    private String motherOccupation;
    private String motherContact;
    private String motherAddress;
    private String guardianName;
    private String guardianRelationship;
    private String guardianContact;
    private Integer siblingCount; 
    private String siblingOrder;
    
    
 // IDINAGDAG: SECTION 4: Educational Background
    private String schoolName;
    private String schoolLevel;
    private String yearGraduated; // String para sa flexibility (e.g., '2024')
    private String generalAverage; // String para sa flexibility (e.g., '90.50' or '1.75')
    private String schoolAddress;
    private String shsTrack;
    private String shsStrand;
    private String shsGWA; // Senior High School GWA
    private String collegeCourse;
    private Integer unitsEarned; // Total Units Earned


    // --- CONSTRUCTORS, GETTERS, AND SETTERS ---
    
    public EnrollmentData() {}

    // Section 1 Getters/Setters
    public String getApplicantStatus() { return applicantStatus; }
    public void setApplicantStatus(String applicantStatus) { this.applicantStatus = applicantStatus; }

    public String getTermYear() { return termYear; }
    public void setTermYear(String termYear) { this.termYear = termYear; }

    public String getProgram1() { return program1; }
    public void setProgram1(String program1) { this.program1 = program1; }

    public String getProgram2() { return program2; }
    public void setProgram2(String program2) { this.program2 = program2; }

    public String getAcademicLevel() { return academicLevel; }
    public void setAcademicLevel(String academicLevel) { this.academicLevel = academicLevel; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getMiddleInitial() { return middleInitial; }
    public void setMiddleInitial(String middleInitial) { this.middleInitial = middleInitial; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getExtension() { return extension; }
    public void setExtension(String extension) { this.extension = extension; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getPob() { return pob; }
    public void setPob(String pob) { this.pob = pob; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getCivilStatus() { return civilStatus; }
    public void setCivilStatus(String civilStatus) { this.civilStatus = civilStatus; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getCitizenship() { return citizenship; }
    public void setCitizenship(String citizenship) { this.citizenship = citizenship; }

    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }

    public String getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(String monthlyIncome) { this.monthlyIncome = monthlyIncome; }

    public Boolean getInternationalStudent() { return internationalStudent; }
    public void setInternationalStudent(Boolean internationalStudent) { this.internationalStudent = internationalStudent; }

    public Boolean getIndigenous() { return indigenous; }
    public void setIndigenous(Boolean indigenous) { this.indigenous = indigenous; }

    public Boolean get_4ps() { return _4ps; }
    public void set_4ps(Boolean _4ps) { this._4ps = _4ps; }

    // Section 2 Getters/Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getLandline() { return landline; }
    public void setLandline(String landline) { this.landline = landline; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEcName() { return ecName; }
    public void setEcName(String ecName) { this.ecName = ecName; }

    public String getEcRelationship() { return ecRelationship; }
    public void setEcRelationship(String ecRelationship) { this.ecRelationship = ecRelationship; }

    public String getEcMobile() { return ecMobile; }
    public void setEcMobile(String ecMobile) { this.ecMobile = ecMobile; }

    // IDINAGDAG: Section 3 Getters/Setters
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getFatherOccupation() { return fatherOccupation; }
    public void setFatherOccupation(String fatherOccupation) { this.fatherOccupation = fatherOccupation; }

    public String getFatherContact() { return fatherContact; }
    public void setFatherContact(String fatherContact) { this.fatherContact = fatherContact; }

    public String getFatherAddress() { return fatherAddress; }
    public void setFatherAddress(String fatherAddress) { this.fatherAddress = fatherAddress; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getMotherOccupation() { return motherOccupation; }
    public void setMotherOccupation(String motherOccupation) { this.motherOccupation = motherOccupation; }

    public String getMotherContact() { return motherContact; }
    public void setMotherContact(String motherContact) { this.motherContact = motherContact; }

    public String getMotherAddress() { return motherAddress; }
    public void setMotherAddress(String motherAddress) { this.motherAddress = motherAddress; }

    public String getGuardianName() { return guardianName; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }

    public String getGuardianRelationship() { return guardianRelationship; }
    public void setGuardianRelationship(String guardianRelationship) { this.guardianRelationship = guardianRelationship; }

    public String getGuardianContact() { return guardianContact; }
    public void setGuardianContact(String guardianContact) { this.guardianContact = guardianContact; }

    public Integer getSiblingCount() { return siblingCount; }
    public void setSiblingCount(Integer siblingCount) { this.siblingCount = siblingCount; }

    public String getSiblingOrder() { return siblingOrder; }
    public void setSiblingOrder(String siblingOrder) { this.siblingOrder = siblingOrder; }
    
 // IDINAGDAG: Section 4 Getters/Setters
    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }

    public String getSchoolLevel() { return schoolLevel; }
    public void setSchoolLevel(String schoolLevel) { this.schoolLevel = schoolLevel; }

    public String getYearGraduated() { return yearGraduated; }
    public void setYearGraduated(String yearGraduated) { this.yearGraduated = yearGraduated; }

    public String getGeneralAverage() { return generalAverage; }
    public void setGeneralAverage(String generalAverage) { this.generalAverage = generalAverage; }

    public String getSchoolAddress() { return schoolAddress; }
    public void setSchoolAddress(String schoolAddress) { this.schoolAddress = schoolAddress; }

    public String getShsTrack() { return shsTrack; }
    public void setShsTrack(String shsTrack) { this.shsTrack = shsTrack; }

    public String getShsStrand() { return shsStrand; }
    public void setShsStrand(String shsStrand) { this.shsStrand = shsStrand; }

    public String getShsGWA() { return shsGWA; }
    public void setShsGWA(String shsGWA) { this.shsGWA = shsGWA; }

    public String getCollegeCourse() { return collegeCourse; }
    public void setCollegeCourse(String collegeCourse) { this.collegeCourse = collegeCourse; }

    public Integer getUnitsEarned() { return unitsEarned; }
    public void setUnitsEarned(Integer unitsEarned) { this.unitsEarned = unitsEarned; }
}