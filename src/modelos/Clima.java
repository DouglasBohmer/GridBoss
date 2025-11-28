package modelos;

public enum Clima {
    ENSOLARADO(1.0),
    NUBLADO(0.98),     // Levemente mais rápido/frio
    CHUVA_LEVE(1.10),  // 10% mais lento
    CHUVA_FORTE(1.25); // 25% mais lento

    private final double modificadorTempo;

    Clima(double modificador) {
        this.modificadorTempo = modificador;
    }

    public double getModificadorTempo() {
        return modificadorTempo;
    }
    
    public boolean isChuva() {
        return this == CHUVA_LEVE || this == CHUVA_FORTE;
    }
}