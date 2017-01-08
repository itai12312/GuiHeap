package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import heap.BinomialHeap;

public class YotamsRandomicTester {
    static int k=10000;


	public static void main(String[] args) {
		// RANDOMIC TESTER FOR BINOMIAL HEAP 2.0
		
		
		int m = 1000;  //number of elements to insert at start
		int p = 3000;   //number of tests - inserts,deletions,decrease-key,etc.
					    //(don't push it, it's slow)

		Random randi = new Random();
		BinomialHeap BHEAP = new BinomialHeap();
		List<Integer> list = new ArrayList<Integer>();
		check_condition_and_print(!BHEAP.isValid(),10,null);
		check_condition_and_print(BHEAP.size()!=0,20,null);
		check_condition_and_print(!BHEAP.empty(),21,null);
        //Gui swingControlDemo = new Gui(BHEAP);
		BHEAP.insert(69);

		check_condition_and_print(!BHEAP.isValid(),30,null);
		check_condition_and_print(BHEAP.size()!=1,40,null);
		BHEAP.deleteMin();
		// heap should be empty now
		check_condition_and_print(!BHEAP.isValid(),50,null);
		check_condition_and_print(BHEAP.size()!=0,60,null);
		check_condition_and_print(!BHEAP.empty(),61,null);

		BHEAP.deleteMin();
		BHEAP.delete(69);
		BHEAP.delete(666);
		BHEAP.decreaseKey(89, 88);
		BHEAP.decreaseKey(69, 68);

		// add m different random positive ints
		for (int i=0;i<m;i++){
			int num = randi.nextInt(k)+1;
			while(list.contains(num)) num = randi.nextInt(k)+1;
			list.add(num);
			BHEAP.insert(num);
			if(!BHEAP.isValid()) {
				System.out.println("ERROR 70: i=" + i + ", num =" + num);

			}
		}
		System.out.println("Succeeded "+70);

		check_condition_and_print(BHEAP.size()!=m,80,null);
		
		int sum=0;
		boolean[] rep = BHEAP.binaryRep();
		for(int j=0;j<rep.length;j++){
			if(rep[j]) sum+=Math.pow(2, j);
		}
		check_condition_and_print(sum!=m,90,null);
		
		// p random inserts,deletes,deletes-min,decrease-key
		for (int i=0;i<p;i++){
			int random_case = randi.nextInt(7);
            if(i%100 == 0){
                System.out.println("starting case: "+i +" at option "+ random_case);
            }

            if(random_case<=3){
				//insert
				int num = randi.nextInt(k)+1;
				while(list.contains(num)) num = randi.nextInt(k)+1;
				list.add(num);
				BHEAP.insert(num);
				if(!BHEAP.isValid()) System.out.println("ERROR 100: i="+i+", num ="+num);
				if(BHEAP.size()!=list.size()) System.out.println("ERROR 101: i="+i+", num ="+num);

			}
			else if(random_case==4){
				//deletion
				if(randi.nextBoolean()){
					//find-min, delete-min
					int heap_minimum = BHEAP.findMin();
					int[] min_and_index = get_minimum(list);
					int list_minimum = min_and_index[0];
					
					if(heap_minimum!=list_minimum){
						System.out.println("ERROR 110: wrong minimum in heap. "+heap_minimum+", "+list_minimum+" and i="+i);
					}
					BHEAP.deleteMin();
					list.remove(min_and_index[1]);
					if(!BHEAP.isValid()) System.out.println("ERROR 120: after delete-min of "+heap_minimum+" and i="+i);
					if(BHEAP.size()!=list.size()) System.out.println("ERROR 130: after delete-min of "+heap_minimum+" and i="+i);
					if(BHEAP.findMin()==heap_minimum) {
                        System.out.println("ERROR 140: after delete-min of " + heap_minimum + ". Two SAME minimums in a row" + " and i=" + i);
                        int xy = 0;
                    }
				}
				else{
					//delete node
					int ind = randi.nextInt(list.size());
					int value = list.get(ind);
					BHEAP.delete(value);
					list.remove(ind);
					if(!BHEAP.isValid()) System.out.println("ERROR 141: after delete of "+value+" and i="+i);
					if(BHEAP.size()!=list.size()) {
                        String p11 = diff(BHEAP,list);
                        System.out.println("ERROR 151: after delete of " + value + " and i=" + i);

                    }
                }
			}
			else if(random_case==5){
				//decrease=key
				int ind = randi.nextInt(list.size());
				int value = list.get(ind);
				while(value<10){ind = randi.nextInt(list.size()); value = list.get(ind);}

				int new_value = randi.nextInt(value-1)+1;
				while(list.contains(new_value) || new_value>=value) new_value = randi.nextInt(value-1)+1;
				
				BHEAP.decreaseKey(value, new_value);
				list.remove(ind);
				list.add(new_value);
				if(!BHEAP.isValid()) System.out.println("ERROR 160: after decrease-key of "+value+" to "+new_value+" and i="+i);
				if(BHEAP.size()!=list.size()) System.out.println("ERROR 170: after decrease-key of "+value+" to "+new_value+" and i="+i);
			}
			else if(random_case==6){
				//fake actions : 
				if(BHEAP.size()!=list.size()) System.out.println("ERROR 180: i="+i);
				int new_num = randi.nextInt(k)+1;
				while(list.contains(new_num)) new_num = randi.nextInt(k)+1;
				
				//fake decrease-key
				// BHEAP.decreaseKey(new_num, -666); /*changed this-not possible on our tests*/
				if(BHEAP.size()!=list.size()) System.out.println("ERROR 181: i="+i);
				if(!BHEAP.isValid()) System.out.println("ERROR 182: i="+i);

				//fake delete
				BHEAP.delete(new_num);
				if(BHEAP.size()!=list.size()) System.out.println("ERROR 183: i="+i);
				if(!BHEAP.isValid()) System.out.println("ERROR 184: i="+i);
				
				
				//more tests
				sum=0;
				rep = BHEAP.binaryRep();
				for(int j=0;j<rep.length;j++){
					if(rep[j]) sum+=Math.pow(2, j);
				}
				if(BHEAP.size()!=sum) System.out.println("ERROR 185: i="+i);
									

			}
		}
		System.out.println("Succeeded 100-185");
		//System.out.println("\n expected "+ (m+(3*p/7)) + ", measured : "+ BHEAP.size());
		
		
		Collections.sort(list);
		int n = list.size();
		for(int i=0;i<n;i++){
			if(BHEAP.findMin()!=list.get(0)) System.out.println("ERROR 190: i="+i);
            //if(BHEAP.findMin()!=list.get(0) && BHEAP.findMin().key==(get_minimum(list)[0])){
            //    System.out.print("appar ok?");
            //}
			BHEAP.deleteMin();
			list.remove(0);
			if(BHEAP.size()!=list.size()) System.out.println("ERROR 191: i="+i);
			if(!BHEAP.isValid()) System.out.println("ERROR 192: i="+i);
		}
		
		// heap should be empty now
		check_condition_and_print(!BHEAP.isValid(),197,null);
		check_condition_and_print(BHEAP.size()!=0,198,null);
		check_condition_and_print(!BHEAP.empty(),199,null);

		System.out.println("DONE :) ");
	}


    public static String diff(BinomialHeap h, List<Integer> l){
        String s = "";
        for(Integer in:h.map.keySet()){
            if(h.search(in)==null){
                s+=h+" is in map but not hash ";
            }
            if(!l.contains(in)){
                s+=in+" ";
            }
        }
        return s;
    }


    public static void check_condition_and_print(boolean b, int error_number, String msg){
		if(b){
			System.out.println((msg!=null) ? "ERROR "+error_number+" : "+msg : "ERROR "+error_number);
		}
		else System.out.println("Succeeded "+error_number);
	}
	
	public static int[] get_minimum(List<Integer> list){
		int minimum = list.get(0);
		int index = 0;
		for(int i=0;i<list.size();i++){
			if (list.get(i)<minimum){
				minimum = list.get(i);
				index = i;
			}
		}
		return new int[] {minimum,index};
	}

}
