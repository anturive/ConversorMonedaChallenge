import com.google.gson.Gson;

public class Convertir {
    public Moneda convertirJson(String json){
        Gson gson = new Gson();
        Moneda moneda = gson.fromJson(json,Moneda.class);
        return moneda;
    }
}