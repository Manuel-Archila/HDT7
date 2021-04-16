import com.sun.jdi.InterfaceType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void locate(){
        BinaryTree<String> right = new BinaryTree<String>("Derecha");
        BinaryTree<String> left = new BinaryTree<>("Izquierda");
        BinaryTree<String> parent = new BinaryTree<>("Padre", left, right);
        BinarySearchTree<String> busqueda = new BinarySearchTree<>();
        BinaryTree<String> lado = busqueda.locate(parent, "Izquierda");
        Assert.assertEquals("Izquierda", lado.value());
    }

    @Test
    public void add() {
        BinarySearchTree<Integer> prueba = new BinarySearchTree<>();
        prueba.add(5);
        Assert.assertEquals(true, prueba.contains(5));
    }
}