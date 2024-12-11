import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void saveMembersToFile(List<Member> members, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Name;AgeGroup;Active;CompSwimmer;Fee");
            writer.newLine();
            for (Member member : members) {
                int fee = Fee.calculateFee(member); // Beregn kontingentet (fee)
                writer.write(member.getName() + ";" +
                        member.getAgeGroup() + ";" +
                        (member.isActive() ? "true" : "false") + ";" +
                        (member.isCompSwimmer() ? "true" : "false") + ";" +
                        fee);
                writer.newLine();
            }
        }
    }

    public static List<Member> readMembersFromFile(String filename) throws IOException {
        List<Member> members = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true; // Flag til at springe overskriften over

            while ((line = reader.readLine()) != null) {
                // Spring første linje over (overskrift)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Split linjen ved semikolon
                String[] parts = line.split(";");

                // Check at linjen har de forventede dele
                if (parts.length < 5) {
                    System.out.println("Advarsel: Linjen er forkert formateret og bliver sprunget over: " + line);
                    continue;
                }

                // Udpak værdierne
                String name = parts[0];
                String ageGroup = parts[1];
                boolean isActive = Boolean.parseBoolean(parts[2]);
                boolean isCompSwimmer = Boolean.parseBoolean(parts[3]);
                int fee = Integer.parseInt(parts[4]); // Fee kan bruges som validering eller ignoreres

                // Opret og tilføj medlem
                Member member = new Member(name, ageGroup, isActive, isCompSwimmer);
                members.add(member);
            }
        }

        return members;
    }
}