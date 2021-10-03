import java.io.*;
import java.util.*;

/**
 * @author Pedro H. Amorim Sá
 * @version 1.0
 * @since 2021-10-02
 */

class Series {

    // definir atributos
    private String nome;
    private String formato;
    private String duracao;
    private String paisDeOrigem;
    private String idiomaOriginal;
    private String emissoraDeTelevisao;
    private String transmissaoOriginal;
    private int numeroTemporadas;
    private int numeroEpisodios;


    /* Construtores */

    Series() {}

    /**
     * Construtor que recebe parametros para todos os atributos do objeto.
     * @param nome Titulo da serie.
     * @param formato Descricao do formato da serie.
     * @param duracao Descricao do tempo de duracao da serie.
     * @param paisDeOrigem Pais de origem da serie.
     * @param idiomaOriginal Idioma original da serie.
     * @param emissoraDeTelevisao Emissora de televisao da serie.
     * @param transmissaoOriginal Periodo de transmissao original da serie.
     * @param numeroTemporadas Total de temporadas da serie.
     * @param numeroEpisodios Total de episodios da serie.
     */
    Series(
        String nome, String formato, String duracao, String paisDeOrigem,
        String idiomaOriginal, String emissoraDeTelevisao,
        String transmissaoOriginal,int numeroTemporadas,
        int numeroEpisodios) {

        this.nome = nome;
        this.formato = formato;
        this.duracao = duracao;
        this.paisDeOrigem = paisDeOrigem;
        this.idiomaOriginal = idiomaOriginal;
        this.emissoraDeTelevisao = emissoraDeTelevisao;
        this.transmissaoOriginal = transmissaoOriginal;
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
    }


    /* Getters */

    public String getNome() {
        return this.nome;
    }

    public String getFormato() {
        return this.formato;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public String getPaisDeOrigem() {
        return this.paisDeOrigem;
    }

    public String getIdiomaOriginal() {
        return this.idiomaOriginal;
    }

    public String getEmissoraDeTelevisao() {
        return this.emissoraDeTelevisao;
    }

    public String getTransmissaoOriginal() {
        return this.transmissaoOriginal;
    }

    public int getNumeroTemporadas() {
        return this.numeroTemporadas;
    }

    public int getNumeroEpisodios() {
        return this.numeroEpisodios;
    }


    /* Setters */

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public void setEmissoraDeTelevisao(String emissoraDeTelevisao) {
        this.emissoraDeTelevisao = emissoraDeTelevisao;
    }

    public void setTransmissaoOriginal(String transmissaoOriginal) {
        this.transmissaoOriginal = transmissaoOriginal;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }


    /* Clone */

    public Series clone() {
        Series clone = new Series();

        clone.setNome(this.nome);
        clone.setFormato(this.formato);
        clone.setDuracao(this.duracao);
        clone.setPaisDeOrigem(this.paisDeOrigem);
        clone.setIdiomaOriginal(this.idiomaOriginal);
        clone.setEmissoraDeTelevisao(this.emissoraDeTelevisao);
        clone.setTransmissaoOriginal(this.transmissaoOriginal);
        clone.setNumeroTemporadas(this.numeroTemporadas);
        clone.setNumeroEpisodios(this.numeroEpisodios);

        return clone;
    }


    /* Entrada e saida */

    /**
     * Mostra na tela os valores de cada atributo, separados por espaco.
    */
    public void imprimir() {
        String strSerie = (
            nome + " " + formato + " " + duracao + " " + paisDeOrigem
            + " " + idiomaOriginal + " " + emissoraDeTelevisao + " "
            + transmissaoOriginal);
        MyIO.println(trimSpace(strSerie) + " " + numeroTemporadas + " " + numeroEpisodios);
    }

    public String trimSpace(String s) {
        return s.replaceAll("(\\s){2,}", " ");
    }


    /**
     * Le de um arquivo HTML as linhas contendo os valores que serao designados
     * a cada atributo.
     * @param path Caminho do arquivo no SO.
     * @throws Exception
     */
    public void ler(String fileName) throws Exception {
        // definir caminho do arquivo
        String path = "/tmp/series/" + fileName;

        // criar objeto BufferedReader para leitura do arquivo
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        // atribuir título
        this.setNome(parseTitle(fileName));

        // procurar tabela que contem o restante dos dados
        while (!br.readLine().contains("infobox_v2"));

        // buscar as linhas que precedem dado procurado, fazer parse e passar
        // valor ao respectivo atributo
        while (!br.readLine().contains("Formato"));
        this.setFormato(removeTags(br.readLine()));

        while (!br.readLine().contains("Duração"));
        this.setDuracao(removeTags(br.readLine()));

        while (!br.readLine().contains("País de origem"));
        this.setPaisDeOrigem(removeTags(br.readLine()));

        while (!br.readLine().contains("Idioma original"));
        this.setIdiomaOriginal(removeTags(br.readLine()));

        while (!br.readLine().contains("Emissora de televisão original"));
        this.setEmissoraDeTelevisao(removeTags(br.readLine()));

        while (!br.readLine().contains("Transmissão original"));
        this.setTransmissaoOriginal(removeTags(br.readLine()));

        while (!br.readLine().contains("N.º de temporadas"));
        this.setNumeroTemporadas(justInt(removeTags(br.readLine())));

        while (!br.readLine().contains("N.º de episódios"));
        this.setNumeroEpisodios(justInt(removeTags(br.readLine())));

        // fechar arquivo
        br.close();
    }

    /* Metodos auxiliares */

    public String parseHtml(String line) {
        // limpar tags e referencias de caracteres html com regex
        return line.replaceAll("(<[^>]*>)|(&.*?;)", "");
    }

    public int parseHtmlInt(String line) {
        // limpar tags e referencias de caracteres html com regex
        return Integer.parseInt(line.replaceAll("(<[^>]*>)|(\\d+)|(.*)", "$2"));
    }

    public String parseTitle(String fileName) {
        String title = "";
        int n = fileName.length() - 5;

        for (int i = 0; i < n; i++)
        {
            if (fileName.charAt(i) == '_') {
                title += ' ';
            } else {
                title += fileName.charAt(i);
            }
        }
        return title;
    }

    //método para tratar a linha, deixar apenas números e converter o retorno de String para Integer
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //conversão da string resp para número inteiro a ser retornado
    }

