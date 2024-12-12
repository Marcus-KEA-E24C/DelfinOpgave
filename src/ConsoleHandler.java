import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleHandler {
    private static MemberList memberList  = new MemberList();;
    private static void showFeeOverview() {
        Fee.displayFeeOverview(memberList.list);

    }



    public static void startMenu() {
        memberList.loadFromFile("members.txt");
        try {
            List<Member> restanceMembers = FileHandler.readRestanceMembersFromFile("restance.txt");
            for (Member member : restanceMembers) {
                memberList.setRestanceForMember(member.getName(), true); // Opdater medlemslisten
            }
        } catch (IOException e) {
            System.out.println("Kunne ikke indlæse restancefilen: " + e.getMessage());
        }


        //MemberList memberList = new MemberList();

        System.out.println("\nVelkommen til Delfinens konkurrence-, kontingent- og medlemssystem\nSPLASH - Simplifying Planning and Logistics for Aquatic Sports and Handling\n");
        Scanner scanner = new Scanner(System.in);
        boolean mainMenuRunning = true;


        while (mainMenuRunning) {
            System.out.println("Indtast venligst hvilken opgave, du vil have udført" +
                    "\n1. Se medlemsliste" +
                    "\n2. Opret nyt medlem" +
                    "\n3. Vis konkurrencesvømmeroversigt" +
                    "\n4. Vis kontingentoversigt" +
                    "\n5. Restancefil" +
                    "\nx. Luk SPLASH");

            String consoleHandlerMenu = scanner.nextLine();

            String name = "";
            String ageGroup = "";
            boolean isActive = true;
            boolean isCompSwimmer = false;

            switch (consoleHandlerMenu) {
                case "1":
                    System.out.println(memberList);

                    System.out.println("1. Tilføje nyt medlem" +
                            "\n2. Redigere medlemsinfo" +
                            "\n3. Slette medlem" +
                            "\nx. Gå tilbage");

                    boolean memberListLoop = true;


                    System.out.println("Vælg en handling:");
                    String memberListChoices = scanner.nextLine();
                    switch (memberListChoices) {
                        //Tilføj medlem
                        case "1":
                            memberList.addMember();
                            break;
                        //Rediger medlemsinfo
                        case "2":
                            System.out.println("Redigér medlemsinfo");
                            System.out.println("1. Ændre medlemmets navn");
                            System.out.println("2. Ændre medlemmets aldersgruppe");
                            System.out.println("3. Ændre medlemmets aktivitetsstatus");
                            System.out.println("4. Slet medlem");
                            System.out.println("x. Luk Medlemsinfo");

                            System.out.println("Vælg en handling:");
                            String editMemberChoice = scanner.nextLine();


                            switch (editMemberChoice) {

                                case "1":
                                    System.out.print("Indtast navnet på medlemmet, der skal redigeres: ");
                                    String oldName = scanner.nextLine();
                                    System.out.print("Indtast det nye navn: ");
                                    String newName = scanner.nextLine();
                                    memberList.editName(oldName, newName);
                                    break;

                                case "2": // Rediger aldersgruppe
                                    System.out.print("Indtast navnet på medlemmet, der skal redigeres: ");
                                    String searchName = scanner.nextLine();
                                    System.out.println("Indtast den nye aldersgruppe: ");
                                    System.out.println("1. JUNIOR");
                                    System.out.println("2. SENIOR");
                                    System.out.println("3. PENSIONIST");

                                    String ageGroupChoice = scanner.nextLine();

                                    String newAgeGroup = "";
                                    switch (ageGroupChoice) {
                                        case "1":
                                            newAgeGroup = "JUNIOR";
                                            break;
                                        case "2":
                                            newAgeGroup = "SENIOR";
                                            break;
                                        case "3":
                                            newAgeGroup = "PENSIONIST";
                                            break;
                                        default:
                                            System.out.println("Ugyldigt valg.");
                                    }

                                    if (!newAgeGroup.isEmpty()) {
                                        memberList.editAgeGroup(searchName, newAgeGroup);
                                    }
                                    break;

                                //Ændre medlemsstatus
                                case "3":
                                    boolean setNewIsActive;
                                    System.out.print("Indtast navnet på medlemmet, der skal have ændret aktivitetsstatus: ");
                                    String nameToChangeActivityStatus = scanner.nextLine();
                                    memberList.changeActivityStatus(nameToChangeActivityStatus);
                                    break;


                                //Slette medlem
                                case "4":
                                    System.out.print("Indtast navnet på medlemmet, der skal slettes: ");
                                    String nameToRemove = scanner.nextLine();
                                    memberList.removeMember(nameToRemove);
                                    break;

                                //Luk Medlemsliste
                                case "x":
                                    memberListLoop = false;
                                    break;
                                default:
                                    System.out.println("Ugyldigt valg, prøv igen");
                            }
                            break;
                        case "3":
                            System.out.print("Indtast navnet på medlemmet, der skal slettes: ");
                            String nameToRemove = scanner.nextLine();
                            memberList.removeMember(nameToRemove);
                            break;
                    }break;
                    //Opret medlem
                case "2":
                    memberList.addMember();
                    memberList.saveToFile("members.txt");

                    break;


                //Vis konkurrencesvømmeroversigt
                case "3":

                    System.out.println("\nKonkurrencesvømmeroversigt");
                    System.out.println("1. Se konkurrencesvømmeres info");
                    System.out.println("2. Træningstider");
                    System.out.println("3. Konkurrencetider");
                    System.out.println("x Gå tilbage");


                    System.out.println("Vælg en handling:");
                    String compSwimmerChoices = scanner.nextLine();
                    switch (compSwimmerChoices) {
                        // Se konkurrencesvømmeres info
                        case "1":
                             CompSwimmerList compSwimmerList = new CompSwimmerList(memberList.list);
                             compSwimmerList.displayCompSwimmers();


                            System.out.println(compSwimmerList);



                            break;
                        // Se bedste træningstider
                        case "2":
                            System.out.println("1. Se bedste træningsresultater");
                            System.out.println("2. Tilføj nye træningsresultater");

                            System.out.println("Vælg en handling:");
                            String trainingResultsChoices = scanner.nextLine();

                            switch (trainingResultsChoices) {
                                case "1":


                                 // vis træningsresultater, sorter efter laveste tid, cut af efter top 5



                                case "2":

                                    System.out.println("Indtast træningsresultat for konkurrencesvømmeren:");

                                    TrainingResult result = TrainingResult.enterTrainingResult();
                                    if (result != null) {
                                        System.out.println("Træningsresultater gemt: " + result);
                                    } else {
                                        System.out.println("Træningsresultater kunne ikke gemmes.");
                                    }
                                    break;
                                    
                            }

                            break;
                        // Se bedste konkurrencetider
                        case "3":
                            System.out.println("1. Se bedste konkurrenceresultater");
                            System.out.println("2. Tilføj nye konkurrenceresultater");

                            System.out.println("Vælg en handling:");
                            String raceResultsChoices = scanner.nextLine();

                            switch (raceResultsChoices) {
                                case "1":
                                case "2":

                            }

                            break;
                        // Gå tilbage
                        case "x":
                        default:
                            System.out.println("Ugyldigt valg, prøv igen");

                    }
                    break;


                //Vis kontingentoversigt
                case "4":

                    showFeeOverview();

                    break;
                // I din hovedmenu
                case "5":
                    boolean restanceMenuRunning = true;
                    Restance restanceHandler = new Restance(memberList); // Opretter instans af Restance-klassen

                    while (restanceMenuRunning) {
                        System.out.println("\nHåndtering af restance");
                        System.out.println("1. Vis medlemmer med restance");
                        System.out.println("2. Gem restance-medlemmer til fil");
                        System.out.println("3. Fjern restance-medlemmer");
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
                                // Nulstil betalingsstatus for alle medlemmer
                                restanceHandler.resetPayment();
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



                //Luk SPLASH
                case "x":
                    mainMenuRunning = false;
                    System.out.println("Tak for besøget!");
                    memberList.saveToFile("members.txt");
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen");
            }

        }

        scanner.close();

    }


}
