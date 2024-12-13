import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    private static MemberList memberList = new MemberList();
    private static TrainingResult trainingResult = new TrainingResult();
    private static RaceResult raceResult = new RaceResult();
    private static Scanner scanner = new Scanner(System.in);
    private static TopFive topFive;
    public static TrainingTop5 trainingTop5;

    public ConsoleHandler() {
        // Opretter en instans af TopFive
        this.topFive = new TopFive();
        this.trainingTop5 = new TrainingTop5();
    }

    private static void handleTopFive() {
        System.out.println("Viser top 5 svømmeres bedste konkurrencetider for hver disciplin:");
        topFive.displayTopFive("compresult.txt");
    }


    private static void showFeeOverview() {
        Fee.displayFeeOverview(memberList.list);

    }

    private static void loadRestanceMembersFromFile() {
        try {
            List<Member> restanceMembers = FileHandler.readRestanceMembersFromFile("Restance.txt");
            System.out.println("Restance-medlemmer indlæst fra fil:");
            for (Member member : restanceMembers) {
                System.out.println(member);
            }
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af restance-medlemmer: " + e.getMessage());
        }
    }


    public static void startMenu() {
        memberList.loadFromFile("members.txt");
        trainingResult.loadCompSwimmerResultsFromFile("trainingresult.txt");
        raceResult.loadCompSwimmerResultsFromFile("compresult.txt");

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
                case "6":
                    handleTrainingMenu();
                    break;
                case "7":
                    boolean restanceMenuRunning = true;
                    Restance restanceHandler = new Restance(memberList); // Opretter instans af Restance-klassen

                    while (restanceMenuRunning) {
                        System.out.println("\nHåndtering af restance");
                        System.out.println("1. Vis medlemmer med restance");
                        System.out.println("2. Gem restance-medlemmer til fil");
                        System.out.println("3. Registrer betaling fra medlem");
                        System.out.println("4. Sæt medlem i restance");
                        System.out.println("5. Nulstiller betalingsstatus for alle medlemmer");

                        System.out.println("x. Gå tilbage til hovedmenuen");


                        String restanceChoice = scanner.nextLine();

                        switch (restanceChoice) {
                            case "1":
                                restanceHandler.showRestanceMembers();
                                break;
                            case "2":
                                restanceHandler.saveRestanceMembersToFile();
                                break;
                            case "3":
                                restanceHandler.removeRestanceMembers();
                                break;
                            case "4":
                                System.out.println(memberList);
                                System.out.print("Indtast navnet på medlemmet, der skal sættes i restance: ");
                                String nameToSetRestance = scanner.nextLine().trim();
                                memberList.setRestanceForMember(nameToSetRestance, true);
                                System.out.println("Medlem er tilføjet til restance listen");
                                break;
                            case "5":
                                restanceHandler.resetPayment();
                                restanceHandler.saveRestanceMembersToFile();
                                break;
                            case "x":
                                restanceMenuRunning = false;
                                restanceHandler.saveRestanceMembersToFile();
                                break;
                            default:
                                System.out.println("Ugyldigt valg, prøv igen.");
                        }

                    }
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
        System.out.println("6. Håndter trænings- og konkurrenceresultater");
        System.out.println("7. Restancefil");
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

    private static void handleTrainingMenu() {
        boolean trainingMenuRunning = true;

        while (trainingMenuRunning) {
            System.out.println("\nTræningsresultater");
            System.out.println("1. Indtast træningstider");
            System.out.println("2. Rediger træningstid");
            System.out.println("3. Vis alle træningsresultater");
            System.out.println("4. Vis top 5 træningstider");
            System.out.println("5. Slet træningstid");
            System.out.println("6. Gem resultater til trainingresult.txt");
            System.out.println("7. Administrer konkurrenceresultater");
            System.out.println("x. Gå tilbage til hovedmenuen");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    trainingResult.inputTrainingTimes(memberList.list);
                    trainingResult.saveCompSwimmerResultsToFile("trainingresult.txt");

                    break;
                case "2":
                    trainingResult.editTrainingTime();
                    break;
                case "3":
                    //trainingResult.loadCompSwimmerResultsFromFile("trainingresult.txt");
                    trainingResult.printResults();

                    break;
                    
                case "4":
                    trainingTop5.displayTrainingTopFive("trainingresult.txt");
                    break;
                    
                case "5":
                    trainingResult.removeSwimmerFromResults("trainingresult.txt");
                    break;

                case "6":
                    trainingResult.saveCompSwimmerResultsToFile("trainingresult.txt");
                    break;
                case "7":
                    boolean raceMenuRunning = true;
                    while (raceMenuRunning) {
                        System.out.println("\nKonkurrenceresultater");
                        System.out.println("1. Indtast konkurrenceresultat");
                        System.out.println("2. Vis alle konkurrenceresultater");
                        System.out.println("3. Vis top 5 konkurrencetider");
                        System.out.println("4. Slet konkurencetid");
                        System.out.println("x. Tilbage til hovedmenu");
                        String raceChoice = scanner.nextLine();



                        switch (raceChoice) {
                            case "1":
                                raceResult.inputRaceTimes(memberList.list);
                                raceResult.saveCompSwimmerResultsToFile("compresult.txt");

                                break;
                            case "2":
                                raceResult.editRaceTime();
                                break;
                            case "3":
                                //trainingResult.loadCompSwimmerResultsFromFile("compresult.txt");
                                topFive.displayTopFive("compresult.txt");


                                break;
                            case "4":
                                raceResult.removeSwimmerFromResults("compresult.txt");
                                break;

                            case "5":
                                raceResult.saveCompSwimmerResultsToFile("compresult.txt");
                                break;
                            case "x":
                                raceMenuRunning = false;
                                break;


            }

        }
    }
}
    }
}