import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConversorMonedaChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String json = "";
        int option = 0;
        double valor;
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        Map<String, Map<Moneda,Double>> historial = new HashMap<>();

        while(option != 8){
            System.out.println("""
                *****************************************
                Sea bienvenido/a al Conversor de Moneda
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Mostrar historial
                8) Salir
                Elija una opción válida:
                *****************************************""");
            option = sc.nextInt();
            if(option != 7 && option != 8){
                System.out.println("Ingrese el valor que deseas convertir:");
                valor = sc.nextDouble();
                json = menu(option,valor,json);
                Moneda moneda = convertirJson(json);
                Map<Moneda,Double> monedas = new HashMap<>();
                monedas.put(moneda,valor);

                Date tiempo = new Date();
                historial.put(formato.format(tiempo.getTime()),monedas);

                System.out.println("El valor "+valor+" ["+moneda.base_code()+"] corresponde al valor final de =>>> "+
                        moneda.conversion_result()+"["+
                        moneda.target_code()+"]");

            }else if(option == 7){
                List<String> horasOrdenadas = new ArrayList<>(historial.keySet());
                Collections.sort(horasOrdenadas);

                System.out.println("------------HISTORIAL DE CONVERSIONES------------");

                for(String hora :horasOrdenadas){
                    System.out.println("Hora: " + hora);
                    for(Moneda moneda : historial.get(hora).keySet()){
                        System.out.println("Moneda Base: " + moneda.base_code());
                        System.out.println("Moneda Target: " + moneda.target_code());
                        double valorIngresado = historial.get(hora).get(moneda);
                        System.out.println("Valor de entrada: " + valorIngresado);
                        System.out.println("Valor de conversión : " + moneda.conversion_result());
                        System.out.println("------------------------------------------------");
                    }
                }
            }
        }
    }
    private static String menu(int option, double valor, String json){
        Consultar consultar = new Consultar();
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
        }
        return json;
    }

    private static Moneda convertirJson(String json){
        Convertir convertir = new Convertir();
        Moneda moneda = convertir.convertirJson(json);
        return moneda;
    }
}