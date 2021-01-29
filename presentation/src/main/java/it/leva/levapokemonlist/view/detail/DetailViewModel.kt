package it.leva.levapokemonlist.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.leva.domain.state.DataState
import it.leva.domain.usecase.GetPokemonDetailUseCase
import it.leva.domain.viewstate.PokemonViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val getPokemonDetailUseCase: GetPokemonDetailUseCase): ViewModel() {
    private val pokeDetail = MutableLiveData<DataState<PokemonViewState>>()
    fun getPokeDetail() = pokeDetail

    fun fetchDetail(name: String, url:String) {
        viewModelScope.launch {
            getPokemonDetailUseCase.invoke(name, url).collect {
                pokeDetail.postValue(it)
            }
        }
    }
}