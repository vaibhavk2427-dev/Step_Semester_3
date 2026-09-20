public class Gym_Membership
{
    static class GymMember
    {
        protected String memberId;
        protected int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee)
        {
            if (memberId == null ||
                memberId.trim().length() < 4)
            {
                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession()
        {
            sessionsAttended++;
        }

        public int getSessionsAttended()
        {
            return sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember
    {
        private String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName)
        {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }
    }

    static String signUpBatch(
            String[] memberIds,
            int monthlyFee)
    {
        int signedUp = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++)
        {
            try
            {
                GymMember member =
                        new GymMember(memberIds[i], monthlyFee);

                signedUp++;
            }
            catch (IllegalArgumentException e)
            {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }

    public static void main(String[] args)
    {
        PremiumMember p =
                new PremiumMember(
                        "MEM01",
                        2000,
                        "Coach Riya");

        p.attendSession();
        p.attendSession();

        System.out.println(
                "Sessions Attended: "
                + p.getSessionsAttended()
        );

        String[] members =
        {
            "MEM1",
            "GM1",
            "MEM2",
            " ",
            "MEM3"
        };

        System.out.println(
                signUpBatch(members, 1000)
        );
    }
}