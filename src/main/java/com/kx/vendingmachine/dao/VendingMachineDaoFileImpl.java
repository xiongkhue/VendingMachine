/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.dao;

import com.kx.vendingmachine.dto.VendingMachineItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author khuxi
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    
    private Map<String, VendingMachineItem> vendingMachineItems = new HashMap<>();
    public static final String VENDINGITEMS_FILE = "vendingitems";
    public static final String DELIMITER = "::";

//    @Override
//    public VendingMachineItem addVendingMachineItem(String tag, VendingMachineItem vendingMachineItems) throws VendingMachineDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public List<VendingMachineItem> getAllVendingMachineItems() throws VendingMachineDaoException{
        loadVendingMachineItems();
        return new ArrayList<VendingMachineItem>(vendingMachineItems.values());
    }

    @Override
    public VendingMachineItem getVendingMachineItem(String tag) throws VendingMachineDaoException{
        loadVendingMachineItems();
        return vendingMachineItems.get(tag);
    }

    @Override
    public VendingMachineItem buyingVendingMachineItem(String tag, VendingMachineItem bought) throws VendingMachineDaoException{
        
        loadVendingMachineItems();
        vendingMachineItems.remove(tag);
        VendingMachineItem boughtVendingMachineItem = bought;
        if(tag.equals("")){

        } else {
            boughtVendingMachineItem = vendingMachineItems.put(bought.getTag(), bought);
            writeVendingMachineItems();
        }
        return boughtVendingMachineItem;
    }

//    @Override
//    public VendingMachineItem removeVendingMachineItem(String tag) throws VendingMachineDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    private VendingMachineItem unmarshallVendingMachineItem(String vendingMachineItemAsText){
        // vendingItemAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Basic Chips::0.75::4
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in vendingMachineItemTokens.
        // Which should look like this:
        // ______________________________________________
        // |    |         | 
        // |Basic Chips|0.75|4
        // |    |         | 
        // ----------------------------------------------
        //  [0]  [1]    [2]        
        String[] vendingMachineItemTokens = vendingMachineItemAsText.split(DELIMITER);

        // Given the pattern above, the item name is in index 0 of the array.
        String tag = vendingMachineItemTokens[0];

        // Which we can then use to create a new VendingMachineItem object to satisfy
        // the requirements of the VendingMachineItem constructor.
        VendingMachineItem vendingMachineItem = new VendingMachineItem(tag);

        // However, there are 2 remaining tokens that need to be set into the
        // new VendingMachineItem object. Do this manually by using the appropriate setters.

        // Index 1 - Price
        //vendingMachineItem.setPrice(vendingMachineItemTokens[1]);
        vendingMachineItem.setPrice(vendingMachineItemTokens[1]);

        // Index 2 - Quantity
        vendingMachineItem.setQuantity(vendingMachineItemTokens[2]);

        // We have now created a VendingMachineItem! Return it!
        return vendingMachineItem;
    }
    private void loadVendingMachineItems() throws VendingMachineDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                new BufferedReader(
                        new FileReader(VENDINGITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException(
                "-_- Could not load VendingItems data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentVendingMachineItem holds the most recent VendingMachineItem unmarshalled
        VendingMachineItem currentVendingMachineItem;
        // Go through VENDINGITEMS_FILE line by line, decoding each line into a 
        // VendingMachineItem object by calling the unmarshallVendingMachineItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a VendingMachineItem
            currentVendingMachineItem = unmarshallVendingMachineItem(currentLine);

            // We are going to use the title as the map key for our VendingMachineItem object.
            // Put currentVendingMachineItem into the map using product name(tag) as the key
            vendingMachineItems.put(currentVendingMachineItem.getTag(), currentVendingMachineItem);
        }
        // close scanner
        scanner.close();
    }
    private String marshallVendingMachineItem(VendingMachineItem aVendingMachineItem){
        // We need to turn a VendingMachineItem object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Beef Jerky::1.20::5

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the tag, since that's supposed to be first.
        String vendingMachineItemAsText = aVendingMachineItem.getTag() + DELIMITER;

        // add the rest of the properties in the correct order:

        // ReleaseDate
        vendingMachineItemAsText += aVendingMachineItem.getPrice() + DELIMITER;

        // MPAARating
        vendingMachineItemAsText += aVendingMachineItem.getQuantity();

        // We have now turned a vendingmachineitem to text! Return it!
        return vendingMachineItemAsText;
    }
    /**
 * Writes all vendingMachineItems in the vendingMachineItems collection out to a VENDINGITEMS_FILE.  See loadVendingMachineItems
 * 
 * for file format.
 * 
 * @throws VendingMachineDaoException if an error occurs writing to the file
 */
private void writeVendingMachineItems() throws VendingMachineDaoException {
        // Not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDINGITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException(
                "Could not save VendingMachineItem data.", e);
        }

        // Write out the VendingMachineItem objects to the VendingMachineItems file.
        String vendingMachineItemAsText;
        boolean isLast = false;
        int i = 1;
        List<VendingMachineItem> vendingMachineItemList = this.getAllVendingMachineItems();
        for (VendingMachineItem currentVendingMachineItem : vendingMachineItemList) {
            if(isLast){
                // turn a VendingMachineItem into a String
                vendingMachineItemAsText = marshallVendingMachineItem(currentVendingMachineItem);
                // write the VendingMachineItem object to the file
                out.print(vendingMachineItemAsText);
                // force PrintWriter to write line to the file
                out.flush();             
            } else {
                i++;
                // turn a VendingMachineItem into a String
                vendingMachineItemAsText = marshallVendingMachineItem(currentVendingMachineItem);
                // write the VendingMachineItem object to the file
                out.println(vendingMachineItemAsText);
                // force PrintWriter to write line to the file
                out.flush();
                if(i == vendingMachineItemList.size()){
                    isLast = true;
                }
            }
        }
        // Clean up
        out.close();
    }
}
