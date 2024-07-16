package principal;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        int opt;

        if (!Menu.checkFileExists("src/main/resources/jsonStandard.txt")) {
            System.out.println("O arquivo 'jsonStandard.txt' não foi encontrado. Deseja fazer a primeira atualização das taxas de conversão? ");
            System.out.println("1 - sim, 0 - não");
            opt = scanner.nextInt();
            if (opt == 1) {
                Menu.updatingRates();
            }
        } else {
            System.out.println("Taxas de cambio atualizadas!!");
        }
        int option;
        do {
            Menu.mainMenu();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    Menu.brics();
                    break;
                case 2:
                    Menu.G7();
                    break;
                case 3:
                    Menu.completList();
                    break;
                case 4:
                    Menu.updatingRates();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        } while (option != 5);
    }
}

