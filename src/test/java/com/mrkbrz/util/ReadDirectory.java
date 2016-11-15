package com.mrkbrz.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.mrkbrz.pojo.Item;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mirko Manuel Bruzzi on 14/11/16.
 */
public class ReadDirectory {
    private static ReadDirectory ourInstance = new ReadDirectory();

    private static Gson gsonBuilder = new GsonBuilder().serializeNulls().create();

    public static List<Item[]> listItem;

    public static ReadDirectory getInstance() {
        return ourInstance;
    }

    private ReadDirectory() {
    }

    /**
     * load all the input file from a certain directory, then they will be added to a list.
     * this list is initialized internally it will never be null, if no files are found, then
     * the list will be empty.
     */
    public void loadInput(){
        final File directory = new File(this.getClass().getResource("/"+Constant.INPUT_DIR).getFile());
        if(directory==null || directory.listFiles().length==0){
            listItem = Collections.emptyList();
            return;
        }
        JsonReader reader = null;
        listItem = new ArrayList<>(directory.listFiles().length);
        try{
            for (File input : directory.listFiles()) {
                reader = new JsonReader(new FileReader(input));
                Item[] o = gsonBuilder.fromJson(reader, Item[].class);
                Item[] cleanedResult = Arrays.stream(o).filter(obj->Utils.validateEntity(obj)!=null).toArray(Item[]::new);
                listItem.add(cleanedResult);
            }
        }catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
