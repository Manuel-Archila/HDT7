import java.util.Iterator;
import java.util.Comparator;


public class BinarySearchTree<E extends Comparable<E>> {

    protected BinaryTree<E> root;
    protected final BinaryTree<E> EMPTY = new BinaryTree<E>();
    protected int count;
    protected Comparator<E> ordering;


    /**
     * Construye un arbol binario sin datos.
     */
    public BinarySearchTree()
    {
        this(new NaturalComparator<E>());
    }

    /**
     * Construye un arbol binario sin datos.
     */
    public BinarySearchTree(Comparator<E> alternateOrder)
    {
        root = EMPTY;
        count = 0;
        ordering = alternateOrder;
    }

    /**
     * Verifica si el arbol esta vacio
     * @return Verdadero si no tiene datos
     */
    public boolean isEmpty()
    {
        return root == EMPTY;
    }

    /**
     * Quita todos los datos de BST
     */
    public void clear()
    {
        root = new BinaryTree<E>();
        count = 0;
    }

    /**
     * Calcula el numero de datos dentro del BST
     * @return El numero de nodos en el BST
     */
    public int size()
    {
        return count;
    }

    /**
     * Regresa el nodo con el valor que deseado
     */
    protected BinaryTree<E> locate(BinaryTree<E> root, E value)
    {
        E rootValue = root.value();
        BinaryTree<E> child;

        // found at root: done
        if (rootValue.equals(value)) return root;
        // look left if less-than, right if greater-than
        if (ordering.compare(rootValue,value) < 0)
        {
            child = root.right();
        } else {
            child = root.left();
        }
        // no child there: not in tree, return this node,
        // else keep searching
        if (child.isEmpty()) {
            return root;
        } else {
            return locate(child, value);
        }
    }

    /**
     * Regresa el valor del arbol que que predecesor
     * @param root El arbol de que se quiere hallar el predecesor
     * @return El arbol que esta antes de ese
     */
    protected BinaryTree<E> predecessor(BinaryTree<E> root)
    {
        BinaryTree<E> result = root.left();
        while (!result.right().isEmpty()) {
            result = result.right();
        }
        return result;
    }

    /**
     * Regresa el valor del arbol que sigue.
     * @param root El arbol de que se quiere hallar el succesor.
     * @return El arbol que esta despues de ese.
     */
    protected BinaryTree<E> successor(BinaryTree<E> root)
    {
        BinaryTree<E> result = root.right();
        while (!result.left().isEmpty()) {
            result = result.left();
        }
        return result;
    }

    /**
     * Agrega un valor al BST
     */
    public void add(E value)
    {
        BinaryTree<E> newNode = new BinaryTree<E>(value,EMPTY,EMPTY);

        // add value to binary search tree
        // if there's no root, create value at root
        if (root.isEmpty())
        {
            root = newNode;
        } else {
            BinaryTree<E> insertLocation = locate(root,value);
            E nodeValue = insertLocation.value();
            // The location returned is the successor or predecessor
            // of the to-be-inserted value
            if (ordering.compare(nodeValue,value) < 0) {
                insertLocation.setRight(newNode);
            } else {
                if (!insertLocation.left().isEmpty()) {
                    // if value is in tree, we insert just before
                    predecessor(insertLocation).setRight(newNode);
                } else {
                    insertLocation.setLeft(newNode);
                }
            }
        }
        count++;
    }

    /**
     * Determina si el BST tiene ese valor
     * @return verdadero si si contiene el valor.
     */
    public boolean contains(E value)
    {
        if (root.isEmpty()) return false;

        BinaryTree<E> possibleLocation = locate(root,value);
        return value.equals(possibleLocation.value());
    }

    /**
     * Regresa la referencia del valor encontrado dentro del arbol.
     * @return Un valor, de no ser asi null
     */
    public E get(E value)
    {
        if (root.isEmpty()) return null;

        BinaryTree<E> possibleLocation = locate(root,value);
        if (value.equals(possibleLocation.value()))
            return possibleLocation.value();
        else
            return null;
    }

    /**
     * Quita la instancia del valor.
     * @return El valor del que se removio.
     */
    public E remove(E value)
    {
        if (isEmpty()) return null;

        if (value.equals(root.value())) // delete root value
        {
            BinaryTree<E> newroot = removeTop(root);
            count--;
            E result = root.value();
            root = newroot;
            return result;
        }
        else
        {
            BinaryTree<E> location = locate(root,value);

            if (value.equals(location.value())) {
                count--;
                BinaryTree<E> parent = location.parent();
                if (parent.right() == location) {
                    parent.setRight(removeTop(location));
                } else {
                    parent.setLeft(removeTop(location));
                }
                return location.value();
            }
        }
        return null;
    }

    /**
     * Quita el nodo de hast arriba del arbolde.
     * @param topNode El nodo con el valor que se quiere quitar.
     * @return La raiz del BST con todos los nodos como descendientes.
     */
    protected BinaryTree<E> removeTop(BinaryTree<E> topNode)
    {
        // remove topmost BinaryTree from a binary search tree
        BinaryTree<E> left  = topNode.left();
        BinaryTree<E> right = topNode.right();
        // disconnect top node
        topNode.setLeft(EMPTY);
        topNode.setRight(EMPTY);
        // Case a, no left BinaryTree
        //   easy: right subtree is new tree
        if (left.isEmpty()) { return right; }
        // Case b, no right BinaryTree
        //   easy: left subtree is new tree
        if (right.isEmpty()) { return left; }
        // Case c, left node has no right subtree
        //   easy: make right subtree of left
        BinaryTree<E> predecessor = left.right();
        if (predecessor.isEmpty())
        {
            left.setRight(right);
            return left;
        }
        // General case, slide down left tree
        //   harder: successor of root becomes new root
        //           parent always points to parent of predecessor
        BinaryTree<E> parent = left;
        while (!predecessor.right().isEmpty())
        {
            parent = predecessor;
            predecessor = predecessor.right();
        }
        // Assert: predecessor is predecessor of root
        parent.setRight(predecessor.left());
        predecessor.setLeft(left);
        predecessor.setRight(right);
        return predecessor;
    }



    /**
     * Regresa el hashcode asociado a ese valor
     * @return Haschode para ese valor
     */
    public int hashCode(){
        return root.hashCode();
    }

    /**
     * Regresa un String con el la informacion del arbol.
     * @return String que representa al BST.
     */
    public String treeString(){
        return root.treeString();
    }

    /**
     * String con el Arbol y tod su contenido.
     *
     * @post Crea una String con el BST
     *
     * @return String que representa el BST.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<BinarySearchTree:");
        if (!root.isEmpty()) {
            s.append(root);
        }
        s.append(">");
        return s.toString();
    }

    public InOrderIterator<E> iterator() {

        return root.iterator();
    }
}
