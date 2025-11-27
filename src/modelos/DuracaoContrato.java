package modelos;

public enum DuracaoContrato {
    CURTO(3),
    MEDIO(6),
    LONGO(12);

    private final int meses;

    DuracaoContrato(int meses) {
        this.meses = meses;
    }

    public int getMeses() {
        return meses;
    }
}