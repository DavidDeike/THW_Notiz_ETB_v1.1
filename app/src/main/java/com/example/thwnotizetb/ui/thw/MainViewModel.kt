package com.example.thwnotizetb.ui.thw

import androidx.lifecycle.ViewModel




const val TAG = "MainViewModel"

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

  /* private val repository = AppRepository(RamAPI)

    val characters = repository.characters

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                _loading.value = ApiStatus.LOADING
                repository.loadCharacters(1)
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "error loading data from api: $e")
                _loading.value = ApiStatus.ERROR
            }
        }
    }*/
}
