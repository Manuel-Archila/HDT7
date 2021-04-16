import java.util.Iterator;
import java.util.Stack;
import java.util.Iterator;

public class InOrderIterator<E> implements Iterator<E>{
    protected BinaryTree<E> root;
    protected Stack<BinaryTree<E>> todo;

    public InOrderIterator(BinaryTree<E> root){
        todo = new Stack<BinaryTree<E>>();
        this.root = root;
        reset();
    }

    public void reset(){
        todo.clear();
        BinaryTree<E> current = root;
        while(!current.isEmpty()){
            todo.push(current);
            current = current.left;
        }
    }

    public boolean hasNext(){

        return !todo.isEmpty();
    }

    public E get(){
        return todo.peek().value();
    }

    public E next(){
        BinaryTree<E> old = todo.pop();
        E result = old.value();
        if(!old.right().isEmpty()){
            BinaryTree<E> current = old.right;
            do{
                todo.push(current);
                current = current.left;

            } while(!current.isEmpty());
        }
        return result;
    }
}
