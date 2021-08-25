import java.io.*;
import java.net.*;

/**
 * Html - programa
 *
 * @author Pedro Sa (742626)
 * @version 1.0
 * @since 2021-08-20
 */
public class Html
{
    public static String getHtml(String address)
    {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try
        {
            url = new URL(address);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null)
            {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try
        {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
   }

    public static boolean isLetter(char c)
    {
        return ('a' <= c && c <= 'z');
    }

    public static int[] count(String html)
    {
        int totals[] = new int[25];
        int n = html.length();
        int c;

        for (int i = 0; i < n; i++)
        {
            c = (int) html.charAt(i);

            if (c == 60)
            {
                if (html.charAt(i+1) == 'b' && html.charAt(i+2) == 'r' &&
                    html.charAt(i+3) == '>')
                {
                    totals[23]++;
                    i += 3;
                }
                else if (html.charAt(i+1) == 't' && html.charAt(i+2) == 'a' &&
                         html.charAt(i+6) == '>')
                {
                    totals[24]++;
                    i += 6;
                }
            }
            else
            {
                switch (c)
                {
                    case 97:
                        totals[0]++;
                        break;
                    case 101:
                        totals[1]++;
                        break;
                    case 105:
                        totals[2]++;
                        break;
                    case 111:
                        totals[3]++;
                        break;
                    case 117:
                        totals[4]++;
                        break;
                    case 225:
                        totals[5]++;
                        break;
                    case 233:
                        totals[6]++;
                        break;
                    case 237:
                        totals[7]++;
                        break;
                    case 243:
                        totals[8]++;
                        break;
                    case 250:
                        totals[9]++;
                        break;
                    case 224:
                        totals[10]++;
                        break;
                    case 232:
                        totals[11]++;
                        break;
                    case 236:
                        totals[12]++;
                        break;
                    case 242:
                        totals[13]++;
                        break;
                    case 249:
                        totals[14]++;
                        break;
                    case 227:
                        totals[15]++;
                        break;
                    case 245:
                        totals[16]++;
                        break;
                    case 226:
                        totals[17]++;
                        break;
                    case 234:
                        totals[18]++;
                        break;
                    case 238:
                        totals[19]++;
                        break;
                    case 244:
                        totals[20]++;
                        break;
                    case 251:
                        totals[21]++;
                        break;
                    default:
                        if (isLetter((char) c))
                            totals[22]++;
                        break;
                }
            }
        }

        return (totals);
    }

    public static void main(String args[])
    {
        MyIO.setCharset("UTF-8");
        // definir dados
        String title;
        String address;
        String html;
        int totals[] = new int[25];

        // chaves dos valores procurados
        int iKeys[] = {97, 101, 105, 111, 117, 225, 233, 237, 243, 250, 224,
                       232, 236, 242, 249, 227, 245, 226, 234, 238, 244, 251};
        String sKeys[] = {"consoante", "<br>", "<table>"};
        int n = iKeys.length;

        // ler dados
        title = MyIO.readLine();

        while (!(title.length() == 3 &&
                 title.charAt(0) == 'F' &&
                 title.charAt(1) == 'I' &&
                 title.charAt(2) == 'M'))
        {
            address = MyIO.readString();
            html = getHtml(address);
            totals = count(html);

            for (int i = 0; i < 22; i++)
            {
                MyIO.print((char) iKeys[i] + "(" + totals[i] + ") ");
            }

            for (int i = 0; i < 3; i++)
            {
                MyIO.print(sKeys[i] + "(" + totals[i + 22] + ") ");
            }

            MyIO.println(title);

            // ler nova string
            title = MyIO.readLine();
        }
    }
}

