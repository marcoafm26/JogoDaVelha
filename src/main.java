import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // inicializacao
        Scanner ler= new Scanner(System.in);
        Random random = new Random();
        char[] velha = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        int key,keyMaquina;

        // escolher o time
        char time,timeMaquina = 'o';
        System.out.println("Esclhar um time : X ou O");
        time = ler.next().toLowerCase(Locale.ROOT).charAt(0);
        while (time != 'o' && time !='x'){
            System.out.println("Escolha invalida. Selecione uma dentre as duas opcoes.");
            time = ler.next().toLowerCase(Locale.ROOT).charAt(0);
        }
        if (time == 'o')
            timeMaquina = 'x';

        // comeco do jogo
        for (int rodada = 0; rodada < 9; rodada++) {

            // imprimir teclado numerico
            String table = "1 | 2 | 3 \n4 | 5 | 6 \n7 | 8 | 9 \n";
            System.out.println(table);
            System.out.println("Digite o numero referente a posicao escolhida:");


            // validar valor inserido
            key = ler.nextInt();
            while (key < 1 || key > 9 || !validaPos(velha,key-1)){
                System.out.println("Valor invalido, digite um valor entre 1 e 9 que nao esteja preenchido.");
                key = ler.nextInt();
            }

            // marca jogada do player
            velha[key-1] = time;


            //verifica vitoria
            System.out.println("Sua jogada:");
            imprimir(velha);
            if (verificaVitoria(velha,time)){
                System.out.println("Voce venceu!!");
                break;
            }

            //jogada maquina
            keyMaquina = random.nextInt(1,9);
            System.out.println(keyMaquina);
            while (!validaPos(velha,keyMaquina-1)){
                keyMaquina = random.nextInt(1,9);;
            }

            // marca jogada do player
            velha[keyMaquina-1] = timeMaquina;

            //verifica vitoria maquina
            System.out.println("Jogada da maquina:");
            imprimir(velha);
            if (verificaVitoria(velha,timeMaquina)){
                System.out.println("A maquina venceu :/");
                break;
            }

        }

    }

    public static boolean validaPos(char[] velha,int pos){
        if (velha[pos] != ' ')
            return false;
        else
            return true;
    }

    public static boolean verificaVitoria(char[] velha,char time){
        int contLinha=0,contColuna=0;

        for (int i = 0; i < 3; i++) {
            //verifica colunas
            for (int j = i; j <8; j+=3) {
                if(velha[j] == time ){
                    contColuna++;
                }
            }
            if(contColuna == 3) {
                return true;
            }
            contColuna = 0;
        }

        // verifica linhas
        int aux = 0;
        for (int j = 0; j <8; j++) {
            if(aux == 3){
                aux = 0;
                contLinha = 0 ;
            }
            if(velha[j] == time ){
                contLinha++;
            }

            if(contLinha == 3){
                return true;
            }
            aux++;
        }

        //verifica diagonal 1
            if (velha[0] == time && velha[4] == time && velha[8] == time)
                return true;

        //verifica diagonal 2
            if (velha[2] == time && velha[4] == time && velha[6] == time)
            return true;

            return false;
    }

    public static void imprimir(char[] velha){
        String table = velha[0] + "|" + velha[1] + "|" + velha[2] + "\n" +
                velha[3] + "|" + velha[4] + "|" + velha[5] + "\n" +
                velha[6] + "|" + velha[7] + "|" + velha[8] + "\n";
        System.out.println(table);
    }

}
