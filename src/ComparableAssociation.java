
import java.util.Map;

public class ComparableAssociation<K extends Comparable<K>,V> extends Association<K,V> implements Comparable<ComparableAssociation<K,V>>
        , MapEntry<K,V>
{
    /**
     * Constructor de una Asociacion Comparable. El valor de la asociacion es nulo.
     * @param key La llave
     */
    public ComparableAssociation(K key)
    {
        this(key,null);
    }

    /**
     * Crea una nueva asociacion con una llave y un valor .
     * @param key La llave de la asociacion
     * @param value El valor de la asociacion
     */
    public ComparableAssociation(K key, V value)
    {
        super(key,value);
    }

    /**
     * Compara las llaves de las asociaciones.
     * @param that La asociacion que se quiere comparar
     * @return Regresa 1 si es mayor, -1 si es menor y 0 si es igual
     */
    public int compareTo(ComparableAssociation<K,V> that)
    {
        return this.getKey().compareTo(that.getKey());
    }

    /**
     * Regresa una String de la Asociacion
     * @return String que representa la asociacion
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<ComparableAssociation: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
}
