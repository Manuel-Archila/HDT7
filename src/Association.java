import java.util.Map;



public class Association<K,V> implements MapEntry<K,V>
{
    protected K theKey; // the key of the key-value pair
    protected V theValue; // the value of the key-value pair


    /**
     * Constructor. Construye un par con la key y el value
     * @param key Un objeto no nulo
     * @param value El valor asociado con la key.
     */
    public Association(K key, V value)
    {

        theKey = key;
        theValue = value;
    }

    /**
     * Construye una pareja con valor nulo

     * @param key No puede ser nula.
     */
    public Association(K key)
    {
        this(key,null);
    }

    /**
     * Funcion que compara por medio de las llaves
     * @param other Otro objeto al cual comparar
     * @return Regresa verdadero si son iguales
     */
    public boolean equals(Object other)
    {
        Association otherAssoc = (Association)other;
        return getKey().equals(otherAssoc.getKey());
    }

    /**
     * Funcion hashcode estandar
     * @return Regresa un hashcode para la asociacion
     */
    public int hashCode()
    {
        return getKey().hashCode();
    }

    /**
     * Obtiene el valor de la asociacion
     * @return El valor de la asociacion
     */
    public V getValue()
    {
        return theValue;
    }

    /**
     * Obtiene la llave de la asociacion
     * @return La llave de la asociacion
     */
    public K getKey()
    {
        return theKey;
    }

    /**
     * Inserta el nuevo valor del la llave
     * @param value El nuevo valor.
     */
    public V setValue(V value)
    {
        V oldValue = theValue;
        theValue = value;
        return oldValue;
    }

    /**
     * Representacion de la asocicacion en la String.
     * @return String representado la llave y el valor.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Association: "+getKey()+"="+getValue()+">");
        return s.toString();
    }

}
