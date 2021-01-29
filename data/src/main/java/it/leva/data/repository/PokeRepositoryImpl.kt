package it.leva.data.repository

import androidx.paging.PageKeyedDataSource
import it.leva.data.network.dto.BaseResponse
import it.leva.data.network.dto.PokeDetailDTO
import it.leva.data.network.dto.PokeItemDTO
import it.leva.data.network.services.PokeService
import it.leva.data.persistence.datasource.CachedPokemonDataSource
import it.leva.data.persistence.mapper.*
import it.leva.domain.model.Pokemon
import it.leva.domain.repository.PokeRepository
import it.leva.domain.state.DataState
import it.leva.domain.viewstate.PokemonListViewState
import it.leva.domain.viewstate.PokemonViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PokeRepositoryImpl(
    private val pokeService: PokeService,
    private val cachedPokemonDataSource: CachedPokemonDataSource,
    private val pokeEntityMapper: PokeEntityMapper,
    private val pokemonWithRelationsEntityMapper: PokemonWithRelationsEntityMapper,
    private val statEntityEntityMapper: StatEntityEntityMapper,
    private val spritesAndImagesEntityMapper: SpritesAndImagesEntityMapper,
    private val typeEntityEntityMapper: TypeEntityEntityMapper
) : PokeRepository, PageKeyedDataSource<String, Pokemon>() {
    override fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<DataState<PokemonListViewState>> {
        return flow {

            val pokeRemoteList = pokeService.getPokeList(limit = limit, offset = offset)
            var nextPage: String? = null
            if (pokeRemoteList.pokeListDTO.isNullOrEmpty().not()) {
                nextPage = pokeRemoteList.nextPage
                insertIntoDatabase(pokeRemoteList.pokeListDTO)
            }
            emit(
                DataState.SUCCESS(
                    PokemonListViewState(
                        pokemonWithRelationsEntityMapper
                            .mapToDomain(
                                if (pokeRemoteList.pokeListDTO.isNullOrEmpty().not()) {
                                    cachedPokemonDataSource.getAllPokemon(pokeRemoteList.pokeListDTO.map { it.name })
                                } else {
                                    cachedPokemonDataSource.getAllPokemon()
                                }

                            ),
                        nextPage
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getNextPokemon(nextUrl: String): Flow<DataState<PokemonListViewState>> {
        return flow {

            val pokeRemoteList = pokeService.getPokeListFromUrl(nextUrl)
            var nextPage: String? = null
            if (pokeRemoteList.pokeListDTO.isNullOrEmpty().not()) {
                nextPage = pokeRemoteList.nextPage
                insertIntoDatabase(pokeRemoteList.pokeListDTO)
            }
            emit(
                DataState.SUCCESS(
                    PokemonListViewState(
                        pokemonWithRelationsEntityMapper
                            .mapToDomain(
                                if (pokeRemoteList.pokeListDTO.isNullOrEmpty().not()) {
                                    cachedPokemonDataSource.getAllPokemon(pokeRemoteList.pokeListDTO.map { it.name })
                                } else {
                                    cachedPokemonDataSource.getAllPokemon()
                                }

                            ),
                        nextPage
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getPokemonDetail(name: String, url: String): Flow<DataState<PokemonViewState>> {
        return flow {
            val pokemonDto = pokeService.getPokeDetail(url)
            insertIntoDatabase(pokemonDto, url)
            emit(
                DataState.SUCCESS(
                    PokemonViewState(
                        pokemonWithRelationsEntityMapper
                            .mapToDomain(
                                cachedPokemonDataSource.getPokemon(name)
                            )
                    )
                )
            )
        }.flowOn(Dispatchers.IO)
    }


    private suspend fun insertIntoDatabase(poke: BaseResponse, url: String) {
        if (poke is PokeDetailDTO) {
            poke.url = url
            val pEntity = pokeEntityMapper.mapToEntity(poke)
            val statsEntity =
                statEntityEntityMapper.mapToEntity(poke.statDTOS)
            statsEntity.forEach { it.fkPoke = poke.name }
            val typesEntity =
                typeEntityEntityMapper.mapToEntity(poke.typeDTOS)
            typesEntity.forEach { it.fkPoke = poke.name }

            val spritesEntity = spritesAndImagesEntityMapper.mapToEntity(poke.spriteAdImages)
            spritesEntity.fkPoke = poke.name

            cachedPokemonDataSource.insertPokemon(pEntity)
            cachedPokemonDataSource.insertAllStats(statsEntity)
            cachedPokemonDataSource.insertAllTypes(typesEntity)
            cachedPokemonDataSource.insertSpritesAndImage(spritesEntity)
        }
    }

    private suspend fun insertIntoDatabase(list: List<BaseResponse>) {
        cachedPokemonDataSource.insertAllPokemon(pokeEntityMapper.mapToEntity(list))
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Pokemon>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Pokemon>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Pokemon>
    ) {
        TODO("Not yet implemented")
    }


}