import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class project1
{
    public int[] generateConstantData(int size) // generates and returns array with all zeros of required size
    {

      int[] data=new int[size]; //initializing the array with required size
      //System.out.println(data);
      int i=0;
      while(data.length<size)  //assigning the values i.e. 0
      {
          data[i]=0;
          i+=1;
      }
      return data;
    }
    public int[] generateSortedData(int size) // generates array of integers sorted in ascending order of required size 
    {
       int[] data=new int[size];  //initializing the array with required size
       for(int i=0;i<size;i++)    //assigning the values in ascending order
       {
         data[i]=i;
       }
        
        return data;
    }
    public int[] generateRandomData(int size) //generates array of random integers of required size
    { 
      //getting the data from generateSortedData function and storing into list
      List<Integer> preData=Arrays.stream(generateSortedData(size)).boxed().collect(Collectors.toList()); 
      Collections.shuffle(preData); //shuffles the list to make it random
      AtomicInteger count = new AtomicInteger(0); //initializing a counter that works in lambdas
      int[] data=new int[size];
      //typecasting Integers in shuffled list to int and pushing into array
      preData.stream().map(element->Integer.valueOf(element)).forEach((element)->{
        data[count.getAndIncrement()]=element;
  
      });
      return data;
    }
   //insertion sort start starts here
    public void insertionSort(int[] data)
    {
      long startTime=System.currentTimeMillis(); //storing the starttime in milli seconds
       int length= data.length;
       for(int i=0;i<length;++i)
       {
         int key=data[i];
         int j=i-1;
          // Moving elements of array data[0 to i-1] which are greater than the key by one place ahead of the present place 
         while(j>=0 && data[j] > key)
         {
           data[j+1]=data[j];
          j = j - 1;
         }
         data[j+1]=key;
       }

      long elapsedTime=System.currentTimeMillis() - startTime; //storing the time after sort is done
       System.out.println("Time taken for insertion sort is " + Long.toString(elapsedTime) +" milliseconds"); //printing the elapsed time of sort
      if(checkCorrectness(data)) //calling the function to check if data is sorted correctly
      {
        System.out.println("Data correctly sorted after running Insertion Sort.");
      }
      else{
        System.out.println("Data incorrectly sorted after running Insertion Sort.");
      }
    }
   //insertion sort ends here

   //selection sort starts here
    public void selectionSort(int[] data)
    {
      long startTime=System.currentTimeMillis(); //storing the starttime in milli seconds
      int length= data.length;
      for (int i = 0; i < length-1; i++)
      {
          // finds the minimum element in unsorted array
          int min = i;
          for (int j = i+1; j < length; j++)
              if (data[j] < data[min])
                  min = j;

          // swapping the found minimum element with the first element
          int temp = data[min];
          data[min]=data[i];
          data[i]=temp;
      }
      long elapsedTime=System.currentTimeMillis() - startTime; //storing the time after sort is done
       System.out.println("Time taken for selection sort is " + Long.toString(elapsedTime) +" milliseconds"); //prints the elapsed time of sort

      if(checkCorrectness(data)) //calling the function to check if data is sorted correctly or not
      {
        System.out.println("Data correctly sorted after running Selection Sort.");
      }
      else{
        System.out.println("Data incorrectly sorted after running Selection Sort.");
      }

    }
    //selection sort ends here
  
  public boolean checkCorrectness(int[] sortedData) {

    //comparing the elements in array 
    for(int i=1;i<sortedData.length;i++)
    {
        if(sortedData[i]<sortedData[i-1])
        {
          return false;
        }
    }
    return true;

}
    public static void main(String[] args)
    {
         String typeOfAlgo;
         int noOfElements;
         String typeOfData;
         //accesing and storng the cli arguments in the variables
        typeOfAlgo=args[0]; 
        noOfElements=Integer.parseInt(args[1]);
        typeOfData=args[2];
        int[] data=new int[noOfElements]; //inititliazin the array
        project1 p= new project1();  //creating the object
        switch(typeOfData)
        {
          case "c":
          data=p.generateConstantData(noOfElements); //calling the function that generates constant data and storing the array
          break;
          case "r":
          data=p.generateRandomData(noOfElements);  //calling the function that generates random data and storing the array
          break;
          case "s":
          data=p.generateSortedData(noOfElements);  //calling the function that generates sorted data and storing the array
          break;
          default:
          System.out.println("Type of data is not available please select from c, s , r"); //prints error message if option is not available
          System.exit(0);

        }
      switch(typeOfAlgo)
      {
        case "i":
        p.insertionSort(data); //calling insertion sort 
        break;
        case "s":
        p.selectionSort(data); //calling selection sort
        break;
        case "q":
        MedQuickSort qu=new MedQuickSort(); //creating object
        long start=System.currentTimeMillis();  //storing the start time
        //calling the sort function
       qu.QuickSort(data, 0, data.length-1);
        long elapsedTime=System.currentTimeMillis()-start;  //finding th elapsed time
        System.out.println("time taken to sort data using Quick Sort is "+elapsedTime +" milliseconds"); //prints the elapsed time
        if(p.checkCorrectness(data))  //calling the function to check if data is sorted correctly or not
        {
          System.out.println("Data correctly sorted after running Quick Sort.");
        }
        else{
          System.out.println("Data incorrectly sorted after running Quick Sort.");
        }
        break;
        default:
        System.out.println("Type of sort is not available please chose form i, s, q"); //prints error message if option is not available
        System.exit(0);
      }

     
    }
}
class MedQuickSort  {
	public int[] medianPivot(int[] data, int low, int high) {
		int mid = (high+low) / 2;
		int[] arr = { data[low], data[mid], data[high] }; //creating an array with values at indexes low, mid and high 
		Arrays.sort(arr); //Sorting the array
		int middleValue = arr[1]; //storing the middle value
		// swap with the last to serve as pivot
		int temp = data[high];
    data[high]=middleValue;
    data[mid]=temp ;
		return data;
	}

	
	public void QuickSort(int[] data, int low, int high) {

		if (low < high) {
			int part = partition(data, low, high); //calling partition function
			//sorting elements recursively before and after the partition
			QuickSort(data, low, part - 1); 
			QuickSort(data, part + 1, high);
		}
	}

	public int partition(int[] data, int low, int high) {
    data=medianPivot(data, low, high); //calling the medianpivot function and storing the returned array in arr
		int pivot = data[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// checking if current element is smaller than or equal to pivot
			if (data[j] <= pivot) {
				i++;
				// swap arr[i] and arr[j]
				int temp = data[i];
				data[i]=data[j];
				data[j]=temp ;
			}
		}
		// swap arr[i+1] and arr[high] (or pivot)
		int temp = data[i + 1];
    data[i+1]=data[high]; 
    data[high]=temp;
		return i + 1;
	}
}