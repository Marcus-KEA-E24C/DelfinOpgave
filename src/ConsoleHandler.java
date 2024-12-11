import java.util.Scanner;

public class ConsoleHandler {
    private static MemberList memberList = new MemberList();
    private static Scanner scanner = new Scanner(System.in);
    private static void showFeeOverview() {
        Fee.displayFeeOverview(memberList.list);
    }

    public static void startMenu() {
        memberList.loadFromFile("members.txt");
        System.out.println("\nVelkommen til Delfinens konkurrence-, kontingent- og medlemssystem\n" +
                "SPLASH - Simplifying Planning and Logistics for Aquatic Sports and Handling\n");

        boolean mainMenuRunning = true;

        while (mainMenuRunning) {
            showMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleMemberListMenu();
                    break;
                case "2":
                    memberList.addMember();
                    break;
                case "3":
                    memberList.saveToFile("members.txt");
                    break;
                case "4":
                    memberList.loadFromFile("members.txt");
                    break;
                case "5":
                    showFeeOverview();
                    break;
                case "x":
                    mainMenuRunning = false;
                    System.out.println("Tak for besøget!");
                    memberList.saveToFile("members.txt");
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }

        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\nIndtast venligst hvilken opgave, du vil have udført:");
        System.out.println("1. Se medlemsliste");
        System.out.println("2. Opret nyt medlem");
        System.out.println("3. Gem medlemmer til fil");
        System.out.println("4. Indlæs medlemmer fra fil");
        System.out.println("5. Vis kontingentoversigt");
        System.out.println("x. Luk SPLASH");
    }

    private static void handleMemberListMenu() {
        boolean memberListMenuRunning = true;

        while (memberListMenuRunning) {
            showMemberListMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    memberList.addMember();
                    break;
                case "2":
                    editMember();
                    break;
                case "3":
                    System.out.print("Indtast navnet på medlemmet, der skal slettes: ");
                    String nameToRemove = scanner.nextLine();
                    memberList.removeMember(nameToRemove);
                    break;
                case "x":
                    memberListMenuRunning = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private static void showMemberListMenu() {
        System.out.println("\nMedlemsliste:");
        System.out.println(memberList);
        System.out.println("1. Tilføj nyt medlem");
        System.out.println("2. Rediger medlemsinfo");
        System.out.println("3. Slet medlem");
        System.out.println("x. Gå tilbage til hovedmenu");
    }

    private static void editMember() {
        System.out.print("Indtast navnet på medlemmet, der skal redigeres: ");
        String name = scanner.nextLine();

        System.out.println("\nHvad vil du redigere?");
        System.out.println("1. Navn");
        System.out.println("2. Aldersgruppe");
        System.out.println("3. Aktivitetsstatus");
        System.out.println("x. Afslut redigering");

        String editChoice = scanner.nextLine();

        switch (editChoice) {
            case "1":
                System.out.print("Indtast det nye navn: ");
                String newName = scanner.nextLine();
                memberList.editName(name, newName);
                break;
            case "2":
                System.out.println("Indtast den nye aldersgruppe:");
                System.out.println("1. JUNIOR");
                System.out.println("2. SENIOR");
                System.out.println("3. PENSIONIST");
                int ageGroupChoice = scanner.nextInt();
                scanner.nextLine(); // Ryd input-bufferen
                String newAgeGroup = switch (ageGroupChoice) {
                    case 1 -> "JUNIOR";
                    case 2 -> "SENIOR";
                    case 3 -> "PENSIONIST";
                    default -> {
                        System.out.println("Ugyldigt valg.");
                        yield "";
                    }
                };
                if (!newAgeGroup.isEmpty()) {
                    memberList.editAgeGroup(name, newAgeGroup);
                }
                break;
            case "3":
                System.out.print("Indtast aktivitetsstatus (true for aktiv, false for inaktiv): ");
                boolean newStatus = scanner.nextBoolean();
                scanner.nextLine(); // Ryd input-bufferen
                memberList.editActivityStatus(name, newStatus);
                break;
            case "x":
                System.out.println("Redigering annulleret.");
                break;
            default:
                System.out.println("Ugyldigt valg, prøv igen.");
        }
    }
}
