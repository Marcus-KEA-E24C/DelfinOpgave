import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class RaceResult {

    private static List<RaceResult> raceResults = new ArrayList<>();

    private String discipline;
    private int bestRaceTimeSeconds;
    private int bestRaceTimeCentiSeconds;
    private String date;

    public RaceResult(String discipline, int bestRaceTimeSeconds, int bestRaceTimeCentiSeconds, String date) {
        this.discipline = discipline;
        this.bestRaceTimeSeconds = bestRaceTimeSeconds;
        this.bestRaceTimeCentiSeconds = bestRaceTimeCentiSeconds;
        this.date = date;
    }

    // Metode til at tilføje resultater til listen og gemme dem i en fil
    public static void addAndSaveRaceResult(String filename) throws IOException {
        RaceResult newResult = enterRaceResult(); // Opret nyt RaceResult
        raceResults.add(newResult); // Tilføj det til listen
        saveRaceResultsToFile(filename); // Gem alle resultater i filen
        System.out.println("Resultatet blev gemt til filen.");
    }

    public static RaceResult enterRaceResult() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Indtast disciplin for konkurrenceresultat:");
        String discipline = scanner.nextLine();

        System.out.println("Indtast bedste tid i sekunder:");
        int seconds = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast bedste tid i centisekunder:");
        int centiseconds = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast dato (dd/mm/åå):");
        String date = scanner.nextLine();

        return new RaceResult(discipline, seconds, centiseconds, date);
    }

    public static void saveRaceResultsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Discipline;BestRaceTimeSeconds;BestRaceTimeCentiSeconds;Date");
            writer.newLine();
            for (RaceResult result : raceResults) {
                writer.write(result.discipline + ";" + result.bestRaceTimeSeconds + ";" + result.bestRaceTimeCentiSeconds + ";" + result.date);
                writer.newLine();
            }
        }
    }

    public static void loadRaceResultsFromFile(String filename) throws IOException {
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

                raceResults.add(new RaceResult(discipline, seconds, centiseconds, date));
            }
        }
    }

    @Override
    public String toString() {
        return "Disciplin: " + discipline + ", tid: " + bestRaceTimeSeconds + ":" + bestRaceTimeCentiSeconds + ", dato: " + date;
    }
}
