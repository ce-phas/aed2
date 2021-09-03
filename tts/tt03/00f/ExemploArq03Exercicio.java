class ExemploArq03Exercicio {
    public static void main (String[] args){
        Arq.openRead("exemplo.txt");
        String str  = "";
        
        while (Arq.hasNext() == true){
            str += Arq.readString() + '\n';
        }
        
        Arq.close();
        Arq.openWrite("copia.txt");
        Arq.print(str);
        Arq.close();
    }
}
