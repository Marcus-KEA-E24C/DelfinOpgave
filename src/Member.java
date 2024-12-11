import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Member {
    private String name;
    private String ageGroup;
    private boolean isActive;
    private boolean isCompSwimmer;

    private String ageGroupTeam;
    private List<String> swimDisciplines;



    public  Member(String name, String ageGroup, boolean isActive, boolean isCompSwimmer){
        this.name=name;
        this.ageGroup=ageGroup;
        this.isActive=isActive;
        this.isCompSwimmer=isCompSwimmer;
    }

    public String getAgeGroupTeam()
    {
        return ageGroupTeam;
    }


    public List<String> getSwimDisciplines()
    {
        return swimDisciplines;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAgeGroup(){
        return ageGroup;
    }
    public void setAgeGroup(String ageGroup){
        this.ageGroup=ageGroup;
    }
    public boolean isActive(){
        return isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive=isActive;
    }
    public boolean isCompSwimmer(){
        return isCompSwimmer;
    }
    public void setIsCompSwimmer(boolean isCompSwimmer){
        this.isCompSwimmer=isCompSwimmer;


    }


    public void setAgeGroupTeam(String ageGroupTeam) {
        if (isCompSwimmer) {
            this.ageGroupTeam = ageGroupTeam;
        } else {
            throw new IllegalStateException ("Age group team only applicable to comp swimmers");
        }

    }

    public void setSwimDisciplines(List<String> swimDisciplines) {
        if (isCompSwimmer) {
            this.swimDisciplines = swimDisciplines;
        } else {
            throw new IllegalStateException ("Swim disciplines only applicable to comp swimmers");
        }

    }

    public String toString(){
        return "Navn: "+ name + ", Aldersgruppe: "+ageGroup+" , Aktiv: "+( isActive ?"Ja" : "Nej")+
                ", Konkurrencesvømmer: "+(isCompSwimmer ?"Ja":"Nej");
    }
    // Gem medlemmer til en fil
    public static void saveMemberToFile(List<Member> members,String filnavn)throws IOException{
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(filnavn))){
            writer.write("Name;AgeGroup;IsActive;IsCompSwimmer");
            writer.newLine();

            for(Member member:members){
                writer.write(member.getName()+ ";" +
                        member.getAgeGroup() + ";" +
                        member.isActive() + ";" +
                        member.isCompSwimmer());
                writer.newLine();
            }
        }
    }
    // Læs medlemmer fra en fil
    public static List<Member>readMembersFromFile(String filnavn) throws IOException{
        List<Member>members= new ArrayList<>();
        try(BufferedReader reader= new BufferedReader(new FileReader(filnavn))){
            String line;
            while ((line=reader.readLine())!=null){
                String[] data=line.split(";");
                String name= data[0];
                String ageGroup= data[1];
                boolean active=Boolean.parseBoolean(data[2]);
                boolean isCompSwimmer=Boolean.parseBoolean(data[3]);
                members.add(new Member(name,ageGroup, active,isCompSwimmer));
            }
        }
        return members;
    }
    // Samlet metode til at håndtere oprettelse, gemning og læsning
    public static void manageMembers(String filename){
        List<Member> members= new ArrayList<>();
        members.add(new Member("John Doe", "Senior", true, false));
        try{
            saveMemberToFile(members,filename);
            System.out.println("Medlemmer er gemt til filen'"+filename+"'.");
        }catch (IOException e){
            System.out.println("Kunne ikke gemme medlemmer"+e.getMessage());
        }
        try {
            List<Member> loadedMembers = readMembersFromFile(filename);
            System.out.println("Indlæste medlemmer:");
            for (Member member : loadedMembers) {
                System.out.println(member);
            }
        } catch (IOException e) {
            System.out.println("Kunne ikke læse medlemmer fra filen: " + e.getMessage());
        }
    }


    public class SwimDisciplineUtil {
        public static final Set<String> VALID_DISCIPLINES = new HashSet<>(Arrays.asList(
                "crawl", "backstroke", "breaststroke", "butterfly"
        ));
    }


}

