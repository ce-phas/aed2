import java.io.*;
import java.util.*;

/**
 * @author Pedro H. Amorim Sá
 * @version 1.0
 * @since 2021-10-02
 */

public class SeriesLinear {
    // definir variável de contagem
    public static int cmpCount = 0;

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

    SeriesLinear() {}

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
    SeriesLinear(
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

    public SeriesLinear clone() {
        SeriesLinear clone = new SeriesLinear();

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
        MyIO.println(nome + " " + formato + " " + duracao + " " + paisDeOrigem
                     + " " + idiomaOriginal + " " + emissoraDeTelevisao + " "
                     + transmissaoOriginal + " " + numeroTemporadas + " "
                     + numeroEpisodios);
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

        // remover 5 posições do fim referentes à extensão 'html'
        int n = fileName.length() - 5;

        for (int i = 0; i < n; i++)
        {
            if (fileName.charAt(i) == '_') {
                // substituir underscore por espaço
                title += ' ';
            } else {
                // concatenar demais caracteres à nova string
                title += fileName.charAt(i);
            }
        }
        return title;
    }

    /* Pesquisa */

    /**
     * Método de pesquisa sequencial para uma lista de objetos SeriesLinear.
     * @param seriesList Lista de objetos SeriesLinear.
     * @param term Termo procurado dentre os nomes das séries.
     * @return true, se encontrado; false, caso contrário.
     */
    public static boolean search(List<SeriesLinear> seriesList, String term) {
        boolean found = false;

        // instanciar o iterador para a lista
        ListIterator<SeriesLinear> seriesIter = seriesList.listIterator();

        // procurar enquanto valor não for encontrado ou até o fim da lista
        while (!found && seriesIter.hasNext()) {
            found = term.equals(seriesIter.next().getNome());

            // incrementar o contador de comparações
            cmpCount++;
        }

        return found;
    }

    /* Arquivo log */

    /**
     * Método para criar um arquivo de log com matrícula, tempo de execução do
     * algoritmo de busca utilizado e total de comparações.
     * @param t Tempo de execução em segundos.
     * @throws Exception
     */
    public static void logFile(double t) throws Exception {
        FileWriter fw = new FileWriter("742626_sequencial.txt");
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("742626\t" + t + "s\t" + cmpCount);
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

        // instanciar lista de objetos
        List<SeriesLinear> series = new ArrayList<SeriesLinear>();

        // ler nome do arquivo
        String line = MyIO.readLine();

        // criar objetos e inserir na lista
        while (!line.equals("FIM")) {
            SeriesLinear serie = new SeriesLinear();
            series.add(serie);
            serie.setNome(serie.parseTitle(line));

            // ler novo nome de arquivo
            line = MyIO.readLine();
        }

        line = MyIO.readLine();

        // iniciar contagem de tempo
        start = new Date().getTime();

        // ler termos de pesquisa e buscar
        while (!line.equals("FIM")) {
            if (search(series, line)) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NÃO");
            }

            line = MyIO.readLine();
        }

        // encerrar contagem de tempo
        end = new Date().getTime();

        // calcular tempo de execução em segundos
        runtime = (end - start) / 1000.0;

        // registrar em arquivo log
        try {
            logFile(runtime);
        } catch (Exception e) {
            MyIO.println("Erro ao criar arquivo de log");
        }
    }
}
