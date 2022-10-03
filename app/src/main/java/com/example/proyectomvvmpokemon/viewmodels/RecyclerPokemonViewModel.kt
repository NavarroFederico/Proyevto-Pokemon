package com.example.proyectomvvmpokemon.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.proyectomvvmpokemon.model.PokemonDataModel
import com.example.proyectomvvmpokemon.repositoy.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerPokemonViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    private val _itemSelect = MutableLiveData<PokemonDataModel?>()
    var itemDataSelected: PokemonDataModel? = null

    //liststate es el listado de pokemones
    private val _listState =
        MutableLiveData<MutableList<PokemonDataModel>>()//de tipo MutableLiveData ES MODIFICABLE
    var listState: LiveData<MutableList<PokemonDataModel>> =
        _listState //de tipo LiveData NO ES MODIFICABLE

    private val _progressState =
        MutableLiveData<Boolean>()//de tipo MutableLiveData ES MODIFICABLE
    var progressState: LiveData<Boolean> =
        _progressState //de tipo LiveData NO ES MODIFICABLE

    private val repository = PokemonRepository()
    lateinit var observerOnCategorySelected: Observer<PokemonDataModel>

    //preparar la corrutina para manejar las subrutinas
    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default //va ejecutar operaciones de i/o ,operaciones de servicio

    init {
        initObserver()
    }

    private fun initObserver() {
        observerOnCategorySelected = Observer { value ->
            value.let {
                _itemSelect.value = it
            }
        }
    }

    //aveces el liveData puede quedar vivo asi que con esto lo limpiamos
    fun clearSelection() {
        _itemSelect.value = null

    }

    fun setItemSelection(item: PokemonDataModel) {
        itemDataSelected = item
    }

    fun fetchPokemonData() {//me va traer los datos del backend del api server
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getPokemon()
            response?.body()?.pokemon.let{
                list->_listState.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
