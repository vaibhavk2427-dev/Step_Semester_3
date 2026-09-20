import java.util.Scanner;

public class Library_ISBN_Validator
{
    static String normalizeCode(String raw)
    {
        String code = raw.trim();

        if (code.length() >= 3)
        {
            String publisher = code.substring(0, 3).toUpperCase();
            String remaining = code.substring(3);

            code = publisher + remaining;
        }

        return code;
    }

    static String validateAndFormat(String code)
    {
        if (code.length() != 13)
        {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++)
        {
            if (!Character.isLetter(code.charAt(i)))
            {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++)
        {
            if (!Character.isDigit(code.charAt(i)))
            {
                return "Invalid: body must contain only digits";
            }
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(publisher);
        result.append("] YEAR: ");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalog);

        return result.toString();
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String raw = sc.nextLine();

        String normalized = normalizeCode(raw);
        String result = validateAndFormat(normalized);

        System.out.println(result);
    }
}