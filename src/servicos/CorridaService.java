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
    
    private List<CarroDeCorrida> grid = new ArrayList<>();
    private Random random = new Random();

    // --- SETUP ---

    public void iniciarSessao(Pista pista, List<Piloto> pilotosInscritos) {
        this.pistaAtual = pista;
        this.voltaAtual = 0;
        this.safetyCarNaPista = false;
        
        grid.clear();
        for (Piloto p : pilotosInscritos) {
            CarroDeCorrida carro = new CarroDeCorrida(p);
            // Configuração inicial: Tanque cheio (ex: 110kg para F1)
            // Futuramente isso pode vir de uma regra da Categoria
            carro.adicionarCombustivel(110.0); 
            grid.add(carro); 
        }
        
        gerarClimaInicial();
        //System.out.println("Grid formado com " + grid.size() + " carros para " + pista.getNome());
    }

    private void gerarClimaInicial() {
        if (pistaAtual.isOval()) {
            this.climaAtual = Clima.ENSOLARADO; // Regra de Ouro: Ovais não correm na chuva
        } else {
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
            // 1. Calcular Tempo da Volta (FÍSICA AVANÇADA)
            double tempo = calcularTempoFisica(carro);
            
            // 2. Adicionar ao total
            carro.adicionarTempo(tempo);
            
            // 3. Aplicar Desgastes
            if (!safetyCarNaPista) {
                carro.desgastarPneu(pistaAtual.getFatorDesgastePneu());
                carro.consumirCombustivel(pistaAtual.getFatorConsumoCombustivel());
            }
        }

        // 4. Reordenar o Grid (Menor tempo total = Líder)
        grid.sort(Comparator.comparingDouble(CarroDeCorrida::getTempoParaOrdenacao));
    }

    // --- FÍSICA (A MÁGICA ACONTECE AQUI) ---

    private double calcularTempoFisica(CarroDeCorrida carro) {
        if (safetyCarNaPista) return pistaAtual.getTempoBase() * 1.4; // 40% mais lento sob SC

        Equipe equipe = carro.getEquipe();
        Piloto piloto = carro.getPiloto();
        Motor motor = equipe.getMotorObjeto(); // Pega o motor real (com atributos)

        // 1. PERFORMANCE DO CARRO (0 a 100)
        double forcaMotor = 50.0; // Valor padrão se não tiver motor
        double dirigibilidadeMotor = 50.0;
        
        if (motor != null) {
            // Converte escala 1-5 para 20-100
            forcaMotor = motor.getPotencia() * 20.0;
            dirigibilidadeMotor = motor.getDirigibilidade() * 20.0;
        }
        
        // Aero e Chassi (Escala 1-5 -> 20-100)
        double forcaAero = equipe.getNivelAero() * 20.0;
        double forcaChassi = equipe.getNivelChassi() * 20.0;

        // Ponderação pela Pista
        double relMotor = pistaAtual.getRelevanciaMotor();       // ex: 0.9 em Monza
        double relAero = pistaAtual.getRelevanciaAerodinamica(); // ex: 0.1 em Monza
        double relMecanica = 1.0 - relMotor - relAero;           // O que sobra é aderência mecânica/chassi

        double desempenhoCarro = (forcaMotor * relMotor) + 
                                 (forcaAero * relAero) + 
                                 (forcaChassi * relMecanica * 0.5) + 
                                 (dirigibilidadeMotor * relMecanica * 0.5);

        // 2. PERFORMANCE DO PILOTO (0 a 100)
        double habilidadePiloto = piloto.getRitmo(); // Base
        
        // Bônus de Terreno
        if (climaAtual == Clima.CHUVA_FORTE || climaAtual == Clima.CHUVA_LEVE) {
            // Na chuva, habilidade conta MUITO (média entre ritmo e chuva)
            habilidadePiloto = (habilidadePiloto + piloto.getHabilidadeChuva()) / 2.0;
        } else {
            // No seco, verifica tipo de pista
            switch (pistaAtual.getTipo()) {
                case RUA_TEMPORARIO:
                    habilidadePiloto = (habilidadePiloto * 0.7) + (piloto.getHabilidadeRua() * 0.3);
                    break;
                case OVAL_CURTO:
                case OVAL_SPEEDWAY:
                case SUPERSPEEDWAY:
                    habilidadePiloto = (habilidadePiloto * 0.6) + (piloto.getHabilidadeOval() * 0.4);
                    break;
                default: // Misto
                    habilidadePiloto = (habilidadePiloto * 0.8) + (piloto.getHabilidadeMisto() * 0.2);
            }
        }
        
        // Experiência ajuda a manter a constância (reduz erros)
        double fatorExperiencia = piloto.getExperiencia() / 200.0; // Pequeno bônus (0 a 0.5s)

        // 3. FÓRMULA FINAL DO TEMPO
        // Performance Total (60% Carro, 40% Piloto - F1 é muito carro dependente)
        double performanceTotal = (desempenhoCarro * 0.65) + (habilidadePiloto * 0.35);
        
        // O tempo base é para uma performance "Média" (~75).
        // Se a performance for 90, ganha tempo. Se for 50, perde tempo.
        double deltaPerformance = (75.0 - performanceTotal) * 0.15; // 0.15s por ponto de diferença
        
        // Fatores Dinâmicos
        double penalidadePneu = (100.0 - carro.getDesgastePneus()) * 0.05; // Pneu gasto = +tempo
        double penalidadeCombustivel = carro.getCombustivel() * 0.03;      // Tanque cheio = +tempo
        
        double tempoFinal = pistaAtual.getTempoBase() 
                            + deltaPerformance 
                            + penalidadePneu 
                            + penalidadeCombustivel 
                            - fatorExperiencia;

        // Variação Aleatória (Erro humano/Vento) - +/- 0.3s
        tempoFinal += (random.nextDouble() - 0.5) * 0.6;

        return tempoFinal;
    }

    private void verificarSafetyCar() {
        if (safetyCarNaPista) {
            if (random.nextInt(100) < 20) safetyCarNaPista = false; // 20% chance de sair
        } else {
            // Chance baseada na pista + Clima ruim aumenta chance
            double chance = pistaAtual.getChanceSafetyCar(); 
            if (climaAtual == Clima.CHUVA_LEVE) chance *= 1.5;
            if (climaAtual == Clima.CHUVA_FORTE) chance *= 2.0;
            
            // Rolagem de dado (ex: chance 5% -> precisa tirar < 5 em 10000 para ser raro por volta)
            if (random.nextDouble() * 100 < (chance / 10.0)) { 
                safetyCarNaPista = true;
                System.out.println("SAFETY CAR ACIONADO!");
            }
        }
    }

    public List<CarroDeCorrida> getGrid() { return grid; }
    public int getVoltaAtual() { return voltaAtual; }
    public Clima getClima() { return climaAtual; }
}