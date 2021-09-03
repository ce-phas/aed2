public class Arquivo07 {
    public static void main(String[] args) {
        String fileName = MyIO.readString();
        String str = "";
        String encStr = "";

        Arq.openRead(fileName);

        while (Arq.hasNext() == true) {
            str += Arq.readLine() + "\n";
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') {
                encStr+= '\n';
            } else  {
                encStr += (char) (str.charAt(i) + 3);
            }
        }

        Arq.close();
        MyIO.println(encStr);
    }
}