    //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
    public String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // é testado para verificar se o contador i ainda está dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o laço de repetição é encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exceções presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags é concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }

}

public class ListaSeries {
    private Series lista[];
    private int size = 0;

    public ListaSeries() {
        lista = new Series[100];
    }

    public void inserirInicio(Series serie) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        for (int i = size; i > 0; i--) {
            Series temp = lista[i-1].clone();
            lista[i] = temp;
        }

        lista[0] = serie;
        size++;
    }

    public void inserirFim(Series serie) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        lista[size] = serie;
        size++;
    }

    public void inserir(Series serie, int index) throws Exception {
        if (size >= lista.length || index < 0 || index > size) {
            throw new Exception("ERRO: tamanho excedido");
        }

        for (int i = size; i > index; i--) {
            Series temp = lista[i-1].clone();
            lista[i] = temp;
        }

        lista[index] = serie;
        size++;
    }

    public void removerInicio() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        Series removed = lista[0].clone();
        size--;

        for (int i = 0; i < size; i++) {
            Series temp = lista[i+1].clone();
            lista[i] = temp;
        }

        MyIO.println("(R) " + removed.getNome());
    }

    public void removerFim() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        Series removed = lista[--size].clone();
        MyIO.println("(R) " + removed.getNome());
    }

    public void remover(int index) throws Exception {
        if (size == 0 || index < 0 || index >= size) {
            throw new Exception("ERRO: lista vazia ou posição inválida");
        }

        Series removed = lista[index].clone();
        size--;

        for (int i = index; i < size; i++) {
            Series temp = lista[i+1].clone();
            lista[i] = temp;
        }

        MyIO.println("(R) " + removed.getNome());
    }

    public void mostrar() {
        for (int i = 0; i < size; i++) {
            lista[i].imprimir();
        }
    }

    public static void main(String[] args) {
        // definir charset
        MyIO.setCharset("UTF-8");

        // definir dados
        ListaSeries lista = new ListaSeries();
        int commands;

        // ler nome do arquivo a ler
        String line = MyIO.readLine();

        while (!line.equals("FIM")) {
            Series serie = new Series();
            try {
                // tentar ler arquivo
                serie.ler(line);
            } catch (Exception e) {
                MyIO.println("Erro ao ler arquivo `" + line + "`");
            }

            try {
                lista.inserirFim(serie);
            } catch (Exception e) {
                e.printStackTrace();
            }

            line = MyIO.readLine();
        }

        commands = MyIO.readInt();

        for (int i = 0; i < commands; i++) {
            line = MyIO.readLine();
            String cmd = line.substring(0, 2);

            if (cmd.equals("II")) {
                Series serie = new Series();
                try {
                    serie.ler(line.substring(3));
                } catch (Exception e) {
                    MyIO.println("Erro ao ler arquivo `" + line + "`");
                }

                try {
                    lista.inserirInicio(serie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cmd.equals("IF")) {
                Series serie = new Series();
                try {
                    serie.ler(line.substring(3));
                } catch (Exception e) {
                    MyIO.println("Erro ao ler arquivo `" + line + "`");
                }

                try {
                    lista.inserirFim(serie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cmd.equals("I*")) {
                Series serie = new Series();
                int index = Integer.parseInt(line.substring(3,5));

                try {
                    serie.ler(line.substring(6));
                } catch (Exception e) {
                    MyIO.println("Erro ao ler arquivo `" + line + "`");
                }
                try {
                    lista.inserir(serie, index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cmd.equals("RI")) {
                try {
                    lista.removerInicio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cmd.equals("RF")) {
                try {
                    lista.removerFim();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (cmd.equals("R*")) {
                int index = Integer.parseInt(line.substring(3,5));

                try {
                    lista.remover(index);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        lista.mostrar();
    }
}
