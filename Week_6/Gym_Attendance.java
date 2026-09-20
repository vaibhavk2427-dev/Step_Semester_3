public class Gym_Attendance
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
            sessionsAttended = 0;
        }

        public void attendSession()
        {
            sessionsAttended++;
        }

        public int getSessionsAttended()
        {
            return sessionsAttended;
        }

        public String displayInfo()
        {
            return "Standard | Sessions: "
                    + sessionsAttended;
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

        public String getTrainerName()
        {
            return trainerName;
        }

        @Override
        public String displayInfo()
        {
            return "Premium | Trainer: "
                    + trainerName
                    + " | Sessions: "
                    + getSessionsAttended();
        }
    }

    static String batchPrint(GymMember[] members)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < members.length; i++)
        {
            result.append(members[i].displayInfo());

            if (members[i] instanceof PremiumMember)
            {
                PremiumMember premium =
                        (PremiumMember) members[i];

                result.append(
                        " [Trainer via downcast: "
                        + premium.getTrainerName()
                        + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args)
    {
        GymMember standard =
                new GymMember("MEM6", 1000);

        PremiumMember premium =
                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya");

        GymMember[] members =
        {
            standard,
            premium
        };

        System.out.println(
                batchPrint(members)
        );
    }
}