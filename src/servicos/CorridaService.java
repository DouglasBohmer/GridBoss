package servicos;

import modelos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CorridaService {
    
    private Pista pistaAtual;
    private Clima climaAtual;
    private boolean safetyCarNaPista = false;
    private int voltaAtual = 0;
    
    // Agora usamos a lista de CARROS, não de pilotos puros
    private List<CarroDeCorrida> grid = new ArrayList<>();
    
    private Random random = new Random();

    // --- SETUP ---

    public void iniciarSessao(Pista pista, List<Piloto> pilotosInscritos) {
        this.pistaAtual = pista;
        this.voltaAtual = 0;
        this.safetyCarNaPista = false;
        
        // 1. Converter Pilotos em Carros de Corrida
        grid.clear();
        for (Piloto p : pilotosInscritos) {
            CarroDeCorrida carro = new CarroDeCorrida(p);
            // Configuração inicial (Tanque cheio para F1 s/ reabastecimento, etc)
            // Futuramente leremos isso da regra do ano
            grid.add(carro); 
        }
        
        // 2. Definir Clima
        gerarClimaInicial();
        
        System.out.println("Grid formado com " + grid.size() + " carros.");
    }

    private void gerarClimaInicial() {
        if (pistaAtual.isOval()) {
            this.climaAtual = Clima.ENSOLARADO; // Regra de Ouro
        } else {
            // Sorteio simples
            int r = random.nextInt(100);
            if (r < 70) climaAtual = Clima.ENSOLARADO;
            else if (r < 90) climaAtual = Clima.NUBLADO;
            else climaAtual = Clima.CHUVA_LEVE;
        }
    }

    // --- LOOP DA VOLTA ---

    public void simularProximaVolta() {
        if (voltaAtual >= pistaAtual.getQtdVoltas()) return;
        voltaAtual++;

        verificarSafetyCar();

        for (CarroDeCorrida carro : grid) {
            // 1. Calcular Tempo da Volta
            double tempo = calcularTempoFisica(carro);
            
            // 2. Adicionar ao total
            carro.adicionarTempo(tempo);
            
            // 3. Aplicar Desgastes
            if (!safetyCarNaPista) {
                carro.desgastarPneu(pistaAtual.getFatorDesgastePneu());
                carro.consumirCombustivel(pistaAtual.getFatorConsumoCombustivel());
            }
        }

        // 4. Reordenar o Grid (Quem tem menor tempo total fica em 1º)
        grid.sort(Comparator.comparingDouble(CarroDeCorrida::getTempoParaOrdenacao));
        
        // 5. Tentar Ultrapassagens (Simulação visual na lista)
        // Como já ordenamos por tempo, a "ultrapassagem" física já aconteceu.
        // A lógica de "chance de ultrapassar" entra para impedir que um carro
        // muito rápido passe em Mônaco só porque tem tempo menor.
        aplicarBloqueioDePista();
    }

    // --- FÍSICA ---

    private double calcularTempoFisica(CarroDeCorrida carro) {
        if (safetyCarNaPista) return 120.0; // Tempo lento igual para todos

        // Base da pista (ex: 90s)
        double tempo = 90.0; 
        
        // Fator Carro (Equipe)
        if (carro.getEquipe() != null) {
            // Quanto maior a reputação/nível, menor o tempo
            double forcaCarro = carro.getEquipe().getReputacao() / 100.0; 
            tempo -= (forcaCarro * 2.0); // Tira até 2s
        }
        
        // Fator Piloto
        double forcaPiloto = carro.getPiloto().getOverall() / 100.0;
        tempo -= (forcaPiloto * 1.5); // Tira até 1.5s
        
        // Fator Pneu (Desgaste aumenta o tempo)
        double desgaste = (100.0 - carro.getDesgastePneus()) / 100.0; // 0.0 a 1.0 de uso
        tempo += (desgaste * 3.0); // Pneu careca adiciona 3s
        
        // Fator Modo de Pilotagem (Agressivo tira tempo)
        // Ex: NEUTRO (1.0) -> tempo igual
        // Ex: AGRESSIVO (1.1) -> Se base é 90, tira tempo? Não, Agressivo é MAIS RÁPIDO.
        // Então na nossa classe ModoPilotagem, "1.1" de velocidade deve reduzir o tempo.
        // Fórmula: Tempo / Fator
        tempo = tempo / carro.getModo().getFatorVelocidade();

        return tempo;
    }

    /**
     * Simula a dificuldade de passar. Se a pista for travada,
     * devolve posições para quem não conseguiu passar "na marra".
     */
    private void aplicarBloqueioDePista() {
        int dificuldade = pistaAtual.getDificuldadeUltrapassagem(); // 0 a 100
        
        // Se a pista for difícil (ex: Mônaco 95), podemos desfazer trocas de posição
        // se a diferença de tempo for pequena demais.
        // (Lógica complexa para refinar depois, por enquanto deixamos o tempo ditar)
    }

    private void verificarSafetyCar() {
        // Lógica de acidente aleatório
        if (safetyCarNaPista) {
            if (random.nextInt(100) < 20) safetyCarNaPista = false; // 20% chance de sair
        } else {
            double chance = pistaAtual.getChanceSafetyCar(); // ex: 5%
            if (random.nextDouble() * 100 < chance) {
                safetyCarNaPista = true;
                System.out.println("SAFETY CAR DEPLOYED!");
            }
        }
    }

    // Getters para a Tela
    public List<CarroDeCorrida> getGrid() { return grid; }
    public int getVoltaAtual() { return voltaAtual; }
    public Clima getClima() { return climaAtual; }
}