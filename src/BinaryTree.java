import java.lang.Math;
import java.util.Iterator;


public class BinaryTree<E>
{
    protected E val; // value associated with node
    /**
     * The parent of this node
     */
    protected BinaryTree<E> parent; // parent of node

    protected BinaryTree<E> left, right; // children of node

    /**
     * Constructor de un arbol
     */
    public BinaryTree()
    {
        val = null;
        parent = null; left = right = this;
    }

    /**
     * Constructor de un arbol sin hijos.
     */
    public BinaryTree(E value)
    {
        val = value;
        right = left = new BinaryTree<E>();
        setLeft(left);
        setRight(right);
    }

    /**
     * Constructor de un arbol con dos arboles hijos
     * @param value El valor que que va referenciar el nodo
     * @param left Un subarbol que toma el valor del hijo a la izquierda
     * @param right Un subarbol que toma el valor del hijo a la derecha
     */
    public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right)
    {
        //Assert.pre(value != null, "Tree values must be non-null.");
        val = value;
        if (left == null) { left = new BinaryTree<E>(); }
        setLeft(left);
        if (right == null) { right = new BinaryTree<E>(); }
        setRight(right);
    }

    /**
     * Obtiene el subarbol izquierdo de ese nodo
     * @return Regresa el subarbol que se encuentra a la izquierda
     */
    public BinaryTree<E> left()
    {
        return left;
    }

    /**
     * Obtiene el subarbol izquierdo de ese nodo
     * @return Regresa el subarbol que se encuentra a la izquierda
     */
    public BinaryTree<E> right()
    {
        return right;
    }

    /**
     * Obtiene la referencia del padre de este nodo
     * @return La referencia al padre de este nodo
     */
    public BinaryTree<E> parent()
    {
        return parent;
    }

    /**
     * Inserta la referencia de un arbol al subarbol izquierdo
     * @param newLeft El arbol que se va a insertar
     */
    public void setLeft(BinaryTree<E> newLeft)
    {
        if (isEmpty()) return;
        if (left != null && left.parent() == this) left.setParent(null);
        left = newLeft;
        left.setParent(this);
    }

    /**
     * Inserta la referencia de un arbol al subarbol derecho
     * @param newRight El arbol que se va a insertar
     */
    public void setRight(BinaryTree<E> newRight)
    {
        if (isEmpty()) return;
        if (right != null && right.parent() == this) right.setParent(null);
        right = newRight;
        right.setParent(this);
    }

    /**
     * Inserta un nuevo arbol para que sea el nuevo padre.
     * @param newParent El padre que se le asignara a un nodo
     */
    protected void setParent(BinaryTree<E> newParent)
    {
        if (!isEmpty()) {
            parent = newParent;
        }
    }

    /**
     * Obtiene el tamano del arbol
     * @return El tamano del arbol
     */
    public int size()
    {
        if (isEmpty()) return 0;
        return left().size() + right().size() + 1;
    }

    /**
     * Obtiene la referencia de la raiz de un arbol
     * @return La raiz del arbol
     */
    public BinaryTree<E> root()
    {
        if (parent() == null) return this;
        else return parent().root();
    }



    /**
     * Regresa verdadero si el arbol esta vacio
     * @return Verdadero si el arbol esta vacio
     */
    public boolean isEmpty()
    {
        return val == null;
    }




    /**
     * Determina si este nodo es el hijo izquierdo
     * @return Verdadero si este nodo es el hijo izquierdo
     */
    public boolean isLeftChild()
    {
        if (parent() == null) return false;
        return this == parent().left();
    }

    /**
     * Determina si este nodo es el hijo derecho
     * @return Verdadero si este nodo es el hijo derecho
     */
    public boolean isRightChild()
    {
        if (parent() == null) return false;
        return this == parent().right();
    }

    /**
     * Regresa el valor asociado con este nodo
     * @return El valor del nodo
     */
    public E value()
    {
        return val;
    }

    /**
     * Ingresa el nuevo valor
     * @param value El nuevo valor del nodo
     */
    public void setValue(E value)
    {
        val = value;
    }

    /**
     * @post regresa la suma todos los hashcodes
     */
    public int hashCode()
    {
        if (isEmpty()) return 0;
        int result = left().hashCode() + right().hashCode();
        if (value() != null) result += value().hashCode();
        return result;
    }

    /**
     * Obtiene la profundidad de un nodo
     * @return El tamano del camino para llegar a ese nodo
     */
    public int depth()
    {
        if (parent() == null) return 0;
        return 1 + parent.depth();
    }

    public Iterator<E> iterator(){
        return new InOrderIterator<>(this);
    }

    /**
     * Regresa una String que representa el arbol linkeado en ese nodo
     * @return String que representa el arbol linkeado en ese nodo
     */
    public String treeString(){
        String s = "";
        for (int i=0; i < this.depth(); i++){
            s += "\t|";
        }

        s += ("<" + val + " : " + getHand() + ">\n");

        if (!left.isEmpty()) s += left.treeString();
        if (!right.isEmpty()) s += right.treeString();

        return s;
    }

    /**
     * Metodo implmentado en el metodo treeString.
     */
    private String getHand(){
        if (isRightChild()) return "R";
        if (isLeftChild()) return "L";
        return "Root";
    }


    /**
     * Regresa una representacion del arbol en ese nodo.
     * @return String que representa el subarbol.
     */
    public String toString()
    {
        if (isEmpty()) return "<BinaryTree: empty>";
        StringBuffer s = new StringBuffer();
        s.append("<BinaryTree "+value());
        if (!left().isEmpty()) s.append(" "+left());
        else s.append(" -");
        if (!right().isEmpty()) s.append(" "+right());
        else s.append(" -");
        s.append('>');
        return s.toString();
    }
}