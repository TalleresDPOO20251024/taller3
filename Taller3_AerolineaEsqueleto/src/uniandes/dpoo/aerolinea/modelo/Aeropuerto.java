package uniandes.dpoo.aerolinea.modelo;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;

/**
 * Esta clase encapsula la información sobre los aeropuertos e implementa algunas operaciones relacionadas con la ubicación geográfica de los aeropuertos.
 * 
 * No puede haber dos aeropuertos con el mismo código.
 */
public class Aeropuerto
{
    // Atributos
    private String codigo;
    private String nombre;
    private double latitud;
    private double longitud;
    private static Set<String> codigosRegistrados = new HashSet<>();
    private static final double RADIO_TERRESTRE = 6371.0; // Radio de la Tierra en kilómetros

    /**
     * Constructor de la clase Aeropuerto
     * @param codigo Código único del aeropuerto
     * @param nombre Nombre del aeropuerto
     * @param latitud Latitud del aeropuerto
     * @param longitud Longitud del aeropuerto
     * @throws AeropuertoDuplicadoException Si ya existe un aeropuerto con el mismo código
     */
    public Aeropuerto(String codigo, String nombre, double latitud, double longitud) throws AeropuertoDuplicadoException {
        if (codigosRegistrados.contains(codigo)) {
            throw new AeropuertoDuplicadoException("Ya existe un aeropuerto con el código: " + codigo);
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        codigosRegistrados.add(codigo);
    }

    /**
     * Retorna el código del aeropuerto
     * @return Código del aeropuerto
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre del aeropuerto
     * @return Nombre del aeropuerto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la latitud del aeropuerto
     * @return Latitud del aeropuerto
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Retorna la longitud del aeropuerto
     * @return Longitud del aeropuerto
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Calcula la distancia en kilómetros entre dos aeropuertos usando la fórmula de la distancia euclidiana aproximada.
     * @param aeropuerto1 Primer aeropuerto
     * @param aeropuerto2 Segundo aeropuerto
     * @return Distancia en kilómetros entre los dos aeropuertos
     */
    public static int calcularDistancia(Aeropuerto aeropuerto1, Aeropuerto aeropuerto2) {
        // Convertir los ángulos a radianes para facilitar las operaciones trigonométricas
        double latAeropuerto1 = Math.toRadians(aeropuerto1.getLatitud());
        double lonAeropuerto1 = Math.toRadians(aeropuerto1.getLongitud());
        double latAeropuerto2 = Math.toRadians(aeropuerto2.getLatitud());
        double lonAeropuerto2 = Math.toRadians(aeropuerto2.getLongitud());

        // Calcular la distancia debido a la diferencia de latitud y de longitud
        double deltaX = (lonAeropuerto2 - lonAeropuerto1) * Math.cos((latAeropuerto1 + latAeropuerto2) / 2);
        double deltaY = (latAeropuerto2 - latAeropuerto1);

        // Calcular la distancia real en kilómetros
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY) * RADIO_TERRESTRE;

        return (int) Math.round(distancia);
    }
}
