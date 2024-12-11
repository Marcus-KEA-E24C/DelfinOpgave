import java.util.List;


public class CompSwimmerList {


    private List<Member> memberList;

    public CompSwimmerList(List<Member> memberList) {
        this.memberList = memberList;


    }

    public void displayCompSwimmers() {
        boolean found = false;


        for (Member member : memberList) {
            if (member.isCompSwimmer()) {
                System.out.println("Medlem: " + member.getName());
                System.out.println("Aldersgruppe: " + member.getAgeGroup());
                System.out.println("Aktiv: " + (member.isActive() ? "Ja" : "Nej"));
                System.out.println("Konkurrencesvømmer: Ja");
                System.out.println("Svømmediscipliner: " + String.join(", ", member.getSwimDisciplines()));
                found = true;
            }
        }


        if (!found) {
            System.out.println("Der er ikke registreret noget konkurrencesvømmere.");
        }
    }
}
