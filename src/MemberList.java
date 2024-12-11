import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberList {

    public ArrayList<Member> list;


    //Konstruktør, der initialiserer liste-attributten
    public MemberList() {
        this.list = new ArrayList<Member>();
    }

    //Metode, der tilføjer Member til MemberList
    public void addMember() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        String ageGroup = "";
        boolean isActive = true;
        boolean isCompSwimmer = false;

        // Opret en ny Member
        Member member = new Member(name, ageGroup, isActive, isCompSwimmer);

        // Indtast medlemmets fulde navn
        System.out.println("Indtast medlemmets fulde navn: ");
        name = scanner.nextLine();
        member.setName(name);

        // Indtast aldersgruppe med korrekt validering
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Indtast medlemmets aldersgruppe: ");
            System.out.println("1. JUNIOR");
            System.out.println("2. SENIOR");
            System.out.println("3. PENSIONIST");
            String addMemberAgeChoice = scanner.nextLine();

            switch (addMemberAgeChoice) {
                case "1":
                    member.setAgeGroup("JUNIOR");
                    validInput = true;
                    break;

                case "2":
                    member.setAgeGroup("SENIOR");
                    validInput = true;
                    break;

                case "3":
                    member.setAgeGroup("PENSIONIST");
                    validInput = true;
                    break;

                default:
                    System.out.println("Ugyldig indtastning, prøv igen.");
            }
        }

        // Spørg om konkurrencesvømmerstatus
        System.out.println("Er medlemmet en konkurrencesvømmer? (ja/nej)");
        String compSwimmerInput = scanner.nextLine();
        if (compSwimmerInput.equalsIgnoreCase("ja")) {
            isCompSwimmer = true;
            member.setIsCompSwimmer(true);

            // Indtast svømmediscipliner for konkurrencesvømmeren
            System.out.println("Indtast svømmediscipliner for konkurrencesvømmeren (adskilt med komma) \n" +
                    "Mulige svømmediscipliner: backstroke, breaststroke, butterfly, crawl");
            String swimDisciplinesInput = scanner.nextLine();
            String[] disciplinesArray = swimDisciplinesInput.split(",");
            ArrayList<String> swimDisciplines = new ArrayList<>();

            for (String discipline : disciplinesArray) {
                String trimmedDiscipline = discipline.trim().toLowerCase();
                if (Member.SwimDisciplineUtil.VALID_DISCIPLINES.contains(trimmedDiscipline)) {
                    swimDisciplines.add(trimmedDiscipline);
                } else {
                    System.out.println("Ugyldig disciplin indtastet: '" + trimmedDiscipline + "'");
                }
            }
            member.setSwimDisciplines(swimDisciplines);
            System.out.println("Følgende discipliner er tilføjet: " + swimDisciplines);
        } else {
            member.setIsCompSwimmer(false);
        }

        // Tilføj medlem til listen
        list.add(member);
        System.out.println("Medlem tilføjet: " + member);
    }

    //Fjerner medlem
    public void removeMember(String name) {
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
    public Member searchMember(String name) {
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
    public void editName(String oldName, String newName) {
        Member member = searchMember(oldName);
        if (member != null) {
            member.setName(newName);
            System.out.println("Navnet ændret fra: " + oldName + "til: " + newName);
        }
    }

    public void editAgeGroup(String name, String newAgeGroup) {
        Member member = searchMember(name); // Søg efter medlemmet baseret på navn
        if (member != null) {
            member.setAgeGroup(newAgeGroup); // Opdater aldersgruppen
            System.out.println("Aldersgruppen for " + name + " er ændret til: " + newAgeGroup);
        } else {
            System.out.println("Medlem med navnet " + name + " blev ikke fundet.");
        }
    }

    public void changeActivityStatus(String name) {
        Member member = searchMember(name);
        if (member != null) {
            boolean currentStatus = member.getIsActive();
            member.setIsActive(!currentStatus);
            System.out.println("Aktivitetsstatus for " + name + " er nu ændret til: " + (!currentStatus) + ".");
        } else {
            System.out.println("Medlem med navnet " + name + " blev ikke fundet.");
        }
    }

    @Override
    public String toString() {
        StringBuilder listStr = new StringBuilder();
        for (Member m : list) {
            listStr.append(m).append("\n");
        }
        return listStr.toString();
    }

    public void saveToFile(String filename) {
        try {
            FileHandler.saveMembersToFile(this.list, filename);
            System.out.println("Medlemmer er gemt til filen '" + filename + "'.");
        } catch (IOException e) {
            System.out.println("Kunne ikke gemme medlemmer: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try {
            List<Member> loadedMembers = FileHandler.readMembersFromFile(filename);
            this.list.clear(); // Ryd listen
            this.list.addAll(loadedMembers); // Tilføj alle medlemmer fra filen
            System.out.println("Medlemmer er indlæst fra filen '" + filename + "'.");
        } catch (IOException e) {
            System.out.println("Kunne ikke læse medlemmer: " + e.getMessage());
        }
    }

}
