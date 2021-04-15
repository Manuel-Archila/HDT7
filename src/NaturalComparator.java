import java.util.Comparator;


public class NaturalComparator<E extends Comparable<E>> implements Comparator<E>
{
    /**
     *
     * @param a el objeto que se va a comparar
     * @param b el objeto con el cual se va a comparar
     * @return 1 si es mayor, -1 si es menor y 0 si es igual
     */
    public int compare(E a, E b)
    {
        return a.compareTo(b);
    }

    /**
     *
     * @param b objeto con el que se compara si es igual
     * @return Verdadero si son iguales.
     */
    public boolean equals(Object b) {
        return (b != null) && (b instanceof NaturalComparator);
    }
}
