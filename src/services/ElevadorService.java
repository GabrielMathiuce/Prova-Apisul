package services;

import classes.Elevador;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.IElevadorService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ElevadorService implements IElevadorService {

    private Elevador[] leitorDeInput() {
        File file = new File("input.json");
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String bfLine = "";
            String line = "";
            while (bfLine != null) {
                line += bfLine = bufferedReader.readLine();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(line, Elevador[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    private List<Integer> ordenaERetornaListaDeAndares() {
        List<Integer> andaresList = new ArrayList<>();

        for (Elevador elevador : leitorDeInput()) {
            andaresList.add(elevador.getAndar());
        }

        andaresList.sort(Comparator.naturalOrder());
        return andaresList;
    }

    private List<Character> ordenaERetornaListaDeElevadores() {
        List<Character> elevadoresList = new ArrayList<>();

        for (Elevador elevador : leitorDeInput()) {
            elevadoresList.add(elevador.getElevador());
        }

        elevadoresList.sort(Comparator.naturalOrder());
        return elevadoresList;
    }

    private List<Character> ordenaERetornaListaDeTurnos() {
        List<Character> turnosList = new ArrayList<>();

        for (Elevador elevador : leitorDeInput()) {
            turnosList.add(elevador.getTurno());
        }

        turnosList.sort(Comparator.naturalOrder());
        return turnosList;
    }
    @Override
    public List<Integer> andarMenosUtilizado() {

        Map<Integer, Integer> andarRepeticoesMap = new HashMap<>();
        List<Integer> andaresMenosUtilizados = new ArrayList<>();

        for(Integer i : ordenaERetornaListaDeAndares()) {
            andarRepeticoesMap.put(i, andarRepeticoesMap.getOrDefault(i, 0) + 1);
        }

        int minimo = Integer.MAX_VALUE;

        for(Map.Entry<Integer, Integer> map : andarRepeticoesMap.entrySet()) {
            int andarAtual = map.getKey();
            int repeticoes = map.getValue();

            if (repeticoes < minimo) {
                minimo = repeticoes;
                andaresMenosUtilizados.clear();
                andaresMenosUtilizados.add(andarAtual);
            }
            else if(repeticoes == minimo) {
                andaresMenosUtilizados.add(andarAtual);
            }
        }
        return andaresMenosUtilizados;
    }
    @Override
    public List<Character> elevadorMaisFrequentado() {
        Map<Character, Integer> elevadorRepeticoesMap = new HashMap<>();
        List<Character> elevadoresMaisFrequentados = new ArrayList<>();

        for(Character c : ordenaERetornaListaDeElevadores()) {
            elevadorRepeticoesMap.put(c, elevadorRepeticoesMap.getOrDefault(c, 0) + 1);
        }

        int maximo = Integer.MIN_VALUE;

        for(Map.Entry<Character, Integer> map : elevadorRepeticoesMap.entrySet()) {
            char elevadorAtual = map.getKey();
            int repeticoes = map.getValue();

            if (repeticoes > maximo) {
                maximo = repeticoes;
                elevadoresMaisFrequentados.clear();
                elevadoresMaisFrequentados.add(elevadorAtual);
            }
            else if(repeticoes == maximo) {
                elevadoresMaisFrequentados.add(elevadorAtual);
            }
        }
        return elevadoresMaisFrequentados;
    }

    @Override
    public List<Character> periodoMaiorFluxoElevadorMaisFrequentado() {
        List<Character> elevadorMaisFrequentadoList = elevadorMaisFrequentado();
        List<Character> maiorFluxoElevadorMaisFrequentadoList = new ArrayList<>();
        Map<Character, Integer> turnoRepeticoesMap = new HashMap<>();

        for (Elevador elevadorAtual : leitorDeInput()) {
            if(elevadorMaisFrequentadoList.contains(elevadorAtual.getElevador())) {
                turnoRepeticoesMap.put(elevadorAtual.getTurno(), turnoRepeticoesMap.getOrDefault(elevadorAtual.getTurno(), 0) + 1);
            }
        }

        int maximo = Integer.MIN_VALUE;

        for(Map.Entry<Character, Integer> map : turnoRepeticoesMap.entrySet()) {
            char turnoAtual = map.getKey();
            int repeticoes = map.getValue();

            if (repeticoes > maximo) {
                maximo = repeticoes;
                maiorFluxoElevadorMaisFrequentadoList.clear();
                maiorFluxoElevadorMaisFrequentadoList.add(turnoAtual);
            }
            else if(repeticoes == maximo) {
                maiorFluxoElevadorMaisFrequentadoList.add(turnoAtual);
            }
        }
        return maiorFluxoElevadorMaisFrequentadoList;
    }

    @Override
    public List<Character> elevadorMenosFrequentado() {
        Map<Character, Integer> elevadorRepeticoesMap = new HashMap<>();
        List<Character> elevadoresMenosFrequentados = new ArrayList<>();

        for(Character c : ordenaERetornaListaDeElevadores()) {
            elevadorRepeticoesMap.put(c, elevadorRepeticoesMap.getOrDefault(c, 0) + 1);
        }

        int minimo = Integer.MAX_VALUE;

        for(Map.Entry<Character, Integer> map : elevadorRepeticoesMap.entrySet()) {
            char elevadorAtual = map.getKey();
            int repeticoes = map.getValue();

            if (repeticoes < minimo) {
                minimo = repeticoes;
                elevadoresMenosFrequentados.clear();
                elevadoresMenosFrequentados.add(elevadorAtual);
            }
            else if(repeticoes == minimo) {
                elevadoresMenosFrequentados.add(elevadorAtual);
            }
        }
        return elevadoresMenosFrequentados;
    }

    @Override
    public List<Character> periodoMenorFluxoElevadorMenosFrequentado() {
            List<Character> elevadorMenosFrequentadoList = elevadorMenosFrequentado();
            List<Character> menorFluxoElevadorMenosFrequentadoList = new ArrayList<>();
            Map<Elevador, Integer> turnoRepeticoesMap = new HashMap<>();

            for (Elevador elevadorAtual : leitorDeInput()) {
                if(elevadorMenosFrequentadoList.contains(elevadorAtual.getElevador())) {
                    turnoRepeticoesMap.put(elevadorAtual, turnoRepeticoesMap.getOrDefault(elevadorAtual, 0) + 1);
                }
            }

            int minimo = Integer.MAX_VALUE;

            for(Map.Entry<Elevador, Integer> map : turnoRepeticoesMap.entrySet()) {
                Elevador elevadorAtual = map.getKey();
                int repeticoes = map.getValue();

                if (repeticoes < minimo) {
                    minimo = repeticoes;
                    menorFluxoElevadorMenosFrequentadoList.clear();
                    menorFluxoElevadorMenosFrequentadoList.add(elevadorAtual.getTurno());
                }
                else if(repeticoes == minimo) {
                    menorFluxoElevadorMenosFrequentadoList.add(elevadorAtual.getTurno());
                }
            }

            return menorFluxoElevadorMenosFrequentadoList;
    }

    @Override
    public List<Character> periodoMaiorUtilizacaoConjuntoElevadores() { Map<Character, Integer> turnoRepeticoesMap = new HashMap<>();
        List<Character> turnosList = new ArrayList<>();
        for(Character c : ordenaERetornaListaDeTurnos()) {
            turnoRepeticoesMap.put(c, turnoRepeticoesMap.getOrDefault(c, 0) + 1);
        }

        int maximo = Integer.MIN_VALUE;

        for(Map.Entry<Character, Integer> map : turnoRepeticoesMap.entrySet()) {
            char turnoAtual = map.getKey();
            int repeticoes = map.getValue();

            if (repeticoes > maximo) {
                maximo = repeticoes;
                turnosList.clear();
                turnosList.add(turnoAtual);
            }
            else if (repeticoes == maximo) {
                turnosList.add(turnoAtual);
            }
        }
        return turnosList;
    }

    @Override
    public float percentualDeUsoElevadorA() {
        int totalDeUsos = leitorDeInput().length;
        int totalDeUsosElevadorA = (int) Arrays.stream(leitorDeInput()).map(it -> it.getElevador() == 'A' ? it : null).filter(Objects::nonNull).count();
        return totalDeUsosElevadorA * 100f / totalDeUsos;
    }

    @Override
    public float percentualDeUsoElevadorB() {
        int totalDeUsos = leitorDeInput().length;
        int totalDeUsosElevadorA = (int) Arrays.stream(leitorDeInput()).map(it -> it.getElevador() == 'B' ? it : null).filter(Objects::nonNull).count();
        return totalDeUsosElevadorA * 100f / totalDeUsos;
    }

    @Override
    public float percentualDeUsoElevadorC() {
        int totalDeUsos = leitorDeInput().length;
        int totalDeUsosElevadorA = (int) Arrays.stream(leitorDeInput()).map(it -> it.getElevador() == 'C' ? it : null).filter(Objects::nonNull).count();
        return totalDeUsosElevadorA * 100f / totalDeUsos;
    }

    @Override
    public float percentualDeUsoElevadorD() {
        int totalDeUsos = leitorDeInput().length;
        int totalDeUsosElevadorA = (int) Arrays.stream(leitorDeInput()).map(it -> it.getElevador() == 'D' ? it : null).filter(Objects::nonNull).count();
        return totalDeUsosElevadorA * 100f / totalDeUsos;
    }

    @Override
    public float percentualDeUsoElevadorE() {
        int totalDeUsos = leitorDeInput().length;
        int totalDeUsosElevadorA = (int) Arrays.stream(leitorDeInput()).map(it -> it.getElevador() == 'E' ? it : null).filter(Objects::nonNull).count();
        return totalDeUsosElevadorA * 100f / totalDeUsos;
    }
}
