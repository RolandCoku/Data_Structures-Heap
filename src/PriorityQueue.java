import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<T> extends AbstractCollection<T> {
    private int currentSize;
    private T[] array;
    private Comparator<T> comparator;
    private static final int DEFAULT_CAPACITY = 20;

    public PriorityQueue(){
        currentSize = 0;
        comparator = null;
        array = (T[])new Object[DEFAULT_CAPACITY];
    }
    public PriorityQueue(Comparator<T> comparator){
        currentSize = 0;
        this.comparator = comparator;
        array = (T[])new Object[DEFAULT_CAPACITY];
    }
    public PriorityQueue(Collection<T> collection){
        this.comparator = null;
        currentSize = collection.size();
        array = (T[])new Object[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (T item : collection){
            array[i++] = item;
        }
        buildHeap();
    }

    public int size(){
        return currentSize;
    }
    public void clear(){
        currentSize = 0;
    }
    public T getMin(){ //Return the smallest item in the priority queue
        if(currentSize == 0){
            return null;
        }
        return array[1];
    }

    public boolean add(T x){
        if(currentSize + 1 == array.length){
            doubleArray();
        }
        int hole = ++currentSize;
        array[0] = x;
        if(comparator == null){
            while (compare(x, array[hole / 2]) < 0){//If x is smaller than the parent of the hole
                array[hole] = array[hole / 2];//Move the parent to the hole
                hole /= 2;
            }
        }else{
            while (comparator.compare(x, array[hole / 2]) < 0){//If x is smaller than the parent of the hole
                array[hole] = array[hole / 2];//Move the parent to the hole
                hole /= 2;
            }
        }
        array[hole] = x;
        return true;
    }

    public T remove(){
        if(currentSize == 0){
            return null;
        }
        T minItem = array[1];
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    private void percolateDown(int hole){
        int child;
        T temp = array[hole];

        if(comparator == null){

            while (hole * 2 <= currentSize){//While the hole has at least one child
                child = hole * 2;//Left child
                if(child != currentSize && compare(array[child + 1], array[child]) < 0){//If the right child is smaller than the left child
                    child++;//Go to the right child
                }
                if(compare(array[child], temp) < 0){//If the child is smaller than the item
                    array[hole] = array[child];//Move the child to the hole
                }else{
                    break;//If the child is bigger than the item, break
                }
                hole = child;
            }

        }else{

            while (hole * 2 <= currentSize){
                child = hole * 2;
                if(child != currentSize && comparator.compare(array[child + 1], array[child]) < 0){
                    child++;
                }
                if(comparator.compare(array[child], temp) < 0){
                    array[hole] = array[child];
                }else{
                    break;
                }
                hole = child;
            }
        }
        array[hole] = temp;//Move the item to the hole
    }

    private void buildHeap(){
        for(int i = currentSize / 2; i > 0; i--){
            percolateDown(i);
        }
    }
    private void doubleArray(){
        T[] newArray;
        newArray = (T[])new Object[array.length * 2];
        for(int i = 0; i < array.length; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private int compare(T lhs, T rhs){
        if(comparator == null){
            return ((Comparable)lhs).compareTo(rhs);
        }else{
            return comparator.compare(lhs, rhs);
        }
    }

    public void print(){
        for(int i = 1; i <= currentSize; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public void lowerPriority(int index, int amount) {
        if (index < 1 || index > currentSize){
            System.out.println("Index out of bounds");
            return;
        }
        array[index] = array[index - amount];
        percolateDown(index);
    }
    public void higherPriority(int index, int amount) {
        if (index < 1 || index > currentSize){
            System.out.println("Index out of bounds");
            return;
        }
        array[index] = array[index + amount];
        percolateDown(index);
    }
    public void remove(int index) {
        if (index < 1 || index > currentSize){
            System.out.println("Index out of bounds");
            return;
        }
        array[index] = array[currentSize--];
        percolateDown(index);
    }

    public Iterator<T> iterator(){
        return null;
    }
}
