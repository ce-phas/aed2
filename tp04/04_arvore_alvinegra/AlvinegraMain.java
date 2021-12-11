import java.io.*;
import java.lang.Math;
import java.util.Date;

/**
 * @author Pedro H. Amorim Sá
 * @version 1.0
 * @since 2021-12-07
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

    Series() {
    }

    /**
     * Construtor que recebe parametros para todos os atributos do objeto.
     * 
     * @param nome                Titulo da serie.
     * @param formato             Descricao do formato da serie.
     * @param duracao             Descricao do tempo de duracao da serie.
     * @param paisDeOrigem        Pais de origem da serie.
     * @param idiomaOriginal      Idioma original da serie.
     * @param emissoraDeTelevisao Emissora de televisao da serie.
     * @param transmissaoOriginal Periodo de transmissao original da serie.
     * @param numeroTemporadas    Total de temporadas da serie.
     * @param numeroEpisodios     Total de episodios da serie.
     */
    Series(
            String nome, String formato, String duracao, String paisDeOrigem,
            String idiomaOriginal, String emissoraDeTelevisao,
            String transmissaoOriginal, int numeroTemporadas,
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
        String strSerie = (nome + " " + formato + " " + duracao + " " + paisDeOrigem
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
     * 
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
        while (!br.readLine().contains("infobox_v2"))
            ;

        // buscar as linhas que precedem dado procurado, fazer parse e passar
        // valor ao respectivo atributo
        while (!br.readLine().contains("Formato"))
            ;
        this.setFormato(parseHtml(br.readLine()));

        while (!br.readLine().contains("Duração"))
            ;
        this.setDuracao(parseHtml(br.readLine()));

        while (!br.readLine().contains("País de origem"))
            ;
        this.setPaisDeOrigem(parseHtml(br.readLine()));

        while (!br.readLine().contains("Idioma original"))
            ;
        this.setIdiomaOriginal(parseHtml(br.readLine()));

        while (!br.readLine().contains("Emissora de televisão original"))
            ;
        this.setEmissoraDeTelevisao(parseHtml(br.readLine()));

        while (!br.readLine().contains("Transmissão original"))
            ;
        this.setTransmissaoOriginal(parseHtml(br.readLine()));

        while (!br.readLine().contains("N.º de temporadas"))
            ;
        this.setNumeroTemporadas(parseHtmlInt(br.readLine()));

        while (!br.readLine().contains("N.º de episódios"))
            ;
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

        for (int i = 0; i < n; i++) {
            if (fileName.charAt(i) == '_') {
                title += ' ';
            } else {
                title += fileName.charAt(i);
            }
        }
        return title.trim();
    }
}

/* Classe nó */

class No {
    public Series elemento;
    public No esq;
    public No dir;
    public boolean cor;

    public No(Series elemento) {
        this(elemento, null, null, false);
    }

    public No(Series elemento, boolean cor) {
        this(elemento, null, null, cor);
    }

    public No(Series elemento, No esq, No dir, boolean cor) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}

/* Classe árvore */

class ArvoreSeries {
    private No raiz;

    // contador
    private int cmpCount;

    public ArvoreSeries() {
        raiz = null;
    }

    public boolean pesquisar(String valor) {
        System.out.print("raiz ");
        return pesquisar(valor, raiz);
    }

    private boolean pesquisar(String valor, No i) {
        boolean resp;

        if (i == null) {
            cmpCount++;
            resp = false;
        } else if (valor.compareTo(i.elemento.getNome()) == 0) {
            cmpCount += 2;
            resp = true;
        } else if (valor.compareTo(i.elemento.getNome()) < 0) {
            cmpCount += 3;
            System.out.print("esq ");
            resp = pesquisar(valor, i.esq);
        } else {
            cmpCount += 3;
            System.out.print("dir ");
            resp = pesquisar(valor, i.dir);
        }
        return resp;
    }

    public void inserir(Series elemento) {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new No(elemento);

            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new No(elemento);
            } else {
                raiz.dir = new No(elemento);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) < 0) {
                raiz.esq = new No(elemento);
            } else if (elemento.getNome().compareTo(raiz.dir.elemento.getNome()) < 0) {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (elemento.getNome().compareTo(raiz.elemento.getNome()) > 0) {
                raiz.dir = new No(elemento);

            } else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome()) > 0) {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void inserir(Series elemento, No bisavo, No avo, No pai, No i) {
        if (i == null) {
            if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                i = pai.esq = new No(elemento, true);
            } else {
                i = pai.dir = new No(elemento, true);
            }
            if (pai.cor) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor && i.dir.cor) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                // não fazer nada: elemento repetido
            }
        }
    }

    private void balancear(No bisavo, No avo, No pai, No i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor) {
            // 4 s de reequilibrios e acoplamento
            if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou
                                                                                // direita-esquerda
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    private No rotacaoDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private No rotacaoEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        return noDir;
    }

    private No rotacaoDirEsq(No no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
     }
  
     private No rotacaoEsqDir(No no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
     }

     /**
     * Método para criar um arquivo de log com matrícula, total de comparações,
     * total de movimentações e tempo de execução, em segundos, do algoritmo de
     * ordenação utilizado.
     * 
     * @param t Tempo de execução em segundos.
     * @throws Exception
     */
    public void logFile(double t, String filename) throws Exception {
        FileWriter fw = new FileWriter("742626_" + filename + ".txt");
        try (BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write("742626\t" + cmpCount + "\t" + t);
        }
    }
}

public class AlvinegraMain {
    public static void main(String[] args) {
        // definir charset
        MyIO.setCharset("UTF-8");

        // definir dados para contagem de tempo de execução
        double start;
        double end;
        double runtime;

        ArvoreSeries series = new ArvoreSeries();

        String line = MyIO.readLine();

        // inserção inicial
        while (!line.equals("FIM")) {
            Series serie = new Series();
            try {
                // tentar ler arquivo
                serie.ler(line);
            } catch (Exception e) {
                MyIO.println("Erro ao ler arquivo `" + line + "`");
            }
            series.inserir(serie);
            line = MyIO.readLine();
        }

        line = MyIO.readLine();

        // iniciar contagem de tempo
        start = new Date().getTime();
        
        // procurar nomes na árvore
        while (!line.equals("FIM")) {
            String resp = series.pesquisar(line.trim()) ? "SIM" : "NAO";
            System.out.println(resp);

            line = MyIO.readLine();
        }

        // encerrar contagem de tempo e calcular tempo de execução
        end = new Date().getTime();
        runtime = (end - start) / 1000.0;

        try {
            series.logFile(runtime, "alvinegra");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
