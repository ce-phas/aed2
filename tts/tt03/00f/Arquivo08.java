public class Arquivo08 {
    public static void main(String[] args) {
        String fileName = MyIO.readString();
        String str = "";
        String encStr = "";

        Arq.openRead(fileName);

        while (Arq.hasNext() == true) {
            encStr += Arq.readLine() + "\n";
        }

        for (int i = 0; i < encStr.length(); i++) {
            if (encStr.charAt(i) == '\n') {
                str+= '\n';
            } else  {
                str += (char) (encStr.charAt(i) - 3);
            }
        }

        Arq.close();
        MyIO.println(str);
    }
}