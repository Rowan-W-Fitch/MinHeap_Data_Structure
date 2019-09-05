import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryMinHeap implements IPriorityQueue {
	Integer[] arr;
	//int min_val;
	
	public BinaryMinHeap(int len) {
		arr = new Integer[len];
		for(int i=0; i<arr.length; i++) {
			arr[i] = null;
		}
	}

	@Override
	public void add(int value) {
		if(size() >= arr.length-1) {
			int oldLen = arr.length;
			Integer[] newArr = Arrays.copyOf(arr, oldLen*2);
			arr = newArr;
		}
		
		arr[size()+1] = value;
		
		
		bubbleUp();
		
	}

	@Override
	public boolean isEmpty() {
		for(int i=1; i< arr.length; i++) {
			if(arr[i] != null) return false;
			else continue;
		}
		return true;
	}

	@Override
	public int peek() {
		if(isEmpty()) throw new NoSuchElementException("queue is empty");
		else return arr[1];
	}

	@Override
	public int remove() {
		if(isEmpty()) throw new NoSuchElementException("queue is empty");
		else {
			int old_head = peek();
			arr[1] = arr[size()];
			arr[size()] = null;
			bubbleDown();
			return old_head;
		}
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;
		else {
			int len = 0;
			for(int i=1; i<arr.length; i++) {
				if(arr[i] != null) len += 1;
			}
			return len;
		}
	}

	@Override
	public void bubbleDown() {
		int indx = 1;
		while(2*indx <= size()) {
			int lessVal = 2*indx;
			//checking if right branch is smaller bc of reassignment
			if(2*indx +1 <= size() && arr[2*indx] > arr[2*indx +1]) lessVal = 2*indx +1;
			//swapping w/ smaller indx
			if(arr[indx] > arr[lessVal]) {
				int bigger = arr[indx];
				arr[indx] = arr[lessVal];
				arr[lessVal] = bigger;
			}
			else break;
			//updating indx
			indx = lessVal;
		}
	}

	@Override
	public void bubbleUp() {
		int indx = size();
		while(indx > 1 && arr[indx/2] > arr[indx]) {
			int smaller = arr[indx];
			arr[indx] = arr[indx/2];
			arr[indx/2] = smaller;
			
			indx = indx/2;
		}
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return Arrays.deepToString(arr);
	}
	

}
