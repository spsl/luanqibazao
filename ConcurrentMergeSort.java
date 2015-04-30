package test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoinTest extends RecursiveTask{

	public static final int INSERT_SORT_LENGTH = 23;
	private static final long serialVersionUID = 1L;
	private int[] array = null;
	private int start;
	private int end;
	
	ForkAndJoinTest(int[] a, int s, int e) {
		array = a;
		start = s;
		end = e;
	}
	

	protected Object compute() {
		boolean canCompute = (end - start) <= INSERT_SORT_LENGTH;
		if(canCompute){
			insertSort(array, start, end);
		}else{
			
			int mid = (end + start)/2;
			ForkAndJoinTest left = new ForkAndJoinTest(array,start,mid);
			ForkAndJoinTest right = new ForkAndJoinTest(array,mid,end);
			
			left.fork();
			right.fork();
			
			left.join();
			right.join();
			
			merge(array,start,mid,end);
		}
		
		return null;
	}
	
	public static int[] deepCopy(int[] a) {
		int b[] = new int[a.length];
		for(int i = 0; i <  a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}
	
	public static void main(String[] args) {

		ForkJoinPool forkJoinPool =new ForkJoinPool(6);
		
		int[] array = new SuijiArray(50000).get();
		int[] barray = deepCopy(array);
		int[] carray = deepCopy(array);
		ForkAndJoinTest test = new ForkAndJoinTest(array,0,array.length);
		long end = 0l;
		long start = System.currentTimeMillis();
		Future future = forkJoinPool.submit(test); 
		try {
			future.get();
		end = System.currentTimeMillis();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println("concurrentMergeSort:"+(end-start));
		
		long start1 = System.currentTimeMillis();
		mergeSort(barray, 0, barray.length);
		long end1 = System.currentTimeMillis();
		System.out.println("mergeSortTime=:"+(end1 - start1));
		
		long start2 = System.currentTimeMillis();
		Arrays.sort(carray);
		long end2 = System.currentTimeMillis();
		System.out.println("SystemSortTime=:"+(end2 - start2));
		
	}
	
	public static void insertSort(int[] a,int start, int end) {
		for(int i = start + 1; i < end; i++) {
			int t = a[i];
			int j = i;
			for(; j > start && t < a[j-1]; j--) {
				a[j] = a[j-1];
			}
			a[j] = t;
		}
	}
	
	
	public static void mergeSort(int[] a, int start, int end){
		
		if((end - start) <= INSERT_SORT_LENGTH ) {
			insertSort(a, start, end);
		} else {
			int mid = (start + end) /2;
			mergeSort(a,start,mid);
			mergeSort(a, mid, end);
			merge(a, start, mid, end);
		}
	}
	
	public static void merge(int[] a, int start, int mid, int end) {
		int[] b= new int[end-start];
		
		int k = 0;
		int i = start ;
		int j = mid;
		
		while(i < mid && j < end) {
			if(a[i] < a[j]) {
				b[k++] = a[i++];
			}else {
				b[k++] = a[j++];
			}
		}
		
		if(i == mid) {
			while(j < end) {
				b[k++] = a[j++];
			}
		}else {
			while(i < mid) {
				b[k++] = a[i++];
			}
		}
		
		for(int ii = 0; ii < (end - start);ii++) {
			a[start + ii] = b[ii];
		}
		
	}

}

class SuijiArray {
	private int length = 0;
	private int index = 0;
	private int[] array = null;
	Random rand = new Random();
	SuijiArray(int le) {
		length = le;
		index = le-1;
		array = new int[length];
		for(int i = 0; i < length; i++) {
			array[i] = i+1;
		}
	}
	
	public int[] get() {
		int[] a = new int[length];
		
		for(int i = 0; i< length; i++) {
			a[i] = next();
		}
		
		return a;
	}
	
	private int next() {
		if(index == 0){
			return array[0];
		}
		int random = rand.nextInt(index);
		int result = array[random];
		swap(array,index--,random);
		return result;
	}
	private void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}













