package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    private String codigoRuta;
    private Aeropuerto aeropuertoOrigen;
    private Aeropuerto aeropuertoDestino;
    private int duracionMinutos;
    private double tarifaBaja;
    private double tarifaAlta;

    public Ruta(String codigoRuta, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, int duracionMinutos, double tarifaBaja, double tarifaAlta) {
        this.codigoRuta = codigoRuta;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.duracionMinutos = duracionMinutos;
        this.tarifaBaja = tarifaBaja;
        this.tarifaAlta = tarifaAlta;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public double getTarifaBaja() {
        return tarifaBaja;
    }

    public double getTarifaAlta() {
        return tarifaAlta;
    }

    public static int getMinutos(String horaCompleta) {
        int minutos = Integer.parseInt(horaCompleta) % 100;
        return minutos;
    }

    public static int getHoras(String horaCompleta) {
        int horas = Integer.parseInt(horaCompleta) / 100;
        return horas;
    }
}
