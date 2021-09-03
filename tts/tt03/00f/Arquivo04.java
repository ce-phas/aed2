public class Arquivo04 {
    public static void main(String[] args) {
        String srcFile = MyIO.readString();
        String dstFile = MyIO.readString();
        String content = "";

        Arq.openRead(srcFile);

        while (Arq.hasNext() == true) {
            content += Arq.readLine() + "\n";
        }

        Arq.close();
        Arq.openWrite(dstFile);
        Arq.print(content);
        Arq.close();
    }
}