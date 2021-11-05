import java.io.*;
import java.util.*;

/**
 * @author Pedro H. Amorim Sá
 * @version 1.0
 * @since 2021-11-04
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
}


class Celula {
    public Series elemento;
    public Celula ant;
    public Celula prox;

    public Celula() {
        this(new Series());
    }

    public Celula (Series elemento) {
        this.elemento = elemento;
        this.ant = this.prox = null;
    }
}


public class ListaSeries {
    private Celula primeiro;
    private Celula ultimo;
    private int size;

    // definir variáveis de contagem
    private int cmpCount;
    private int swpCount;

    // construtor para nó cabeça
    public ListaSeries() {
        primeiro = new Celula();
        ultimo = primeiro;
        size = 0;
        cmpCount = 0;
        swpCount = 0;
    }

    /**
     * Insere um objeto Series no início da lista.
     * @param serie Objeto a inserir.
     */
    public void inserirInicio(Series serie) {
        Celula tmp = new Celula(serie);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }

        size++;
        tmp = null;
    }


    /**
     * Insere um objeto Series no fim da lista.
     * @param serie Objeto a inserir.
     */
    public void inserirFim(Series serie) {
        ultimo.prox = new Celula(serie);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        size++;
    }

    /**
     * Insere um objeto Series na lista em um índice específico.
     * @param serie Objeto a inserir.
     * @param index Posição para inserir.
     * @throws Exception Se posição inválida.
     */
    public void inserir(Series serie, int pos) throws Exception {

        // verificar se tamanho e índice são válidos
        if (pos < 0 || pos > size) {
            throw new Exception("ERRO ao inserir: posição (i: " + pos + "s: "
                                + size + ") inválida!");
        } else if (pos == 0) {
            inserirInicio(serie);
        } else if (pos == size) {
            inserirFim(serie);
        } else {
            // caminhar até a posição anterior à inserção
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox);

            Celula tmp = new Celula(serie);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
            size++;
        }
    }

    /**
     * Remove um objeto do início da lista.
     * @throws Exception Se lista vazia.
     */
    public Series removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("ERRO ao remover: lista vazia!");
        }

        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Series resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        size--;

        return resp;
    }

    /**
     * Remove um objeto do fim da lista.
     * @throws Exception Se lista vazia.
     */
    public Series removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("ERRO ao remover: lista vazia!");
        }

        Series resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        size--;
        
        return resp;
    }

    /**
     * Remove um objeto da lista de um índice específico.
     * @throws Exception Se lista vazia.
     */
    public Series remover(int pos) throws Exception {
        Series resp;

        if (primeiro == ultimo) {
            throw new Exception("ERRO: lista vazia!");
        } else if (pos < 0 || pos >= size) {
            throw new Exception("ERRO ao remover: posição (i: " + pos + "s: "
                                + size + ") inválida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == size - 1) {
            resp = removerFim();
        } else {
            // caminhar até a posição anterior à de remoção
            Celula i = primeiro.prox;
            for (int j = 0; j < pos; j++, i = i.prox);

            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
            size--;
        }

        return resp;
    }

    /**
     * Mostra todos os atributos de cada objeto da lista.
     */
    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            i.elemento.imprimir();
        }
    }

    /**
     * Método para trocar dois elementos de posição em uma lista.
     * @param i Índice do primeiro elemento.
     * @param j Índice do segundo elemento.
     */
    private void swap(Celula i, Celula j) {
        Celula tmp = new Celula();
        tmp.elemento = i.elemento.clone();
        i.elemento = j.elemento;
        j.elemento = tmp.elemento;
        tmp = null;
    }

    /**
     * Método para ordenação de lista baseado no algoritmo quicksort.
     */
    public void sort() {
        sort(0, size - 1);
    }

    private void sort(int l, int r) {
        try {
            int i = l;
            int j = r;
            Series p = getCel((l + r) / 2).elemento;

            while (i <= j) {
                Celula cel_i = getCel(i);
                Celula cel_j = getCel(j);
                int cmpPaisL = cel_i.elemento.getPaisDeOrigem().trim().compareTo(p.getPaisDeOrigem().trim());
                int cmpPaisR = cel_j.elemento.getPaisDeOrigem().trim().compareTo(p.getPaisDeOrigem().trim());
                int cmpNomeL = cel_i.elemento.getNome().compareTo(p.getNome());
                int cmpNomeR = cel_j.elemento.getNome().compareTo(p.getNome());

                cmpCount++;
                while (cmpPaisL < 0 || (cmpPaisL == 0 && cmpNomeL < 0)) {
                    i++;
                    cel_i = getCel(i);
                    cmpPaisL = cel_i.elemento.getPaisDeOrigem().trim().compareTo(p.getPaisDeOrigem().trim());
                    cmpNomeL = cel_i.elemento.getNome().compareTo(p.getNome());
                    cmpCount++;
                }

                cmpCount++;
                while (cmpPaisR > 0 || (cmpPaisR == 0 && cmpNomeR > 0)) {
                    j--;
                    cel_j = getCel(j);
                    cmpPaisR = cel_j.elemento.getPaisDeOrigem().trim().compareTo(p.getPaisDeOrigem().trim());
                    cmpNomeR = cel_j.elemento.getNome().compareTo(p.getNome());
                    cmpCount++;
                }

                if (i <= j) {
                    swap(cel_i, cel_j);
                    swpCount++;
                    i++;
                    j--;
                }
            }

            if (l < j) sort(l, j);
            if (i < r) sort(i, r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Celula getCel(int pos) throws Exception {
        Celula cel = primeiro.prox;

        if (pos < 0 || pos > size) {
            throw new Exception("ERRO: posição (i: " + pos + " - s: " + size + ") inválida!");
        } else {
            for (int i = 0; i < pos; i++, cel = cel.prox);
        }
        
        return cel;
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
        FileWriter fw = new FileWriter("742626_quicksort2.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("742626\t" + cmpCount + "\t" + swpCount + "\t" + t);
        writer.close();
    }

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
