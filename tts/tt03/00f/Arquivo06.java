public class Arquivo06 {
    public static void main(String[] args) {
        String file1 = MyIO.readString();
        String file2 = MyIO.readString();
        String str = "";
        String invStr = "";
        char c;

        Arq.openRead(file1);

        while (Arq.hasNext() == true) {
            str += Arq.readLine() + "\n";
        }

        Arq.close();

        for (int i = str.length() - 1; i >= 0; i--) {
            c = str.charAt(i);

            if (c == '\n') {
                invStr+= '\n';
            } else  {
                invStr += c;
            }
        }

        Arq.openWrite(file2);
        Arq.print(invStr);
        Arq.close();
    }
}