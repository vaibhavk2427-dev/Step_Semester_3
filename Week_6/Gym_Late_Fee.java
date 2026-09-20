public class Gym_Late_Fee
{
    static class GymMember
    {
        protected String memberId;
        protected int monthlyFee;

        private int[] lateFeeHistory;
        private int lateFeeCount;

        public GymMember(String memberId, int monthlyFee)
        {
            if (memberId == null ||
                memberId.trim().length() < 4)
            {
                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;

            lateFeeHistory = new int[10];
            lateFeeCount = 0;
        }

        protected void chargeLateFee(int amount)
        {
            if (lateFeeCount < 10)
            {
                lateFeeHistory[lateFeeCount] = amount;
                lateFeeCount++;
            }
        }

        public int[] getLateFeeHistory()
        {
            int[] copy = new int[lateFeeCount];

            for (int i = 0; i < lateFeeCount; i++)
            {
                copy[i] = lateFeeHistory[i];
            }

            return copy;
        }

        public int getTotalLateFees()
        {
            int total = 0;

            for (int i = 0; i < lateFeeCount; i++)
            {
                total = total + lateFeeHistory[i];
            }

            return total;
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

        @Override
        protected void chargeLateFee(int amount)
        {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args)
    {
        PremiumMember p =
                new PremiumMember(
                        "MEM5",
                        2000,
                        "Coach Riya");

        p.chargeLateFee(200);

        System.out.println(
            "Total Late Fees: "
            + p.getTotalLateFees()
        );

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        int[] newHistory = p.getLateFeeHistory();

        System.out.println(
            "Recorded Fee: "
            + newHistory[0]
        );
    }
}