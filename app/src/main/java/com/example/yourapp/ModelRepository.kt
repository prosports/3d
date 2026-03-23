// ModelRepository.kt

package com.example.yourapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Define the data model
data class Item(val id: Int, val name: String)

// Define a repository interface
interface Repository {
    fun getItems(): LiveData<List<Item>>
}

// Implement the repository pattern
class ModelRepository : Repository {
    private val itemsLiveData = MutableLiveData<List<Item>>()

    init {
        // Simulate fetching data from a data source
        itemsLiveData.value = listOf(
            Item(1, "Item 1"),
            Item(2, "Item 2"),
            Item(3, "Item 3")
        )
    }

    override fun getItems(): LiveData<List<Item>> {
        return itemsLiveData
    }
}