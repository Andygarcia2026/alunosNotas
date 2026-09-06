import java.nio.file.Files;  // biblioteca importada pra usar Files.readAllLines
import java.nio.file.Path;    // biblioteca importada pra usar Path.of
import java.util.List;        // biblioteca importada pra usar List<String>
import java.util.Scanner;      //biblioteca importada para poder usar o Scanner

public class DadosAlunos {           //nome do arquivo java em letra maiuscula para ser considerado uma classe e poder ser chamado pelo main
    public static void executar() {    //método usado para chamar este arquivo no Main.java

        try {
            Scanner sc = new Scanner(System.in);   //chama a biblioteca do Scanner para o usuario digitar


            System.out.print("Digite o nome do aluno: ");    //pede o nome do aluno para consultar no TXT
            String nomeBusca = sc.nextLine();                //guarda esse nome na variavel string nomeBusca para poder ser usada depois

            List<String> linhas = Files.readAllLines(Path.of("src/AlunosNotas"));  //le o arquivo txt e transforma cada linha em um item
            String linhaEncontrada = null;    //cria uma variavel vazia para guardar a linha do aluno quando encontrar

            for (String l : linhas) {     //repete passando por cada linhado TXT l é cada linha da vez
                String[] temp = l.split(";");    //pega cada linha por vez e corta dividindo em vetores usando o ;
                if (temp[0].trim().equalsIgnoreCase(nomeBusca.trim())) {  //ve se o nome digitado anteriormenta na variavel nomeBusca é igual ao nome no vetor[0]
                    linhaEncontrada = l;   //se achar o nome do aluno guarda a linha inteira na variavel l
                    break;   // para o for para nao precisar procurar mais
                }
            }

            if (linhaEncontrada == null) {   //se a linha encontrada for vazia
                System.out.println("Aluno " + nomeBusca + " não encontrado!");   //retorna na tela que nao achou o aluno
                return;                        //fecha todo o codigo e retorna ao Main para nao tentar calcular a media do aluno que não foi achado
            }



            String[] partes = linhaEncontrada.split(";"); //aqui declaro que cada parte de cada linha vai ser dividido em partes através do ;
            String nome = partes[0]; //aqui declaro que o vetor 0 será sempre o nome em forma de String

            double nota1 = Double.parseDouble(partes[1].trim().replace(",", "."));   //aqui estou declarando que a nota1 vai ser o vetor 1
            double nota2 = Double.parseDouble(partes[2].trim().replace(",", "."));   //aqui estou declarando que a nota2 vai ser o vetor 2
            double nota3 = Double.parseDouble(partes[3].trim().replace(",", "."));   //aqui estou declarando que a nota3 vai ser o vetor 3
            double nota4 = Double.parseDouble(partes[4].trim().replace(",", "."));   //aqui estou declarando que a nota4 vai ser o vetor 4
            double nota5 = Double.parseDouble(partes[5].trim().replace(",", "."));   //aqui estou declarando que a nota5 vai ser o vetor 5

            System.out.println("Nome do aluno : " + nome); //aqui estou pedindo o nome do aluno para ser verificado no arquivo txt

            System.out.println("Digite a primeira nota da RECUPERAÇÃO: ");    //pedindo a primeira nota de recuperação do aluno
            double recupera1 = Double.parseDouble(sc.nextLine().trim().replace(",", ".")); //declarando a variavel da primeira nota de rec e convertendo , para .

            System.out.println("Digite a segunda nota da RECUPERAÇÃO: ");    //pedindo a segunda nota da recuperação para o aluno
            double recupera2 = Double.parseDouble(sc.nextLine().trim().replace(",", "."));  //declarando a variavel da segunda nota de rec e convertendo , para .

            double media = (nota1 + nota2 + nota3 + nota4 + nota5) / 5;   //conta para saber a media das 5 notas ja disponiveis no TXT AlunosNotas
            double mediaRecuperacao = (recupera1 + recupera2) / 2;        //conta para saber a media das duas notas de recuperação (notas dadas pelo usuario)
            double mediaFinal = (media + mediaRecuperacao) / 2;           //conta para saber a mediaFinal do aluno somando a media mais a media de recuperação

            if (mediaFinal >= 7) {                                         //se a media for maior ou igual a 7 aprovado
                System.out.println("Você esta APROVADO!! PARABÉNS :) ");   // imprime na tela aprovado
            } else {                                                       //resto ( se nao for maior ou igual a 7 vai ser menor.. então reprovado
                System.out.println("Você está REPROVADO!! :( ");           //imprime na tela reprovado
            }


            System.out.println("A média das 5 notas foi : " + media);                   //imprime na tela media das 5 notas ja disponiveis no arquivo TXT AlunosNotas
            System.out.println("Sua média de RECUPERAÇÃO foi : " + mediaRecuperacao);//imprime na tela a media das duas notas da recuperação
            System.out.println("Sua NOTA FINAL foi : " + mediaFinal);                //imprime na tela a mediaFinal das duas medias (5 notas+ 2 notas rec)

            sc.close();  // para fechar a biblioteca Scanner

        } catch (Exception e) {
            System.out.println(" Erro ao ler o arquivo " + e.getMessage());

        }
    }

}
