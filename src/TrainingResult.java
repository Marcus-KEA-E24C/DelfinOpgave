import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TrainingResult {
    private List<TrainingEntry> results = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void saveCompSwimmerResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name;Discipline;Time");
            writer.newLine();
            for (TrainingEntry entry : results) {
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
                TrainingEntry entry = new TrainingEntry(swimmerName, discipline, time);
                results.add(entry);
            }
            System.out.println("Træningsresultater indlæst fra " + filename);
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af træningsresultater: " + e.getMessage());
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

    // Metode til at indtaste træningstider
    public void inputTrainingTimes(List<Member> members) {
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

        System.out.println("Indtast bedste træningstid i sekunder og centisekunder, adskilt af punktum");
        double time = Double.parseDouble(scanner.nextLine());


        TrainingEntry newEntry = new TrainingEntry(swimmerName, discipline, time);
        results.add(newEntry);

        System.out.println("Tid tilføjet for " + swimmerName + " i disciplinen " + discipline);

        // Debugging: Udskriv indholdet af results-listen
        System.out.println("\nAntal resultater i listen: " + results.size());
        for (TrainingEntry entry : results) {
            System.out.println(entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime());
        }

        // Gem resultater automatisk
        saveCompSwimmerResultsToFile("trainingresult.txt");
    }


    // Metode til at redigere tid
    public void editTrainingTime() {
        System.out.println("\nListe over registrerede tider:");
        for (int i = 0; i < results.size(); i++) {
            TrainingEntry entry = results.get(i);
            System.out.println((i + 1) + ". " + entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime());
        }

        System.out.print("\nVælg nummeret på tiden, der skal redigeres: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= results.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        TrainingEntry entryToEdit = results.get(index);

        System.out.print("Indtast ny tid i sekunder og centisekunder (fx 12.34): ");
        double newTime = scanner.nextDouble();
        entryToEdit.setTime(newTime);

        System.out.println("Tiden er opdateret for " + entryToEdit.getSwimmerName() + " i disciplinen " + entryToEdit.getDiscipline());
    }

    // Metode til at gemme tider til en fil
    public void saveResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (TrainingEntry entry : results) {
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
        System.out.println("\nTræningsresultater:");
        results.stream()
                .sorted(Comparator.comparing(TrainingEntry::getDiscipline)
                .thenComparing(TrainingEntry::getTime))
                .forEach(entry -> System.out.println(entry.getSwimmerName() + " - " + entry.getDiscipline() + " - Tid: " + entry.getTime()));
    }
    public void removeSwimmerFromResults(String filename) {
        // Indlæs konkurrencesvømmere fra filen
        //loadCompSwimmerResultsFromFile(filename);

        // Vis liste over svømmere
        System.out.println("\nListe over svømmere:");
        for (int i = 0; i < results.size(); i++) {
            TrainingEntry entry = results.get(i);
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
        TrainingEntry entryToRemove = results.get(choice);
        results.remove(entryToRemove);

        System.out.println("Svømmeren " + entryToRemove.getSwimmerName() + " er blevet fjernet fra træningsresultaterne.");

        // Opdater filen
        saveCompSwimmerResultsToFile(filename);
    }


}

// Hjælpeklasse til at gemme træningsresultater
class TrainingEntry {
    private String swimmerName;
    private String discipline;

    private double time;

    public TrainingEntry(String swimmerName, String discipline, double time) {
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
