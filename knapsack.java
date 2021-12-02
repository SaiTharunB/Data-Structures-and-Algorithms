import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class knapsack
{
    public double fractionalKnapsack(List<item> items,int weight,int size)
    {
        List<Double> profits=new ArrayList<>();
        List<Integer> weights=new ArrayList<>();
        List<Double> ratio=new ArrayList<>();
        List<item> knapsack=new ArrayList<>();
        items.stream().forEach(i->{
            profits.add(i.profit);
            weights.add(i.weight);
            double r=i.profit/i.weight;
            ratio.add(r);
        });
        while(weight>0 && size>0)
        {
            Double max= Collections.max(ratio);
            int index = ratio.indexOf(max);
            item i=items.get(index);
            if(i.weight<weight)
            {
                knapsack.add(i);
                weight-=i.weight;
                items.remove(index);
                ratio.remove(index);
            }
            else
            {
                double newProfit=(i.profit/i.weight)*weight;
                knapsack.add(new item(newProfit,weight));
                weight-=i.weight;
                items.remove(index);
                ratio.remove(index);
            }
            

            size--;
        }
       double profit=0;
        for(item i : knapsack)
        {
            profit+=i.profit;
        }
        return profit;
    }
    public static void main(String[] args)
    {
         knapsack k=new knapsack();
         List<item> items=new ArrayList<>();
         items.add(new item(5,1));
         items.add(new item(10,3));
         items.add(new item(15,5));
         items.add(new item(7,4));
         items.add(new item(8,1));
         items.add(new item(9,3));
         items.add(new item(4,2));
         System.out.println("Maximum profit is " + k.fractionalKnapsack(items, 15, items.size()));
    }
}
class item
{
    public double profit;
    public int weight;
    public item(double profit,int weight)
    {
        this.profit=profit;
        this.weight=weight;
    }

    public String toString(){//overriding the toString() method  
        return "profit: "+profit+" weight: "+weight;  
       }  
}