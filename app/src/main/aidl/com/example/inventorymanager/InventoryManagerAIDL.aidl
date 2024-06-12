// InventoryManagerAIDL.aidl
package com.example.inventorymanager;
import com.example.inventorymanager.DetailsAIDL;


// Declare any non-default types here with import statements

interface InventoryManagerAIDL {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     DetailsAIDL getBookAndDetails(int id);
     void setBookFavorite(int id, boolean favorite);
}


