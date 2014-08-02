package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO for bus stop
 *
 * @author Esther Álvarez Feijoo
 */
public class Stop {

    /**
     * Unique id for this stop
     */
    @JsonProperty(value = "id", required = true)
    public Integer id;

    /**
     * Name for this stop
     */
    @JsonProperty(value = "name",required = true)
    public String name;

    /**
     * Default constructor
     */
    public Stop() {
    }

    /**
     * Constructor
     *
     * @param id
     */
    public Stop(int id) {
        this(id, null);
    }

    /**
     * Constructor that receives all attributes
     *
     * @param id
     * @param name
     */
    public Stop(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
