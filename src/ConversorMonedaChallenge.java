import java.util.Scanner;

public class ConversorMonedaChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Consultar consultar = new Consultar();
        Convertir convertir = new Convertir();

        int option = 0;
        double valor;
        String json = "";

        while(option != 7){
            System.out.println("""
                *****************************************
                Sea bienvenido/a al Conversor de Moneda
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Salir
                Elija una opción válida:
                *****************************************""");
            option = sc.nextInt();
            if(option != 7){
                System.out.println("Ingrese el valor que deseas convertir:");
                valor = sc.nextDouble();

                switch (option){
                    case 1:
                        json = consultar.Consultar("USD","ARS",valor);
                        break;
                    case 2:
                        json = consultar.Consultar("ARS","USD",valor);
                        break;
                    case 3:
                        json = consultar.Consultar("USD","BRL",valor);
                        break;
                    case 4:
                        json = consultar.Consultar("BRL","USD",valor);
                        break;
                    case 5:
                        json = consultar.Consultar("USD","COP",valor);
                        break;
                    case 6:
                        json = consultar.Consultar("COP","USD",valor);
                        break;
                    case 7:
                        return;
                }
                Moneda moneda = convertir.convertirJson(json);
                System.out.println("El valor "+valor+" ["+moneda.base_code()+"] corresponde al valor final de =>>> "+
                        moneda.conversion_result()+"["+
                        moneda.target_code()+"]");
            }
        }
    }
}
