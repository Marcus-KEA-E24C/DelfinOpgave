import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Fee {
    private static final int JUNIOR_FEE=1000;
    private static final int SENIOR_FEE=1600;
    private static final double PENSIONIST_DISCOUNT = 0.25;
    private static final int PASSIVE_FEE = 500;


    public static int calculateFee(Member member) {
        if (!member.isActive()) {
            return PASSIVE_FEE;
        }

        switch (member.getAgeGroup()) {
            case "JUNIOR":
                return JUNIOR_FEE;
            case "SENIOR":
                return SENIOR_FEE;
            case "PENSIONIST":
                return (int) (SENIOR_FEE * (1 - PENSIONIST_DISCOUNT));
            default:
                throw new IllegalArgumentException("Ukendt aldersgruppe: " + member.getAgeGroup());
        }
    }
    public static int calculateTotalFee(List<Member> members){
        int total=0;
        for(Member member:members){
            total += calculateFee(member);
        }
        return total;
    }
    public static void displayFeeOverview(List<Member> members) {
        System.out.println("Oversigt over kontingent for alle medlemmer:");
        for (Member member : members) {
            int fee = calculateFee(member);
            System.out.println(member.getName() + " (" + member.getAgeGroup() +
                    ", " + (member.isActive() ? "Aktiv" : "Passiv") + "): " + fee + " kr.");
        }

        int totalFee = calculateTotalFee(members);
        System.out.println("\nSamlet forventet indbetaling: " + totalFee + " kr.");
    }
    @Test
    public void testCalculateFee() {
        Member juniorMember = new Member("Junior", "JUNIOR", true, false);
        Member seniorMember = new Member("Senior", "SENIOR", true, false);
        Member pensionistMember = new Member("Pensionist", "PENSIONIST", true, false);
        Member passiveMember = new Member("Passive", "SENIOR", false, false);

        // Test junior fee
        assertEquals(1000, Fee.calculateFee(juniorMember),
                "Gebyr for junior medlem er forkert");

        // Test senior fee
        assertEquals(1600, Fee.calculateFee(seniorMember),
                "Gebyr for senior medlem er forkert");

        // Test pensionist fee
        assertEquals(1200, Fee.calculateFee(pensionistMember),
                "Gebyr for pensionist medlem er forkert (1600 - 25%)");

        // Test passive fee
        assertEquals(500, Fee.calculateFee(passiveMember),
                "Gebyr for passivt medlem er forkert");
    }

    @Test
    public void testCalculateTotalFee() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("Junior", "JUNIOR", true, false));
        members.add(new Member("Senior", "SENIOR", true, false));
        members.add(new Member("Pensionist", "PENSIONIST", true, false));
        members.add(new Member("Passive", "SENIOR", false, false));

        int expectedTotal = 1000 + 1600 + 1200 + 500; // Summen af alle kontingenter
        assertEquals(expectedTotal, Fee.calculateTotalFee(members));
    }

}



