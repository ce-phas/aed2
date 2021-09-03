public class Arquivo02 {
    public static void main(String[] args) {
        String fname = MyIO.readString();
        String str = "";

        Arq.openRead(fname);

        while (Arq.hasNext() == true) {
            str += Arq.readLine() + "\n";
        }

        Arq.close();
        MyIO.println(str);
    }
}        
        