import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member {
    private String name;
    private String ageGroup;
    private boolean isActive;
    private boolean isCompSwimmer;
    private boolean feeType;

    private String ageGroupTeam;
    private List<String> swimDisciplines;


    public Member(String name, String ageGroup, boolean isActive, boolean isCompSwimmer) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.isActive = isActive;
        this.isCompSwimmer = isCompSwimmer;

    }

    public String getAgeGroupTeam() {
        return ageGroupTeam;
    }


    public List<String> getSwimDisciplines() {
        return swimDisciplines;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isCompSwimmer() {
        return isCompSwimmer;
    }

    public void setIsCompSwimmer(boolean isCompSwimmer) {
        this.isCompSwimmer = isCompSwimmer;


    }


    public void setAgeGroupTeam(String ageGroupTeam) {
        if (isCompSwimmer) {
            this.ageGroupTeam = ageGroupTeam;
        } else {
            throw new IllegalStateException("Age group team only applicable to comp swimmers");
        }

    }

    public void setSwimDisciplines(List<String> swimDisciplines) {
        if (isCompSwimmer) {
            this.swimDisciplines = swimDisciplines;
        } else {
            throw new IllegalStateException("Swim disciplines only applicable to comp swimmers");
        }

    }

    public String toString() {
        return "Navn: " + name + ", Aldersgruppe: " + ageGroup + " , Aktiv: " + (isActive ? "Ja" : "Nej") +
                ", Konkurrencesvømmer: " + (isCompSwimmer ? "Ja" : "Nej");
    }

    }

