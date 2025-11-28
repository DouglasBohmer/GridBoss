package modelos;

public enum TipoPista {
    MISTO_PERMANENTE, // Ex: Interlagos, Spa
    RUA_TEMPORARIO,   // Ex: Mônaco, Singapura
    ROVAL,            // Ex: Charlotte Roval (Misto dentro de oval) - Usa habilidade de Rua
    OVAL_CURTO,       // Ex: Bristol, Iowa
    OVAL_SPEEDWAY,    // Ex: Indianapolis, Texas
    SUPERSPEEDWAY;    // Ex: Daytona, Talladega

    /**
     * Verifica se a pista é do tipo Oval (qualquer um deles).
     * Útil para proibir chuva e usar atributo 'habilidadeOval'.
     */
    public boolean isOval() {
        return this == OVAL_CURTO || 
               this == OVAL_SPEEDWAY || 
               this == SUPERSPEEDWAY;
    }
    
    /**
     * Verifica se a pista é de Rua ou Roval.
     * Útil para usar atributo 'habilidadeRua'.
     */
    public boolean isRua() {
        return this == RUA_TEMPORARIO || 
               this == ROVAL;
    }
}