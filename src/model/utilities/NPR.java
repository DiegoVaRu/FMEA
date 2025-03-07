package model.utilities;

public class NPR {
    public static double calcNPR(int gravedad, int ocurrencia, int deteccion) {
        return (gravedad + ocurrencia + deteccion)/3;
    }
}
