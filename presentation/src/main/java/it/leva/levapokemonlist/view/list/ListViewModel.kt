package it.leva.levapokemonlist.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import it.leva.domain.state.DataState
import it.leva.domain.usecase.GetPokemonListUseCase
import it.leva.domain.usecase.GetPokemonNextPageListUseCase
import it.leva.domain.viewstate.PokemonListViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonNextPageListUseCase: GetPokemonNextPageListUseCase
) : ViewModel() {

    private val pokelist = MutableLiveData<DataState<PokemonListViewState>>()
    fun getPokeList() = pokelist


    fun fetchList() {
        viewModelScope.launch {
            getPokemonListUseCase.invoke(10, 0).collect {
                pokelist.postValue(it)
            }
        }

    }
}