package heap;
import java.util.Random;
import heap.BinomialHeap.HeapNode;

public class Tester1 {

	public static void main(String [] args){
		/**
		BinomialHeap heap= new BinomialHeap(d, 3*size);
		BinomialHeap_Item [] items= new BinomialHeap_Item[size];
		
		for (int j = 0; j < size; j++) {
			items[j] = new BinomialHeap_Item(""+numbers[j], numbers[j]);
		}
		
		heap.arrayToHeap(items);
		heap.print();
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();
			int num = rand.nextInt(10);
			BinomialHeap_Item item = new BinomialHeap_Item(""+num,num);
			System.out.println(" Insert " +num);
			heap.Insert(item);
			if(!heap.isHeap()){
				System.out.println("nooo! insert "+ heap.size);
			}
			heap.print();
		}
*/
	    
		//heap.print();
		//System.out.println(heap.isHeap());

//		test(10000, 3);// restore this


        //I checked statistically - and also found some mistakes...
		// now we just have to check the things the asked us to check
		// and also have to 
	}

/*
	public static void test(int m, int d)
	{
		int [] numbers= new int[m];
	    Random rand = new Random();
	    BinomialHeap heap = new BinomialHeap(d,m);
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rand.nextInt(1001);
		}
		BinomialHeap.HeapNode[] items= new BinomialHeap.HeapNode[numbers.length];
		for (int j = 0; j < numbers.length; j++) {
<<<<<<< HEAD
			items[j] = new HeapNode(""+numbers[j], numbers[j]);
		}
		heap.arrayToHeap(items);
		BinomialHeap.BinomialHeapSort(numbers);
		System.out.println(BinomialHeap.counter);
=======
			items[j] = new HeapNode(numbers[j]);
		}
		heap.arrayToHeap(items);
		BinomialHeap.BinomialHeapSort(numbers);
		System.out.println(heap.size());
>>>>>>> 579a789b15b332d8c6d2a59dfbe1a8a4544c162d
		
	}
	
	public static void checkBinomialHeapSort(int[] numbers){
		System.out.println();
		int [] sorted = BinomialHeap.sort(numbers);
		for (int i = 0; i < sorted.length; i++) {
			System.out.print(sorted[i]+ ", ");
		}
	}
	
	public static void checkStatistically(){
		int [] numbers= new int[100];
	    Random rand = new Random();
	    BinomialHeap heap = new BinomialHeap(1000,2000);
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rand.nextInt(1000);
		}
		BinomialHeap.HeapNode[] items= new BinomialHeap.HeapNode[numbers.length];
		
		for (int j = 0; j < numbers.length; j++) {
			items[j] = new HeapNode(""+numbers[j], numbers[j]);
		}
		
		heap.arrayToHeap(items);
		for (int i = 0; i < 100; i++) {
			if(heap.size()>0 && Math.random()<0.5){
			heap.delete(heap.array[rand.nextInt(heap.size)]);
			if(!heap.isHeap()){
				System.out.println("nooo! del"+heap.size);
				heap.print();
			}
			}
			else if (heap.size>0){
				int num = rand.nextInt(heap.size);
				HeapNode item = new HeapNode(""+num,num);
				heap.Insert(item);
				if(!heap.isHeap()){
					System.out.println("nooo! insert "+ heap.size);
					heap.print();
				}
			}
		}
		System.out.println(heap.isHeap());
		
	}
	
*/
	
}
