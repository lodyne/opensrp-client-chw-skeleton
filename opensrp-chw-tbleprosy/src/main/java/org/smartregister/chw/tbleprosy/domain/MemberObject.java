package org.smartregister.chw.tbleprosy.domain;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.smartregister.util.Utils;

import java.io.Serializable;
import java.util.Date;

public class MemberObject implements Serializable {

    private String familyHeadName;
    private String familyHeadPhoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String gender;
    private String martialStatus;
    private String uniqueId;
    private String age;
    private String dob;
    private String relationalid;
    private String details;
    private String dateChwTBLeprosyTest;
    private String feverTBLeprosyChw;
    private String feverDuration;
    private String dateHfTBLeprosyTest;
    private Date tbleprosyTestDate;
    private String tbleprosyTreat;
    private String famLlin;
    private String llin2Days;
    private String llinCondition;
    private String tbleprosyEduChw;
    private String baseEntityId;
    private String relationalId;
    private String primaryCareGiver;
    private String primaryCareGiverName;
    private String primaryCareGiverPhone;
    private String familyHead;
    private String familyBaseEntityId;
    private String familyName;
    private String phoneNumber;
    private String tbleprosyFollowUpDate;
    private String enrollmentDate;

    public MemberObject() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return Utils.getName(getFirstName(), getLastName());
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getVisitAge() {
        return new Period(new DateTime(dob), new DateTime()).getYears();
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartialStatus() {
        return martialStatus;
    }
    public void setMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRelationalid() {
        return relationalid;
    }

    public void setRelationalid(String relationalid) {
        this.relationalid = relationalid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDateChwTBLeprosyTest() {
        return dateChwTBLeprosyTest;
    }

    public void setDateChwTBLeprosyTest(String dateChwTBLeprosyTest) {
        this.dateChwTBLeprosyTest = dateChwTBLeprosyTest;
    }

    public String getFeverTBLeprosyChw() {
        return feverTBLeprosyChw;
    }

    public void setFeverTBLeprosyChw(String feverTBLeprosyChw) {
        this.feverTBLeprosyChw = feverTBLeprosyChw;
    }

    public String getFeverDuration() {
        return feverDuration;
    }

    public void setFeverDuration(String feverDuration) {
        this.feverDuration = feverDuration;
    }

    public String getDateHfTBLeprosyTest() {
        return dateHfTBLeprosyTest;
    }

    public void setDateHfTBLeprosyTest(String dateHfTBLeprosyTest) {
        this.dateHfTBLeprosyTest = dateHfTBLeprosyTest;
    }

    public Date getTBLeprosyTestDate() {
        return tbleprosyTestDate;
    }

    public void setTBLeprosyTestDate(Date tbleprosyTestDate) {
        this.tbleprosyTestDate = tbleprosyTestDate;
    }

    public String getTBLeprosyTreat() {
        return tbleprosyTreat;
    }

    public void setTBLeprosyTreat(String tbleprosyTreat) {
        this.tbleprosyTreat = tbleprosyTreat;
    }

    public String getFamLlin() {
        return famLlin;
    }

    public void setFamLlin(String famLlin) {
        this.famLlin = famLlin;
    }

    public String getLlin2Days() {
        return llin2Days;
    }

    public void setLlin2Days(String llin2Days) {
        this.llin2Days = llin2Days;
    }

    public String getLlinCondition() {
        return llinCondition;
    }

    public void setLlinCondition(String llinCondition) {
        this.llinCondition = llinCondition;
    }

    public String getTBLeprosyEduChw() {
        return tbleprosyEduChw;
    }

    public void setTBLeprosyEduChw(String tbleprosyEduChw) {
        this.tbleprosyEduChw = tbleprosyEduChw;
    }

    public String getBaseEntityId() {
        return baseEntityId;
    }

    public void setBaseEntityId(String baseEntityId) {
        this.baseEntityId = baseEntityId;
    }

    public String getRelationalId() {
        return relationalId;
    }

    public void setRelationalId(String relationalId) {
        this.relationalId = relationalId;
    }

    public String getFamilyBaseEntityId() {
        return familyBaseEntityId;
    }

    public void setFamilyBaseEntityId(String familyBaseEntityId) {
        this.familyBaseEntityId = familyBaseEntityId;
    }

    public String getPrimaryCareGiver() {
        return primaryCareGiver;
    }

    public void setPrimaryCareGiver(String primaryCareGiver) {
        this.primaryCareGiver = primaryCareGiver;
    }

    public String getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(String familyHead) {
        this.familyHead = familyHead;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getFamilyHeadName() {
        return familyHeadName;
    }

    public void setFamilyHeadName(String familyHeadName) {
        this.familyHeadName = familyHeadName;
    }

    public String getFamilyHeadPhoneNumber() {
        return familyHeadPhoneNumber;
    }

    public void setFamilyHeadPhoneNumber(String familyHeadPhoneNumber) {
        this.familyHeadPhoneNumber = familyHeadPhoneNumber;
    }

    public String getPrimaryCareGiverName() {
        return primaryCareGiverName;
    }

    public void setPrimaryCareGiverName(String primaryCareGiverName) {
        this.primaryCareGiverName = primaryCareGiverName;
    }

    public String getPrimaryCareGiverPhone() {
        return primaryCareGiverPhone;
    }

    public void setPrimaryCareGiverPhone(String primaryCareGiverPhone) {
        this.primaryCareGiverPhone = primaryCareGiverPhone;
    }

    public String getTBLeprosyFollowUpDate() {
        return tbleprosyFollowUpDate;
    }

    public void setTBLeprosyFollowUpDate(String tbleprosyFollowUpDate) {
        this.tbleprosyFollowUpDate = tbleprosyFollowUpDate;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
