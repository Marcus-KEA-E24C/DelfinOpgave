import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;


public class RaceResult {
    private List<RaceEntry> results = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void saveCompSwimmerResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name;Discipline;Time");
            writer.newLine();
            for (RaceEntry entry : results) {
                writer.write(entry.getSwimmerName() + ";" + entry.getDiscipline() + ";" + entry.getTime());
                writer.newLine();
            }
            System.out.println("Konkurrencesvømmerresultater gemt i " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af resultater: " + e.getMessage());
        }


    }
    public void loadCompSwimmerResultsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Spring overskriftslinjen over
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                if (parts.length < 3) {
                    System.out.println("Advarsel: Linjen er forkert formateret og bliver sprunget over: " + line);
                    continue;
                }

                String swimmerName = parts[0];
                String discipline = parts[1];
                double time = Double.parseDouble(parts[2]);

                // Tilføj entry til results-listen
                RaceEntry entry = new RaceEntry(swimmerName, discipline, time);
                results.add(entry);
            }
            System.out.println("Konkurrencesresultater indlæst fra " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af konkurrencesresultater: " + e.getMessage());
        }
    }


    // Metode til at vise konkurrencesvømmere fra members.txt
    public void showCompetitiveSwimmers(List<Member> members) {
        System.out.println("\nListe over konkurrencesvømmere:");
        for (Member member : members) {
            if (member.isCompetitiveSwimmer()) {
                System.out.println("- " + member.getName());
            }
        }
    }

    // Metode til at indtaste konkurrencestider
    public void inputRaceTimes(List<Member> members) {
        showCompetitiveSwimmers(members);

        System.out.print("\nIndtast navnet på svømmeren: ");
        String swimmerName = scanner.nextLine().trim();

        Member swimmer = members.stream()
                .filter(member -> member.isCompetitiveSwimmer() && member.getName().equalsIgnoreCase(swimmerName))
                .findFirst()
                .orElse(null);

        if (swimmer == null) {
            System.out.println("Svømmeren findes ikke eller er ikke konkurrencesvømmer.");
            return;
        }

        System.out.println("Vælg disciplin:");
        System.out.println("1. Junior-Brystsvømning");
        System.out.println("2. Junior Butterfly");
        System.out.println("3. Junior Crawl");
        System.out.println("4. Senior Rygcrawl");
        System.out.println("5. Senior Brystsvømning");
        System.out.println("6. Senior Butterfly");
        System.out.println("7. Senior Crawl");

        int choice = Integer.parseInt(scanner.nextLine());
        String discipline = switch (choice) {
            case 1 -> "Junior Brystsvømning";
            case 2 -> "Junior Butterfly";
            case 3 -> "Junior Crawl";
            case 4 -> "Senior Rygcrawl";
            case 5 -> "Senior Brystsvømning";
            case 6 -> "Senior Butterfly";
            case 7 -> "Senior Crawl";
            default -> {
                System.out.println("Ugyldigt valg.");
                yield null;
            }
        };

        if (discipline == null) {
            return;
        }

        System.out.println("Indtast bedste konkurrencestid i sekunder og centisekunder, adskilt af punktum");
        double time = Double.parseDouble(scanner.nextLine());


        RaceEntry newEntry = new RaceEntry(swimmerName, discipline, time);
        results.add(newEntry);

        System.out.println("Tid tilføjet for " + swimmerName + " i disciplinen " + discipline);

        // Debugging: Udskriv indholdet af results-listen
        System.out.println("\nAntal resultater i listen: " + results.size());
        for (RaceEntry entry : results) {
            System.out.println(entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime());
        }

        // Gem resultater automatisk
        saveCompSwimmerResultsToFile("raceresult.txt");
    }


    // Metode til at redigere tid
    public void editRaceTime() {
        System.out.println("\nListe over registrerede tider:");
        for (int i = 0; i < results.size(); i++) {
            RaceEntry entry = results.get(i);
            System.out.println((i + 1) + ". " + entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime());
        }

        System.out.print("\nVælg nummeret på tiden, der skal redigeres: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= results.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        RaceEntry entryToEdit = results.get(index);

        System.out.print("Indtast ny tid i sekunder og centisekunder (fx 12.34): ");
        double newTime = scanner.nextDouble();
        entryToEdit.setTime(newTime);

        System.out.println("Tiden er opdateret for " + entryToEdit.getSwimmerName() + " i disciplinen " + entryToEdit.getDiscipline());
    }

    // Metode til at gemme tider til en fil
    public void saveResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (RaceEntry entry : results) {
                writer.write(entry.getSwimmerName() + ", " + entry.getDiscipline() + ", " + entry.getTime());
                writer.newLine();
            }
            System.out.println("Resultater gemt til " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af resultater: " + e.getMessage());
        }
    }

    // Metode til at vise alle resultater
    public void printResults() {
        System.out.println("\nKonkurrencesresultater:");
        results.stream()
                .sorted(Comparator.comparing(RaceEntry::getDiscipline)
                        .thenComparing(RaceEntry::getTime))
                .forEach(entry -> System.out.println(entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime()));
    }
    public void removeSwimmerFromResults(String filename) {
        // Indlæs konkurrencesvømmere fra filen
        //loadCompSwimmerResultsFromFile(filename);

        // Vis liste over svømmere
        System.out.println("\nListe over svømmere:");
        for (int i = 0; i < results.size(); i++) {
            RaceEntry entry = results.get(i);
            System.out.println((i + 1) + ". " + entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime());
        }

        // Vælg svømmer til fjernelse
        System.out.print("\nIndtast nummeret på svømmeren, der skal fjernes: ");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        if (choice < 0 || choice >= results.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        // Fjern svømmeren
        RaceEntry entryToRemove = results.get(choice);
        results.remove(entryToRemove);

        System.out.println("Svømmeren " + entryToRemove.getSwimmerName() + " er blevet fjernet fra konkurrencesresultaterne.");

        // Opdater filen
        saveCompSwimmerResultsToFile(filename);
    }


}

