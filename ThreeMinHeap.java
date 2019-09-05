import java.util.Arrays;
import java.util.NoSuchElementException;

public class ThreeMinHeap implements IPriorityQueue {
	Integer[] arr;
	
	public ThreeMinHeap(int i) {
		arr = new Integer[i];
		for(int k =0; k< arr.length; k++) {
			arr[k] = null;
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
		while(3*indx -1 <= size()) {
			int lessVal = 3*indx -1;
			//checking if right branch is smaller bc of reassignment
			if(3*indx +1 <= size() && arr[3*indx -1] > arr[3*indx +1]) lessVal = 3*indx +1;
			if(3*indx <= size() && arr[lessVal] > arr[3*indx]) lessVal = 3*indx;
			//swapping w/ smaller indx
			if(arr[indx] >= arr[lessVal]) {
				int bigger = arr[indx];
				arr[indx] = arr[lessVal];
				arr[lessVal] = bigger;
			}
			//updating indx
			indx++;
		}
		
	}

	@Override
	public void bubbleUp() {
		int indx = size();
		while(indx > 1 && arr[(indx + 1)/3] > arr[indx]) {
			int smaller = arr[indx];
			arr[indx] = arr[(indx +1)/3];
			arr[(indx+1)/3] = smaller;
			
			indx = (indx+1)/3;
		}
		
	}
	
	public String toString() {
		return Arrays.deepToString(arr);
	}

}
