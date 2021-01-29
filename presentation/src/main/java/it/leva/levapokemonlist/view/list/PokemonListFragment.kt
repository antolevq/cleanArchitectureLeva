package it.leva.levapokemonlist.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import it.leva.domain.model.Pokemon
import it.leva.domain.state.DataState
import it.leva.levapokemonlist.R
import it.leva.levapokemonlist.view.list.adapter.MainListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonListFragment : Fragment() {

    var mView: View? = null
    val viewmodel: ListViewModel by viewModel()
    var adapter: MainListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.fetchList()
        startObserver()
    }

    private fun startObserver() {
        viewmodel.getPokeList().observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.SUCCESS -> {
                    dataState.data?.let {
                        it.pokemonList?.let { safePokeList ->
                            initView(safePokeList)
                        }
                    }
                }
                is DataState.ERROR -> {

                }
                is DataState.LOADING -> {

                }
            }
        })
    }

    private fun initView(pokeList: List<Pokemon>) {
        adapter = MainListAdapter(pokeList) { name, url ->
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                    name,
                    url
                )
            mView?.let { Navigation.findNavController(it).navigate(action) }

        }
        mView?.findViewById<RecyclerView>(R.id.rvPokemonList)?.adapter = adapter
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