// Hjælpeklasse til at gemme konkurrencesresultater
class RaceEntry {
    private String swimmerName;
    private String discipline;

    private double time;

    public RaceEntry(String swimmerName, String discipline, double time) {
        this.swimmerName = swimmerName;
        this.discipline = discipline;
        this.time = time;
    }



    public String getSwimmerName() {
        return swimmerName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }


}


/**

public class RaceResult {

    private static List<RaceResult> raceResults = new ArrayList<>();

    private String discipline;
    private int bestRaceTimeSeconds;
    private int bestRaceTimeCentiSeconds;
    private String date;
    private int placement;
    private Member swimmer; // Reference til medlem

    public RaceResult(String discipline, int bestRaceTimeSeconds, int bestRaceTimeCentiSeconds,
                      String date, int placement, Member swimmer) {
        this.discipline = discipline;
        this.bestRaceTimeSeconds = bestRaceTimeSeconds;
        this.bestRaceTimeCentiSeconds = bestRaceTimeCentiSeconds;
        this.date = date;
        this.placement = placement;
        this.swimmer = swimmer;
    }

    public static void addAndSaveRaceResult(String filename, List<Member> compSwimmers) throws IOException {
        RaceResult newResult = enterRaceResult(compSwimmers); // Opret nyt RaceResult
        raceResults.add(newResult); // Tilføj det til listen
        saveRaceResultsToFile(filename); // Gem alle resultater i filen
        System.out.println("Resultatet blev gemt til filen.");
    }
    public static void removeRaceResultByTime(String filename) {

        Scanner scanner = new Scanner(System.in);

        // Vis eksisterende resultater
        System.out.println("\nListe over konkurrenceresultater:");
        for (int i = 0; i < raceResults.size(); i++) {
            System.out.println((i + 1) + ". " + raceResults.get(i));
        }

        // Lad brugeren vælge resultat via input
        System.out.print("\nIndtast nummeret på det resultat, der skal fjernes: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        // Valider input
        if (index < 0 || index >= raceResults.size()) {
            System.out.println("Ugyldigt valg. Ingen ændringer foretaget.");
            return;
        }

        // Fjern valgt resultat
        RaceResult resultToRemove = raceResults.get(index);
        raceResults.remove(index);

        System.out.println("Resultatet for svømmer: " + resultToRemove.swimmer.getName() +
                ", Disciplin: " + resultToRemove.discipline +
                ", Tid: " + resultToRemove.bestRaceTimeSeconds + ":" + resultToRemove.bestRaceTimeCentiSeconds +
                ", Dato: " + resultToRemove.date +
                ", Placering: #" + resultToRemove.placement +
                " er blevet fjernet.");

        // Opdater filen
        try {
            saveRaceResultsToFile(filename);
            System.out.println("Filen '" + filename + "' er blevet opdateret.");
        } catch (IOException e) {
            System.out.println("Fejl ved opdatering af filen: " + e.getMessage());
        }
    }



    public static RaceResult enterRaceResult(List<Member> compSwimmers) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nKonkurrencesvømmere:");
        for (int i = 0; i < compSwimmers.size(); i++) {
            System.out.println((i + 1) + ". " + compSwimmers.get(i));
        }

        System.out.println("Vælg svømmeren ved at indtaste nummeret:");
        int swimmerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Member selectedSwimmer = compSwimmers.get(swimmerIndex);

        String[] validDisciplines = {"backstroke", "breaststroke", "butterfly", "crawl"};
        String discipline = "";

        // Loop indtil gyldigt disciplin-input gives
        while (true) {
            System.out.println("Indtast disciplin for konkurrenceresultat (muligheder: backstroke, breaststroke, butterfly, crawl):");
            discipline = scanner.nextLine().toLowerCase(); // Konverter til små bogstaver for nem sammenligning

            // Tjek om input er i listen over gyldige discipliner
            boolean isValid = false;
            for (String validDiscipline : validDisciplines) {
                if (validDiscipline.equals(discipline)) {
                    isValid = true;
                    break;
                }
            }

            if (isValid) {
                break; // Afslut loop, hvis disciplin er gyldig
            } else {
                System.out.println("Ugyldig disciplin. Prøv igen.");
            }
        }


        System.out.println("Indtast bedste tid i sekunder:");
        int seconds = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast bedste tid i centisekunder:");
        int centiseconds = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast dato (dd/mm/åå):");
        String date = scanner.nextLine();

        System.out.println("Indtast placering i konkurrencen:");
        int placement = Integer.parseInt(scanner.nextLine());

        return new RaceResult(discipline, seconds, centiseconds, date, placement, selectedSwimmer);
    }

    public static void saveRaceResultsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Discipline;;BestRaceTimeSeconds;BestRaceTimeCentiSeconds;Date;Placement;Swimmer");
            writer.newLine();
            for (RaceResult result : raceResults) {
                writer.write(result.discipline + ";"+ result.bestRaceTimeSeconds + ";" +
                        result.bestRaceTimeCentiSeconds + ";" + result.date + ";" +
                        result.placement + ";" + result.swimmer.getName());
                writer.newLine();
            }

        }
    }

    public static void loadRaceResultsFromFile(String filename, List<Member> allMembers) throws IOException {
        raceResults.clear(); // Ryd listen for at undgå dubletter
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                String discipline = parts[0];
                int seconds = Integer.parseInt(parts[1]);
                int centiseconds = Integer.parseInt(parts[2]);
                String date = parts[3];
                int placement = Integer.parseInt(parts[4]);
                String swimmerName = parts[5];

                Member swimmer = allMembers.stream()
                        .filter(member -> member.getName().equals(swimmerName))
                        .findFirst()
                        .orElse(null);

                raceResults.add(new RaceResult(discipline, seconds, centiseconds, date, placement, swimmer));
            }
        }
    }

    public static void printRaceResults() {
        System.out.println("\nListe over konkurrenceresultater:");
        for (RaceResult result : raceResults) {
            System.out.println(result);
        }


    }

    @Override
    public String toString() {
        return "Svømmer: " + swimmer.getName() + ", Disciplin: " + discipline +
                ", Tid: " + bestRaceTimeSeconds + ":" + bestRaceTimeCentiSeconds +
                ", Dato: " + date + ", Placering: #" + placement;
    }
}

**/