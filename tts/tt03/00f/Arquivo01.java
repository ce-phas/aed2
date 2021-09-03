public class Arquivo01 {
    public static void main(String[] args) {
        String fname = MyIO.readString();
        String line = MyIO.readLine();

        Arq.openWrite(fname);
        Arq.println(line);
        Arq.close();
    }
}