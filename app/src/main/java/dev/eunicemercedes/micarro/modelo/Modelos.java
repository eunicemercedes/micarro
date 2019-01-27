package dev.eunicemercedes.micarro.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Modelos {
    @SerializedName("Model")
    @Expose
    private List<Modelo> modelos;

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}
