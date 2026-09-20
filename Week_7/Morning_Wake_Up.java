public class Morning_Wake_Up
{
    interface Ringable
    {
        String ring();
    }

    static class AlarmClock implements Ringable
    {
        private String time;

        public AlarmClock(String time)
        {
            this.time = time;
        }

        @Override
        public String ring()
        {
            return "Alarm ringing for " + time;
        }
    }

    static class Doorbell implements Ringable
    {
        private String location;

        public Doorbell(String location)
        {
            this.location = location;
        }

        @Override
        public String ring()
        {
            return "Doorbell ringing at " + location;
        }
    }

    static void ringAll(Ringable[] devices)
    {
        for (int i = 0; i < devices.length; i++)
        {
            System.out.println(devices[i].ring());
        }
    }

    public static void main(String[] args)
    {
        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");

        System.out.println(a.ring());
        System.out.println(d.ring());

        Ringable[] devices =
        {
            a,
            d
        };

        ringAll(devices);
    }
}