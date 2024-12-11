import java.util.List;

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
}


