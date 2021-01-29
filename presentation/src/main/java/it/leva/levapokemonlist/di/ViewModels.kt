package it.leva.levapokemonlist.di

import it.leva.levapokemonlist.view.detail.DetailViewModel
import it.leva.levapokemonlist.view.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get(), get()) }
    viewModel { DetailViewModel(get()) }
}