package com.mrkbrz.util;

import com.mrkbrz.pojo.Item;

import java.math.BigDecimal;

import static com.mrkbrz.util.Constant.FIVE;
import static com.mrkbrz.util.Constant.HUNDRED;
import static com.mrkbrz.util.Constant.TEN;


/**
 * Created by Mirko Manuel Bruzzi on 14/11/16.
 */
public class Utils {

    /**
     * @param item that holds the necessary property
     * @return the tax number that has to  be paid for a certain product
     */
    public static BigDecimal getTaxes(Item item){
        BigDecimal result = BigDecimal.ZERO;

        if(item.getHasExtraTax()){
            result = result.add(new BigDecimal(item.getPrice().toString()).multiply(TEN).divide(HUNDRED)).setScale(2,BigDecimal.ROUND_UP);
            result = customRound(result);
        }
        if(item.getImported()){
            result = result.add(new BigDecimal(item.getPrice().toString()).multiply(FIVE).divide(HUNDRED)).setScale(2,BigDecimal.ROUND_UP);
            result = customRound(result);
        }


        return result.setScale(2,BigDecimal.ROUND_UP);
    }

    /**
     * @param num that has to be round
     * @return a new BigDecimal rounded
     */
    public static BigDecimal customRound(BigDecimal num) {
        return new BigDecimal(Math.round(num.floatValue() * 20) / 20.0);
    }

    /**
     * checks if an entity has all the value not null
     * if a value is null then a default is used.
     * if there is no description then will be return e null entity
     * and skipped when it should be added to the list
     * @param i
     * @return Item
     */
    public static Item validateEntity(Item i){
        final Item tempItem = i.cloneObject();
        if(tempItem!=null){
            if(i.getDescription()==null || i.getDescription().isEmpty()){
                return null;
            }
            else if(i.getHasExtraTax()==null){
                return null;
            }
            else{
                if(i.getUnits()==null){
                    tempItem.setUnits(1);
                }
                if(i.getPrice()==null){
                    tempItem.setPrice(1);
                }
            }
        }
        return tempItem;
    }

}
