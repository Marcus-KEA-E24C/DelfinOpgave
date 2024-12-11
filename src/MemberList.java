import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberList {

    public ArrayList<Member> list;


    //Konstruktør, der initialiserer liste-attributten
    public MemberList(){
        this.list = new ArrayList<Member>();
    }

    //Metode, der tilføjer Member til MemberList
    public void addMember(){
        Scanner scanner = new Scanner(System.in);
        String name = "";
        String ageGroup = "";
        boolean isActive = true;
        boolean isCompSwimmer = false;
        Member member = new Member(name, ageGroup, isActive, isCompSwimmer);

        System.out.println("Indtast medlemmets fulde navn: ");
        name = scanner.nextLine();
        member.setName(name);

        boolean validInput = false;


        while (!validInput) {

            System.out.println("Indtast medlemmets aldersgruppe: ");
            System.out.println("1. JUNIOR");
            System.out.println("2. SENIOR");
            System.out.println("3. PENSIONIST");
            int addMemberAgeChoice = scanner.nextInt();
            scanner.nextLine();

            switch (addMemberAgeChoice) {
                case 1:
                    member.setAgeGroup("JUNIOR");
                    validInput = true;
                    break;

                case 2:
                    member.setAgeGroup("SENIOR");
                    validInput = true;
                    break;

                case 3:
                    member.setAgeGroup("PENSIONIST");
                    validInput = true;
                    break;

                default:
                    System.out.println("Ugyldig indtastning, prøv igen.");


            }

            System.out.println("Er medlemmet en konkurrencesvømmer? (ja/nej)");
            String compSwimmerInput = scanner.nextLine();
            if (compSwimmerInput.equalsIgnoreCase("ja")) {
                isCompSwimmer = true;
                member.setIsCompSwimmer(true);

                System.out.println("Indtast svømmediscipliner for konkurrencesvømmeren (adskilt med komma)");
                String swimDisciplinesInput = scanner.nextLine();
                String[] SwimDisciplinesArrayList = swimDisciplinesInput.split(",");
                ArrayList<String> swimDisciplines = new ArrayList<String>();

                for (String disciplines : SwimDisciplinesArrayList) {
                    swimDisciplines.add(disciplines.trim());
                }
                member.setSwimDisciplines(swimDisciplines);


            } else {
                member.setIsCompSwimmer(false);
            }


            /** mulighed for at tilføje svømmediscipliner her
             **/


            break;

        }
        list.add(member);
        System.out.println("Medlem tilføjet: " + member);
    }

    //Fjerner medlem
    public void removeMember(String name){
        for (Member member : list) {
            if (member.getName().toLowerCase().trim().equals(name.toLowerCase().trim())) {
                list.remove(member);
                System.out.println("Medlem fjernet: " + member);
                return;
            }
        }
        System.out.println("Medlem findes ikke");
    }

    //Metode, der søger igennem Members
    public Member searchMember(String name){
        for (Member member : list) {
            if (name.equals(member.getName())) {
                System.out.println("Medlem fundet:" + member);
                return member;
            }
        }
        System.out.println("Hov, dette medlem findes ikke.");
        return null;
    }

    //Metode, der ændre navnet på medlem
    public void editName(String oldName, String newName){
        Member member = searchMember(oldName);
        if (member != null) {
            member.setName(newName);
            System.out.println("Navnet ændret fra: " + oldName + "til: " + newName);
        }
    }

    public void editAgeGroup(String name, String newAgeGroup){
        Member member = searchMember(name); // Søg efter medlemmet baseret på navn
        if (member != null) {
            member.setAgeGroup(newAgeGroup); // Opdater aldersgruppen
            System.out.println("Aldersgruppen for " + name + " er ændret til: " + newAgeGroup);
        } else {
            System.out.println("Medlem med navnet " + name + " blev ikke fundet.");
        }
    }

    @Override
    public String toString(){
        StringBuilder listStr = new StringBuilder();
        for (Member m : list) {
            listStr.append(m).append("\n");
        }
        return listStr.toString();
    }

}