import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TopFive {

    // Indre klasse til at holde information om en svømmeres resultat
    static class CompetitionResult {
        String swimmerName;
        String discipline;
        double time; // Tid i sekunder,hundrededele

        public CompetitionResult(String swimmerName, String discipline, double time) {
            this.swimmerName = swimmerName;
            this.discipline = discipline;
            this.time = time;
        }
    }

    // Metode til at læse data fra compswimmerresults.txt og returnere en liste af resultater
    public List<CompetitionResult> readCompetitionResults(String filename) {
        List<CompetitionResult> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true; // Flag for at springe overskriftslinjen over

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Spring første linje over
                    continue;
                }

                // Split linjen baseret på separatoren ";"
                String[] parts = line.split(";");
                if (parts.length < 3) {
                    System.out.println("Ugyldigt format i linjen: " + line);
                    continue;
                }


                try {
                    String swimmerName = parts[0].trim();
                    String discipline = parts[1].trim();
                    double time = Double.parseDouble(parts[2].trim());

                    // String ageGroup = parts[1].trim();
                    // String date = parts[4].trim(); Kan evt. valideres, hvis nødvendigt
                    // int placement = Integer.parseInt(parts[5].trim());


                    results.add(new CompetitionResult(swimmerName, discipline, time));
                } catch (NumberFormatException e) {
                    System.out.println("Fejl i format for tid eller placering i linjen: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af fil: " + e.getMessage());
        }

        return results;
    }


    // Metode til at finde top 5 svømmeres bedste tider for hver disciplin
    public void displayTopFive(String filename) {
        List<CompetitionResult> results = readCompetitionResults(filename);

        if (results.isEmpty()) {
            System.out.println("Ingen data tilgængelig i filen.");
            return;
        }

        // Grupper resultaterne efter disciplin
        Map<String, List<CompetitionResult>> groupedResults = results.stream()
                .collect(Collectors.groupingBy(result -> result.discipline));

        for (String discipline : groupedResults.keySet()) {
            System.out.println("\nDisciplin: " + discipline);

            List<CompetitionResult> topResults = groupedResults.get(discipline)
                    .stream()
                    .sorted(Comparator.comparingDouble(result -> result.time))
                    .limit(5)
                    .collect(Collectors.toList());

            if (topResults.isEmpty()) {
                System.out.println("Ingen resultater tilgængelige.");
            } else {
                for (int i = 0; i < topResults.size(); i++) {
                    CompetitionResult result = topResults.get(i);
                    System.out.printf("%d. %s - Tid: %.2f sek.%n", i + 1, result.swimmerName, result.time);
                }
            }
        }
    }
}
