package uniandes.dpoo.aerolinea.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaAerolinea;
import uniandes.dpoo.aerolinea.persistencia.IPersistenciaTiquetes;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Aerolinea {
    private List<Avion> aviones;
    private Map<String, Ruta> rutas;
    private List<Vuelo> vuelos;
    private Map<String, Cliente> clientes;

    public Aerolinea() {
        aviones = new LinkedList<>();
        rutas = new HashMap<>();
        vuelos = new LinkedList<>();
        clientes = new HashMap<>();
    }

    public void agregarRuta(Ruta ruta) {
        rutas.put(ruta.getCodigoRuta(), ruta);
    }

    public void agregarAvion(Avion avion) {
        aviones.add(avion);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getIdentificador(), cliente);
    }

    public boolean existeCliente(String identificadorCliente) {
        return clientes.containsKey(identificadorCliente);
    }

    public Cliente getCliente(String identificadorCliente) {
        return clientes.get(identificadorCliente);
    }

    public Collection<Avion> getAviones() {
        return aviones;
    }

    public Collection<Ruta> getRutas() {
        return rutas.values();
    }

    public Ruta getRuta(String codigoRuta) {
        return rutas.get(codigoRuta);
    }

    public Collection<Vuelo> getVuelos() {
        return vuelos;
    }

    public Vuelo getVuelo(String codigoRuta, String fechaVuelo) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getRuta().getCodigoRuta().equals(codigoRuta) && vuelo.getFecha().equals(fechaVuelo)) {
                return vuelo;
            }
        }
        return null;
    }

    public Collection<Tiquete> getTiquetes() {
        List<Tiquete> tiquetes = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            tiquetes.addAll(vuelo.getTiquetes());
        }
        return tiquetes;
    }

    public void cargarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException, InformacionInconsistenteException {
        IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        cargador.cargarAerolinea(archivo, this);
    }

    public void salvarAerolinea(String archivo, String tipoArchivo) throws TipoInvalidoException, IOException {
        IPersistenciaAerolinea cargador = CentralPersistencia.getPersistenciaAerolinea(tipoArchivo);
        cargador.salvarAerolinea(archivo, this);
    }

    public void programarVuelo(String fecha, String codigoRuta, String nombreAvion) throws Exception {
        Ruta ruta = getRuta(codigoRuta);
        Avion avion = null;
        for (Avion a : aviones) {
            if (a.getNombre().equals(nombreAvion)) {
                avion = a;
                break;
            }
        }
        if (ruta == null || avion == null) {
            throw new Exception("Ruta o avión no encontrados");
        }
        vuelos.add(new Vuelo(fecha, ruta, avion));
    }

    public int venderTiquetes(String identificadorCliente, String fecha, String codigoRuta, int cantidad) throws VueloSobrevendidoException, Exception {
        Cliente cliente = getCliente(identificadorCliente);
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (cliente == null || vuelo == null) {
            throw new Exception("Cliente o vuelo no encontrado");
        }
        return vuelo.venderTiquetes(cliente, cantidad);
    }

    public void registrarVueloRealizado(String fecha, String codigoRuta) {
        Vuelo vuelo = getVuelo(codigoRuta, fecha);
        if (vuelo != null) {
            vuelo.marcarComoRealizado();
        }
    }

    public String consultarSaldoPendienteCliente(String identificadorCliente) {
        Cliente cliente = getCliente(identificadorCliente);
        if (cliente == null) {
            return "Cliente no encontrado";
        }
        int saldo = 0;
        for (Tiquete tiquete : getTiquetes()) {
            if (tiquete.getCliente().equals(cliente) && !tiquete.usado()) {
                saldo += tiquete.getPrecio();
            }
        }
        return "Saldo pendiente: " + saldo;
    }
}
