package com.example.bookexplorer.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.bookexplorer.domain.BookDetails
import com.example.inventorymanager.InventoryManagerAIDL

class ServiceHelper(val context: Context) {
    private var inventoryManagerService: InventoryManagerAIDL? = null
    private var mBound: Boolean = false
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            inventoryManagerService = InventoryManagerAIDL.Stub.asInterface(service)
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
            inventoryManagerService = null
        }
    }
    init {
       bindToService()
    }

    fun bindToService(){
        if(!mBound){
            val intent: Intent = Intent("AidlBookService")
            intent.setPackage("com.example.inventorymanager")
            context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }
    suspend fun getBookDetails(id: Int) : BookDetails?{
        bindToService()
        if(inventoryManagerService!=null) {
            val detailsAIDL = inventoryManagerService?.getBookAndDetails(id)
            return if (detailsAIDL != null) {
                var isFavorite = false;
                if (detailsAIDL.isFavorite == 1)
                    isFavorite = true
                BookDetails(
                    detailsAIDL.id,
                    detailsAIDL.bookId,
                    detailsAIDL.summary,
                    detailsAIDL.publishDate,
                    detailsAIDL.genre,
                    isFavorite
                )
            } else
                null
        } else return null

    }
    suspend fun setBookFavorite(id: Int, favorite: Boolean) : Boolean{
        bindToService()
        inventoryManagerService?.setBookFavorite(id, favorite)
        return mBound;
    }
}