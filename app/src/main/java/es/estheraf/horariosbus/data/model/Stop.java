package es.estheraf.horariosbus.data.model;

import java.io.Serializable;

/**
 * POJO for bus stop
 *
 * @author Esther Álvarez Feijoo
 */
public class Stop implements Serializable {

    /**
     * Unique id for this stop
     */
    public Integer id;

    /**
     * Short name
     */
    public String nameShort;

    /**
     * Long name
     */
    public String nameLong;


    public String coords;

    public Integer parentId;

    public Stop parent;

    public String desc;

    /**
     * Default constructor
     */
    public Stop() {
    }

    /**
     * Constructor
     *
     * @param id internal PK
     */
    public Stop(int id) {
        this(id, null);
    }

    /**
     * Constructor that receives all attributes
     *
     * @param id internal PK
     * @param nameShort name in a short way
     */
    public Stop(int id, String nameShort) {
        this.id = id;
        this.nameShort = nameShort;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        return id.equals(stop.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
