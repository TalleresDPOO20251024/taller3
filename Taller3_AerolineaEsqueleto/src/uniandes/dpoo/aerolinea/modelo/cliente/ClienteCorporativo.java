package uniandes.dpoo.aerolinea.modelo.cliente;

import org.json.JSONObject;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son empresas
 */
public class ClienteCorporativo extends Cliente {
    
    private String nombreEmpresa;
    private int tamanoEmpresa;
    
    /**
     * Constructor de ClienteCorporativo
     * @param nombreEmpresa Nombre de la empresa
     * @param tamanoEmpresa Tamaño de la empresa en número de empleados
     */
    public ClienteCorporativo(String nombreEmpresa, int tamanoEmpresa) {
        super(nombreEmpresa); // Se asume que el identificador del cliente es el nombre de la empresa
        this.nombreEmpresa = nombreEmpresa;
        this.tamanoEmpresa = tamanoEmpresa;
    }
    
    /**
     * Retorna el nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    
    /**
     * Retorna el tamaño de la empresa en número de empleados
     */
    public int getTamanoEmpresa() {
        return tamanoEmpresa;
    }
    
    /**
     * Crea un nuevo objeto de tipo ClienteCorporativo a partir de un objeto JSON.
     * @param cliente El objeto JSON que contiene la información
     * @return El nuevo objeto inicializado con la información
     */
    public static ClienteCorporativo cargarDesdeJSON(JSONObject cliente) {
        String nombreEmpresa = cliente.getString("nombreEmpresa");
        int tam = cliente.getInt("tamanoEmpresa");
        return new ClienteCorporativo(nombreEmpresa, tam);
    }

    /**
     * Salva este objeto de tipo ClienteCorporativo dentro de un objeto JSONObject
     * @return El objeto JSON con toda la información del cliente corporativo
     */
    public JSONObject salvarEnJSON() {
        JSONObject jobject = new JSONObject();
        jobject.put("nombreEmpresa", this.nombreEmpresa);
        jobject.put("tamanoEmpresa", this.tamanoEmpresa);
        jobject.put("tipo", "CORPORATIVO");
        return jobject;
    }
}

