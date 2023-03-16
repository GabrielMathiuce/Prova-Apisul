package classes;

public class Elevador {

    private int andar;
    private char elevador;
    private char turno;

    public Elevador() {
    }

    public Elevador(int andar, char elevador, char turno) {
        this.andar = andar;
        this.elevador = elevador;
        this.turno = turno;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public char getElevador() {
        return elevador;
    }

    public void setElevador(char elevador) {
        this.elevador = elevador;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "classes.Elevador{" +
                "andar=" + andar +
                ", elevador=" + elevador +
                ", turno=" + turno +
                '}';
    }
}