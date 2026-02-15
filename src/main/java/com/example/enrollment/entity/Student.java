package com.example.enrollment.entity;

import jakarta.persistence.Column; // Imported Column
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // NEW FIELD: REFERENCE NUMBER
    // ==========================================
    @Column(unique = true) // This ensures the reference number is unique in the DB
    private String studentNumber;
    
    // ==========================================
    // 1. PERSONAL INFORMATION FIELDS
    // ==========================================
    private String applicantStatus;
    private String termYear;
    private String program1;
    private String program2;
    private String academicLevel;

    private String firstName;
    private String middleName;
    private boolean middleNameNA;
    private String middleInitial;
    private String lastName;
    private String extension; 

    private String dob; 
    private Integer age;
    private String placeOfBirth;
    private String sex;

    private String civilStatus;
    private String nationality;
    private String citizenship;
    private String religion;

    private String monthlyIncome;
    private boolean internationalStudent;
    private boolean indigenous;
    private boolean fourPs;

    // ==========================================
    // 2. CONTACT INFORMATION FIELDS
    // ==========================================
    private String street;
    private String city;
    private String province;
    private String zip;
    
    private String mobile;
    private String landline;
    private String email;
    
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactMobile;

    // ==========================================
    // 3. FAMILY INFO
    // ==========================================
    private String fatherName;
    private String fatherOccupation;
    private String fatherContact;
    private String fatherAddress;

    // Mother
    private String motherName;
    private String motherOccupation;
    private String motherContact;
    private String motherAddress;

    // Guardian
    private String guardianName;
    private String guardianRelationship;
    private String guardianContact;

    // Siblings
    private Integer siblingCount;
    private String siblingOrder;

    // ==========================================
    // 4. EDUCATIONAL BG
    // ==========================================
    private String elementarySchool;
    private String elementaryYear;
    private String elementaryAddress;

    // Junior High
    private String jhsSchool;
    private String jhsYear;
    private String jhsAddress;

    // Senior High
    private String shsSchool;
    private String shsYear;
    private String shsAddress;
    private String shsTrack;

    // College (if Transferee)
    private String lastSchool;
    private String lastSchoolYear;
    private String courseTaken;

    // ==========================================
    // 5. REQUIREMENTS (FILE PATHS)
    // ==========================================
    private String form138Path;
    private String goodMoralPath;
    private String psaBirthCertPath;
    private String idPicturePath;
    private String marriageCertPath;
    private String otherDocPath;

    // ==========================================
    // GETTERS AND SETTERS
    // ==========================================

    // --- Reference Number Getter/Setter ---
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // --- Requirements Path Getters/Setters ---
    public String getForm138Path() { return form138Path; }
    public void setForm138Path(String form138Path) { this.form138Path = form138Path; }

    public String getGoodMoralPath() { return goodMoralPath; }
    public void setGoodMoralPath(String goodMoralPath) { this.goodMoralPath = goodMoralPath; }

    public String getPsaBirthCertPath() { return psaBirthCertPath; }
    public void setPsaBirthCertPath(String psaBirthCertPath) { this.psaBirthCertPath = psaBirthCertPath; }

    public String getIdPicturePath() { return idPicturePath; }
    public void setIdPicturePath(String idPicturePath) { this.idPicturePath = idPicturePath; }

    public String getMarriageCertPath() { return marriageCertPath; }
    public void setMarriageCertPath(String marriageCertPath) { this.marriageCertPath = marriageCertPath; }

    public String getOtherDocPath() { return otherDocPath; }
    public void setOtherDocPath(String otherDocPath) { this.otherDocPath = otherDocPath; }

    // --- Contact Info Getters/Setters ---
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

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactRelationship() { return emergencyContactRelationship; }
    public void setEmergencyContactRelationship(String emergencyContactRelationship) { this.emergencyContactRelationship = emergencyContactRelationship; }

    public String getEmergencyContactMobile() { return emergencyContactMobile; }
    public void setEmergencyContactMobile(String emergencyContactMobile) { this.emergencyContactMobile = emergencyContactMobile; }

    // --- Personal Info Getters/Setters ---
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

    public boolean isMiddleNameNA() { return middleNameNA; }
    public void setMiddleNameNA(boolean middleNameNA) { this.middleNameNA = middleNameNA; }

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

    public String getPlaceOfBirth() { return placeOfBirth; }
    public void setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }

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

    public boolean isInternationalStudent() { return internationalStudent; }
    public void setInternationalStudent(boolean internationalStudent) { this.internationalStudent = internationalStudent; }

    public boolean isIndigenous() { return indigenous; }
    public void setIndigenous(boolean indigenous) { this.indigenous = indigenous; }

    public boolean isFourPs() { return fourPs; }
    public void setFourPs(boolean fourPs) { this.fourPs = fourPs; }

    // --- Family Info Getters/Setters ---
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

    // --- Educational BG Getters/Setters ---
    public String getElementarySchool() { return elementarySchool; }
    public void setElementarySchool(String elementarySchool) { this.elementarySchool = elementarySchool; }

    public String getElementaryYear() { return elementaryYear; }
    public void setElementaryYear(String elementaryYear) { this.elementaryYear = elementaryYear; }

    public String getElementaryAddress() { return elementaryAddress; }
    public void setElementaryAddress(String elementaryAddress) { this.elementaryAddress = elementaryAddress; }

    public String getJhsSchool() { return jhsSchool; }
    public void setJhsSchool(String jhsSchool) { this.jhsSchool = jhsSchool; }

    public String getJhsYear() { return jhsYear; }
    public void setJhsYear(String jhsYear) { this.jhsYear = jhsYear; }

    public String getJhsAddress() { return jhsAddress; }
    public void setJhsAddress(String jhsAddress) { this.jhsAddress = jhsAddress; }

    public String getShsSchool() { return shsSchool; }
    public void setShsSchool(String shsSchool) { this.shsSchool = shsSchool; }

    public String getShsYear() { return shsYear; }
    public void setShsYear(String shsYear) { this.shsYear = shsYear; }

    public String getShsAddress() { return shsAddress; }
    public void setShsAddress(String shsAddress) { this.shsAddress = shsAddress; }

    public String getShsTrack() { return shsTrack; }
    public void setShsTrack(String shsTrack) { this.shsTrack = shsTrack; }

    public String getLastSchool() { return lastSchool; }
    public void setLastSchool(String lastSchool) { this.lastSchool = lastSchool; }

    public String getLastSchoolYear() { return lastSchoolYear; }
    public void setLastSchoolYear(String lastSchoolYear) { this.lastSchoolYear = lastSchoolYear; }

    public String getCourseTaken() { return courseTaken; }
    public void setCourseTaken(String courseTaken) { this.courseTaken = courseTaken; }

}