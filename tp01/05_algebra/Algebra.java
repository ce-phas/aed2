public class Algebra
{
    public static int getEntries(String s)
    {
        return ((int) s.charAt(0) - 48);
    }

    public static boolean[] getValues(String s, int e)
    {
        boolean[] v = new boolean[e];

        for(int i = 0; i < e; i++)
        {
            int bin = (int) s.charAt(i * 2 + 2) - 48;
            v[i] = (bin == 1 ? true : false);
        }

        return v;
    }

    public static String getExpr(String s, int e)
    {
        String expr = "";
        int n = s.length();
        int start = 2 * e + 2;

        for (int i = start; i < n; i++)
        {
            expr += s.charAt(i);
        }

        return expr;
    }

    public static int countOps(String expr)
    {
        int n = expr.length() - 1;
        int ops = 0;

        for (int i = n; i > 4; i--)
        {
            if (expr.charAt(i) == ')')
            {
                ops += 1;
            }
        }

        return ops;
    }

    public static boolean parseBool(String expr, boolean[] vals)
    {
        int x = 0;
        int ops = countOps(expr);
        boolean[] table = new boolean[ops];
        char v1, v2;

        for (int i = expr.length() - 3; i > 1; i--)
        {
            if (expr.charAt(i) == '(')
            {
                v1 = expr.charAt(i + 1);
                if ('A' <= v1 && v1 <= 'C')
                {
                    switch (expr.charAt(i - 1))
                    {
                        case 'd':
                            v2 = expr.charAt(i + 5);
                            table[x] = vals[(int) v1 - 65] && vals[(int) v2 - 65];
                            x++;
                            break;
                        case 'r':
                            v2 = expr.charAt(i + 5);
                            table[x] = vals[(int) v1 - 65] || vals[(int) v2 - 65];
                            x++;
                            break;
                        case 't':
                            table[x] = !vals[(int) v1 - 65];
                            x++;
                            break;
                        default:
                            break;
                    }
                }
                else
                {
                    switch (expr.charAt(i - 1))
                    {
                        case 'd':
                            table[x] = table[x - 1] && table[x - 2];
                            x++;
                            break;
                        case 'r':
                            table[x] = table[x - 1] || table[x - 2];
                            x++;
                            break;
                        case 't':
                            table[x] = !table[x - 1];
                            x++;
                            break;
                    }
                }
            }
        }

        return (table[ops - 1]);
    }

    public static void main(String[] args)
    {
        String fullIn = MyIO.readLine();
        int entries = getEntries(fullIn);
        boolean[] values = getValues(fullIn, entries);
        String expr = getExpr(fullIn, entries);

        if (parseBool(expr, values))
        {
            MyIO.println("1");
        }
        else
        {
            MyIO.println("0");
        }
    }
}