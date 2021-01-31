package it.leva.levapokemonlist.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import it.leva.domain.model.Pokemon
import it.leva.domain.state.DataState
import it.leva.domain.viewstate.PokemonListViewState
import it.leva.levapokemonlist.R
import it.leva.levapokemonlist.extension.scrollListener
import it.leva.levapokemonlist.view.base.BaseFragment
import it.leva.levapokemonlist.view.list.adapter.MainListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonListFragment : BaseFragment() {

    var mView: View? = null
    val viewmodel: ListViewModel by viewModel()
    var adapter: MainListAdapter? = null
    var recylerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewmodel.fetchList()
        startObserver()
    }

    private fun startObserver() {
        viewmodel.getPokeList().observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.SUCCESS -> {
                    hideProgress()
                    dataState.data?.let {
                        refreshView(it)

                    }
                }
                is DataState.ERROR -> {
                    hideProgress()

                }
                is DataState.LOADING -> {
                    showProgress()
                }
            }
        })

        viewmodel.getAdditionalPokeItems().observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.SUCCESS -> {
                    dataState.data?.let {
                        it.pokemonList?.let { additionaPokeItems ->
                            adapter?.addItemsToList(additionaPokeItems)
                        }
                    }
                }
                is DataState.ERROR -> {
                    Toast.makeText(context, dataState.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is DataState.LOADING -> {

                }
            }
        })
    }

    fun initView() {
        mainLayout = mView?.findViewById(R.id.detail_container)
        progressLayout = mView?.findViewById(R.id.progress_container)
    }

    private fun refreshView(pokemonListViewState: PokemonListViewState) {
        pokemonListViewState.pokemonList?.let {
            adapter = MainListAdapter(it.toMutableList()) { name, url ->
                val action =
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                        name,
                        url
                    )
                mView?.let { Navigation.findNavController(it).navigate(action) }

            }
            recylerView = mView?.findViewById<RecyclerView>(R.id.rvPokemonList)
            recylerView?.adapter = adapter
            recylerView?.scrollListener {
                pokemonListViewState.nextPage?.let { url ->
                    viewmodel.getNextPage(url)
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)


        return mView
    }

    companion object {

        fun newInstance() =
            PokemonListFragment()
    }
}