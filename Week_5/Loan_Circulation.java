public class Loan_Circulation
{
    static class LoanReceipt
    {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds)
        {
            this.memberId = memberId;

            this.bookIds = new String[bookIds.length];

            for (int i = 0; i < bookIds.length; i++)
            {
                this.bookIds[i] = bookIds[i];
            }
        }

        public String[] getBookIds()
        {
            String[] copy = new String[bookIds.length];

            for (int i = 0; i < bookIds.length; i++)
            {
                copy[i] = bookIds[i];
            }

            return copy;
        }

        public LoanReceipt withCorrectedBookId(int index, String newId)
        {
            String[] copy = getBookIds();

            if (index >= 0 && index < copy.length)
            {
                copy[index] = newId;
            }

            return new LoanReceipt(memberId, copy);
        }
    }

    static class ReferenceOnlyLoanReceipt extends LoanReceipt
    {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber)
        {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static class CirculationLedger
    {
        static String branchCode;

        static
        {
            branchCode = "SRM-KTR";
        }

        static String processNightlyCirculation(LoanReceipt[] receipts)
        {
            int processed = 0;
            int nullSkipped = 0;
            int referenceOnly = 0;
            int regular = 0;

            for (int i = 0; i < receipts.length; i++)
            {
                if (receipts[i] == null)
                {
                    nullSkipped++;
                }
                else
                {
                    processed++;

                    if (receipts[i] instanceof ReferenceOnlyLoanReceipt)
                    {
                        referenceOnly++;
                    }
                    else
                    {
                        regular++;
                    }
                }
            }

            return processed + " processed | "
                    + nullSkipped + " null skipped | "
                    + referenceOnly + " reference-only | "
                    + regular + " regular";
        }
    }

    public static void main(String[] args)
    {
        String[] books1 = {"B101", "B102"};
        String[] books2 = {"B201"};

        LoanReceipt receipt1 =
                new LoanReceipt("M001", books1);

        ReferenceOnlyLoanReceipt receipt2 =
                new ReferenceOnlyLoanReceipt(
                        "M002",
                        books2,
                        "R-12");

        LoanReceipt corrected =
                receipt1.withCorrectedBookId(0, "B999");

        LoanReceipt[] receipts =
        {
            receipt1,
            receipt2,
            null
        };

        System.out.println(
            CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}