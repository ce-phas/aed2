import java.io.*;
import java.util.*;

/**
 * Lista de séries com ordenação por algoritmo heapsort.
 *
 * @author Pedro H. Amorim Sá
 * @version 1.1
 * @since 2021-10-28
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

    /**
     * Construtor-padrão
     */
    Series() {}

    /**
     * Construtor que recebe parametros para todos os atributos do objeto.
     * @param nome Titulo da série.
     * @param formato Descrição do formato da série.
     * @param duracao Descrição do tempo de duracao da série.
     * @param paisDeOrigem País de origem da série.
     * @param idiomaOriginal Idioma original da série.
     * @param emissoraDeTelevisao Emissora de televisao da série.
     * @param transmissaoOriginal Periodo de transmissão original da série.
     * @param numeroTemporadas Total de temporadas da série.
     * @param numeroEpisodios Total de episodios da série.
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


    /* Entrada e saída */

    /**
     * Mostra na tela os valores de cada atributo, separados por espaço.
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
     * Lê de um arquivo HTML as linhas contendo os valores que serão designados
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
        this.setFormato(parseHtml(br.readLine()));

        while (!br.readLine().contains("Duração"));
        this.setDuracao(parseHtml(br.readLine()));

        while (!br.readLine().contains("País de origem"));
        this.setPaisDeOrigem(parseHtml(br.readLine()));

        while (!br.readLine().contains("Idioma original"));
        this.setIdiomaOriginal(parseHtml(br.readLine()));

        while (!br.readLine().contains("Emissora de televisão original"));
        this.setEmissoraDeTelevisao(parseHtml(br.readLine()));

        while (!br.readLine().contains("Transmissão original"));
        this.setTransmissaoOriginal(parseHtml(br.readLine()));

        while (!br.readLine().contains("N.º de temporadas"));
        this.setNumeroTemporadas(parseHtmlInt(br.readLine()));

        while (!br.readLine().contains("N.º de episódios"));
        this.setNumeroEpisodios(parseHtmlInt(br.readLine()));

        // fechar arquivo
        br.close();
    }


    /* Métodos auxiliares */

    public String parseHtml(String line) {
        // limpar tags e referências de caracteres html com regex
        return line.replaceAll("(<[^>]*>)|(&.*?;)", "");
    }

    public int parseHtmlInt(String line) {
        // limpar tags e referências de caracteres html com regex
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
}


/* Classe de estrutura de dados */

public class ListaSeries {
    // declarar arranjo e tamanho
    private Series lista[];
    private int size;

    // definir variáveis de contagem
    private int cmpCount;
    private int swpCount;

    // construtor-padrão
    public ListaSeries() {
        lista = new Series[100];
        size = 0;
        cmpCount = 0;
        swpCount = 0;
    }

    /**
     * Insere um objeto Series no início da lista.
     * @param serie Objeto a inserir.
     * @throws Exception
     */
    public void inserirInicio(Series serie) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        for (int i = size; i > 0; i--) {
            // clonar objeto da posição anterior e copiar para a nova
            Series temp = lista[i-1].clone();
            lista[i] = temp;
        }

