package com.mrkbrz;

import com.mrkbrz.pojo.Item;
import com.mrkbrz.util.ReadDirectory;
import com.mrkbrz.util.Utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Mirko Manuel Bruzzi on 14/11/16.
 */
public class StartApplication {

    public static void main(String[] args) {
        ReadDirectory.getInstance().loadInput();
        final List<Item[]> items = ReadDirectory.listItem;
        if(!items.isEmpty()){
            IntStream.range(0, items.size()).forEach(idx -> {
                int numOutput = idx + 1;
                System.out.println("Output: " + numOutput);
                BigDecimal total = BigDecimal.ZERO;
                BigDecimal taxes = BigDecimal.ZERO;
                for (Item item : items.get(idx)) {
                    BigDecimal res = Utils.getTaxes(item);
                    taxes = taxes.add(res);
                    Number newPrice = new BigDecimal(item.getPrice().toString()).add(res).setScale(2,BigDecimal.ROUND_UP);
                    if(item.getUnits().intValue()>1){
                        newPrice = new BigDecimal(newPrice.toString()).multiply(new BigDecimal(item.getUnits().intValue())).setScale(2,BigDecimal.ROUND_UP);
                        newPrice = Utils.customRound(new BigDecimal(newPrice.toString()));
                    }
                    item.setPrice(newPrice);
                    total = total.add(new BigDecimal(item.getPrice().toString())).setScale(2,BigDecimal.ROUND_UP);
                    System.out.println(item.toString());
                }
                System.out.println("Sales Taxes: " + taxes);
                System.out.println("Total: " + total);
                System.out.println();
            });
        }
        else{
            System.out.println("No input files provided");
        }
    }
}
