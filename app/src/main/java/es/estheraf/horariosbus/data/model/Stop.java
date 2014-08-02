package es.estheraf.horariosbus.data.model;

/**
 * POJO for bus stop
 *
 * @author Esther Álvarez Feijoo
 */
public class Stop {

    /**
     * Unique id for this stop
     */
    public Integer id;

    /**
     * Name for this stop
     */
    public String name;

    /**
     * Default constructor
     */
    public Stop() {
    }

    /**
     * Constructor that receives all attributes
     */
    public Stop(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