        // inserir objeto no início e incrementar tamanho da lista
        lista[0] = serie;
        size++;
    }


    /**
     * Insere um objeto Series no fim da lista.
     * @param serie Objeto a inserir.
     * @throws Exception
     */
    public void inserirFim(Series serie) throws Exception {
        if (size >= lista.length) {
            throw new Exception("ERRO: tamanho excedido");
        }

        // inserir objeto no fim e incrementar tamanho da lista
        lista[size] = serie;
        size++;
    }


    /**
     * Insere um objeto Series na lista em um índice específico.
     * @param serie Objeto a inserir.
     * @param index Posição para inserir.
     * @throws Exception
     */
    public void inserir(Series serie, int index) throws Exception {
        // verificar se tamanho e índice são válidos
        if (size >= lista.length || index < 0 || index > size) {
            throw new Exception("ERRO: tamanho excedido");
        }

        // deslocar objetos para o final, a partir da posição informada
        for (int i = size; i > index; i--) {
            Series temp = lista[i-1].clone();
            lista[i] = temp;
        }

        // inserir objeto na posição e incrementar tamanho da lista
        lista[index] = serie;
        size++;
    }


    /**
     * Remove um objeto do início da lista.
     * @throws Exception
     */
    public void removerInicio() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        // guardar objeto removido e decrementar tamanho
        Series removed = lista[0].clone();
        size--;

        // deslocar objetos restantes para o início da lista
        for (int i = 0; i < size; i++) {
            Series temp = lista[i+1].clone();
            lista[i] = temp;
        }

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Remove um objeto do fim da lista.
     * @throws Exception
     */
    public void removerFim() throws Exception {
        if (size == 0) {
            throw new Exception("ERRO: lista vazia");
        }

        // guardar objeto removido e decrementar tamanho
        Series removed = lista[--size].clone();

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Remove um objeto da lista de um índice específico.
     * @throws Exception
     */
    public void remover(int index) throws Exception {
        // verificar se tamanho e índice são válidos
        if (size == 0 || index < 0 || index >= size) {
            throw new Exception("ERRO: lista vazia ou posição inválida");
        }

        // guardar objeto removido e decrementar tamanho
        Series removed = lista[index].clone();
        size--;

        // deslocar para a esquerda os objetos à direita da posição
        for (int i = index; i < size; i++) {
            Series temp = lista[i+1].clone();
            lista[i] = temp;
        }

        // mostrar atributo 'Nome' do objeto removido
        MyIO.println("(R) " + removed.getNome());
    }


    /**
     * Método para trocar dois elementos de posição em uma lista.
     * @param i Índice do primeiro elemento.
     * @param j Índice do segundo elemento.
     */
    private void swap(int i, int j) {
        Series tmp = lista[i].clone();
        lista[i] = lista[j].clone();
        lista[j] = tmp;
    }


    /**
     * Método para ordenação inicial da lista, com o nome da série como chave.
     */
    public void sortName() {
        sortName(0, size - 1);
    }


    private void sortName(int l, int r) {
        int i = l;
        int j = r;
        Series p = lista[(l + r) / 2];

        while (i <= j) {
            while (lista[i].getNome().compareTo(p.getNome()) < 0) i++;
            while (lista[j].getNome().compareTo(p.getNome()) > 0) j--;

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (l < j) sortName(l, j);
        if (i < r) sortName(i, r);
    }


    /* Heapsort */

    private void construir(int n) {
        for (int i = n; i > 1 && lista[i].getFormato().trim().compareTo(
             lista[i/2].getFormato().trim()) > 0; i /= 2) {
            swap(i, i/2);
        }
    }


    private void reconstruir(int n) {
        int i = 1;
        while (i <= n / 2) {
            int filho = getMaiorFilho(i, n);
            if (lista[i].getFormato().trim().compareTo(
                lista[filho].getFormato().trim()) < 0) {
                swap(i, filho);
                i = filho;
            } else {
                i = n;
            }
        }
    }


    private int getMaiorFilho(int i, int n) {
        int filho;

        if (2 * i == n || lista[2*i].getFormato().trim().compareTo(
            lista[2*i+1].getFormato().trim()) > 0) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }

        return filho;
    }


    public void sort() {
        Series[] tmp = new Series[101];

        for (int i = 0; i < size; i++) {
            tmp[i+1] = lista[i];
        }

        lista = tmp;

        for (int heapSize = 2; heapSize <= size; heapSize++) {
            this.construir(heapSize);
        }

        int heapSize = size;

        while (heapSize > 1) {
            swap(1, heapSize--);
            reconstruir(heapSize);
        }

        tmp = lista;
        lista = new Series[100];

        for (int i = heapSize; lista[i].getFormato().trim().compareTo(
             lista[i/2].getFormato().trim()) > 0; i /= 2) {
            swap(i, i/2);
        }
    }


    /**
     * Mostra todos os atributos de cada objeto da lista.
     */
    public void mostrar() {
        for (int i = 0; i < size; i++) {
            lista[i].imprimir();
        }
    }


    /* Arquivo log */

    /**
     * Método para criar um arquivo de log com matrícula, total de comparações,
     * total de movimentações e tempo de execução, em segundos, do algoritmo de
     * ordenação utilizado.
     * @param t Tempo de execução em segundos.
     * @throws Exception
     */
    public void logFile(double t) throws Exception {
        FileWriter fw = new FileWriter("742626_heapsort.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("742626\t" + cmpCount + "\t" + swpCount + "\t" + t);
        writer.close();
    }


    /* Main */

    public static void main(String[] args) {
        // definir charset
        MyIO.setCharset("UTF-8");

        // definir dados para contagem de tempo de execução
        double start;
        double end;
        double runtime;

        // definir dados
        ListaSeries lista = new ListaSeries();

        // ler nome do arquivo
        String line = MyIO.readLine();

        // criar e inserir objetos na lista
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

        // ordenação inicial por nome
        lista.sortName();

        // iniciar contagem de tempo
        start = new Date().getTime();

        // realizar ordenação
        lista.sort();

        // encerrar contagem de tempo e calcular tempo de execução
        end = new Date().getTime();
        runtime = (end - start) / 1000.0;

        // toString() de todos os objetos da lista
        lista.mostrar();

        // registrar em arquivo log
        try {
            lista.logFile(runtime);
        } catch (Exception e) {
            MyIO.println("Erro ao criar arquivo de log");
        }
    }
}
