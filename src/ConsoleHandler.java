import java.util.Scanner;
import java.util.ArrayList;

public class ConsoleHandler {
    private static MemberList memberList  = new MemberList();;


    public static void startMenu() {
        //MemberList memberList = new MemberList();

        System.out.println("\nVelkommen til Delfinens konkurrence-, kontingent- og medlemssystem\nSPLASH - Simplifying Planning and Logistics for Aquatic Sports and Handling\n");
        Scanner scanner = new Scanner(System.in);
        boolean mainMenuRunning = true;


        while (mainMenuRunning) {
            System.out.println("Indtast venligst hvilken opgave, du vil have udført");
            System.out.println("1. Se medlemsliste");
            System.out.println("2. Opret nyt medlem");
            System.out.println("3. Vis konkurrencesvømmeroversigt");
            System.out.println("4. Vis kontingentoversigt");
            System.out.println("x. Luk SPLASH");

            String consoleHandlerMenu = scanner.nextLine();

            String name = "";
            String ageGroup = "";
            boolean active = true;
            boolean isCompSwimmer = false;

            switch (consoleHandlerMenu) {
                case "1":
                    System.out.println(memberList);

                    System.out.println("1. Tilføje nyt medlem");
                    System.out.println("2. Redigere medlemsinfo");
                    System.out.println("3. Slette medlem");
                    System.out.println("x. Gå tilbage");

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
                            System.out.println("1. Ændre medlemmets navn");
                            System.out.println("2. Ændre medlemmets aldersgruppe");
                            System.out.println("3. Ændre medlemmets aktivitetsstatus");
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

                                    int ageGroupChoice = scanner.nextInt();
                                    scanner.nextLine(); // Ryd bufferen efter nextInt()

                                    String newAgeGroup = "";
                                    switch (ageGroupChoice) {
                                        case 1:
                                            newAgeGroup = "JUNIOR";
                                            break;
                                        case 2:
                                            newAgeGroup = "SENIOR";
                                            break;
                                        case 3:
                                            newAgeGroup = "PENSIONIST";
                                            break;
                                        default:
                                            System.out.println("Ugyldigt valg.");
                                            break;
                                    }

                                    if (!newAgeGroup.isEmpty()) {
                                        memberList.editAgeGroup(searchName, newAgeGroup);
                                    }
                                    break;

                                //Ændre medlemsstatus
                                case "3":
                                    System.out.print("Indtast navnet på medlemmet, der skal slettes: ");
                                    String nameToChangeActivityStatus = scanner.nextLine();
                                    memberList.removeMember(nameToChangeActivityStatus);
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

                    }
                    //Opret medlem
                case "2":
                    memberList.addMember();
                    break;
                //Vis konkurrencesvømmeroversigt
                case "3":
// til konkurrencesvømmerinfo, træningsresultater, konkurrenceresultater
// her skal hentes fra klassen CompSwimmer

                    System.out.println("1. Se konkurrencesvømmeres info");
                    System.out.println("2. Se bedste træningstider");
                    System.out.println("3. Se bedste konkurrencetider");



                        /**



                         System.out.println("Vælg en handling:");
                         String memberListChoices = scanner.nextLine();
                         switch (memberListChoices) {
                         //Tilføj medlem
                         case "1":
                         memberList.addMember();
                         break;




                         **/

                    break;


                //Vis kontingentoversigt
                case "4":

                    break;

                //Luk SPLASH
                case "x":
                    mainMenuRunning = false;
                    System.out.println("Tak for besøget!");
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen");
            }

        }

        scanner.close();

    }


}
