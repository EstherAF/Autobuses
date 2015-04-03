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
     * @param id
     */
    public Stop(int id) {
        this(id, null);
    }

    /**
     * Constructor that receives all attributes
     *
     * @param id
     * @param nameShort
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

        if (!id.equals(stop.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
