public class Arquivo05 {
    public static void main(String[] args) {
        String file1 = MyIO.readString();
        String file2 = MyIO.readString();
        String str = "";
        String uStr = "";
        char c;

        Arq.openRead(file1);

        while (Arq.hasNext() == true) {
            str += Arq.readLine() + "\n";
        }

        Arq.close();

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

        Arq.openWrite(file2);
        Arq.print(uStr);
        Arq.close();
    }
}