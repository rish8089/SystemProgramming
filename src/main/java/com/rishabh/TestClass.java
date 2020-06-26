package com.rishabh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishabh.jobs.PrepareBeverage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestClass {
    public static void main(String[] args) throws IOException {

        if(args.length==0) {
            System.out.println("Test file not passed");
            System.exit(0);
        }

        CoffeMachine coffeMachine=new CoffeMachine();
        List<Beverage> beverageList=new ArrayList<>();
        init(beverageList,coffeMachine,args[0]);

        //By making the thread pool of fixed size n (no of outlets) , it is ensured that only n beverages can be prepared in parallel
        ExecutorService executorService = Executors.newFixedThreadPool(coffeMachine.getOutlets());
        for(Beverage beverage:beverageList)
            executorService.execute(new PrepareBeverage(coffeMachine,beverage));
        executorService.shutdown();



    }

    /**
     * This method initializes the coffee machine instance and populates the input beverages into the list
     * @param beverageList
     * @throws IOException
     */
    private static void init(List<Beverage> beverageList,CoffeMachine coffeMachine,String testFile) {
        ObjectMapper mapper = new ObjectMapper();

        Map<?, ?> map = null;
        try {
            map = mapper.readValue(TestClass.class.getClassLoader().getResource(testFile), Map.class);
        } catch (Exception e) {
            System.out.println("Either test file not found or file structure is invalid");
            System.exit(0);
        }

        //initializing coffee machine object
        LinkedHashMap<?,?> machine=(LinkedHashMap<?,?>)map.get("machine");
        LinkedHashMap<?,?> outlets=(LinkedHashMap<?,?>)machine.get("outlets");
        coffeMachine.setOutlets((Integer)outlets.get("count_n"));
        LinkedHashMap<?,?> totalIngredients=(LinkedHashMap<?,?>)machine.get("total_items_quantity");
        for(Map.Entry<?,?> ingredient:totalIngredients.entrySet())
            coffeMachine.getTotalIngredients().put((String)ingredient.getKey(),(Integer)ingredient.getValue());

        //instantiating beverages and initializing them
        LinkedHashMap<?,?> beverages=(LinkedHashMap<?,?>)machine.get("beverages");

        for(Map.Entry<?,?> beverage:beverages.entrySet())
        {
            LinkedHashMap<?,?> beverageIngredients=(LinkedHashMap<?,?>)beverage.getValue();
            Beverage newBeverage=new Beverage();
            newBeverage.setName((String)beverage.getKey());
            for(Map.Entry<?,?> beverageIngredient:beverageIngredients.entrySet())
                newBeverage.getIngredients().put((String)beverageIngredient.getKey(),(Integer)beverageIngredient.getValue());

            beverageList.add(newBeverage);
        }
    }
}
