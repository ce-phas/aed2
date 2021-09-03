public class Arquivo03 {
    public static void main(String[] args) {
        String fname = MyIO.readString();
        String str = "";
        String uStr = "";
        char c;

        Arq.openRead(fname);

        while (Arq.hasNext() == true) {
            str += Arq.readLine() + "\n";
        }

        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);

            if ('a' <= c && c <= 'z') {
                uStr += (char) (c - 32);
            } else if (c == '\n') {
                uStr+= '\n';
            } else  {
                uStr += c;
            }
        }

        MyIO.print(uStr);
        Arq.close();
    }
}