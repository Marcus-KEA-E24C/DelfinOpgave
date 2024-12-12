import java.io.IOException;
import java.util.Scanner;

public class Restance {

    private MemberList memberList; // Reference til din MemberList

    // Konstruktør, der tager en MemberList som parameter
    public Restance(MemberList memberList) {
        this.memberList = memberList;
    }

    // Metode til at vise medlemmer med restance
    public void showRestanceMembers() {
        System.out.println("Medlemmer med restance:");
        memberList.list.stream()
                .filter(Member::isInRestance)
                .forEach(member -> System.out.println("- " + member.getName()));
    }
    // Metode til at nulstille betalinger for alle medlemmer
    public  void resetPayment() {
        System.out.println("Nulstiller betalingsstatus for alle medlemmer...");
        for (Member member : memberList.list) {
            member.setRestance(true); // Marker alle medlemmer som i restance
        }

        System.out.println("Alle medlemmer er nu markeret som ikke-betalt.");
        // Gem ændringer automatisk
        try {
            FileHandler.saveMembersToFile(memberList.list, "members.txt");
            System.out.println("Ændringer gemt til 'members.txt'.");
        } catch (IOException e) {
            System.out.println("Fejl under gemning af medlemmer: " + e.getMessage());
        }
    }


    // Metode til at gemme restance-medlemmer til en fil
    public void saveRestanceMembersToFile() {
        System.out.println("Genererer restancefil...");
        try {
            FileHandler.saveRestanceMembersToFile(memberList.list, "restance.txt");
            System.out.println("Restancefil gemt som 'restance.txt'.");
        } catch (IOException e) {
            System.out.println("Fejl under generering af restancefil: " + e.getMessage());
        }
    }

    // Metode til at fjerne medlemmer fra restance
    public void removeRestanceMembers() {
        System.out.println("Medlemmer med restance:");
        int index = 1;
        for (Member member : memberList.list) {
            if (member.isInRestance()) {
                System.out.println(index + ". " + member.getName());
                index++;
            }
        }

        System.out.print("Indtast nummeret på det medlem, der skal fjernes fra restance: ");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());

        index = 1;
        Member memberToRemoveFromRestance = null;
        for (Member member : memberList.list) {
            if (member.isInRestance()) {
                if (index == choice) {
                    memberToRemoveFromRestance = member;
                    break;
                }
                index++;
            }
        }

        if (memberToRemoveFromRestance != null) {
            memberToRemoveFromRestance.setRestance(false); // Fjern restance-flag
            System.out.println(memberToRemoveFromRestance.getName() + " er fjernet fra restance.");
        } else {
            System.out.println("Ugyldigt valg.");
        }
    }

    // Metode til at håndtere hele restance-menuen
    public void handleRestanceMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Håndtering af restance");
            System.out.println("1. Vis medlemmer med restance");
            System.out.println("2. Gem restance-medlemmer til fil");
            System.out.println("3. Fjern restance-medlemmer fra medlemslisten");
            System.out.println("4. Sæt medlem i restance");
            System.out.println("x. Gå tilbage til hovedmenuen");
            System.out.println("Indtast dit valg: ");

            choice = scanner.nextLine(); // Læs brugerens valg

            switch (choice) {
                case "1":
                    // Vis medlemmer med restance
                    showRestanceMembers();
                    break;
                case "2":
                    // Gem restance-medlemmer til fil
                    saveRestanceMembersToFile();
                    break;
                case "3":
                    // Fjern restance-medlemmer fra medlemslisten
                    removeRestanceMembers();
                    break;
                case "4":
                    // Sæt medlem i restance
                    System.out.print("Indtast navnet på medlemmet, der skal sættes i restance: ");
                    String name = scanner.nextLine();
                    memberList.setRestanceForMember(name, true);
                    System.out.println(name + " er nu markeret med restance.");
                    break;
                case "x":
                    // Gå tilbage til hovedmenuen
                    System.out.println("Tilbage til hovedmenuen.");
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
                    break;
            }

        } while (!choice.equals("x")); // Gentag indtil brugeren vælger 'x'
    }
}
