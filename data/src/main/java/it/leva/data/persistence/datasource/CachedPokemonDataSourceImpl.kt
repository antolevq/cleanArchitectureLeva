package it.leva.data.persistence.datasource

import it.leva.data.persistence.dao.PokeDao
import it.leva.data.persistence.dao.SpritesAndImagesDao
import it.leva.data.persistence.dao.StatDao
import it.leva.data.persistence.dao.TypeDao
import it.leva.data.persistence.entity.*
import it.leva.data.persistence.mapper.PokeEntityMapper

class CachedPokemonDataSourceImpl(
    private val pokeDao: PokeDao,
    private val spritesAndImagesDao: SpritesAndImagesDao,
    private val statDao: StatDao,
    private val typeDao: TypeDao,
) : CachedPokemonDataSource {
    override suspend fun insertAllStats(statsList: List<StatEntity>) {
        statsList.firstOrNull()?.let { statEntity ->
            statEntity.fkPoke?.let {
                statDao.deleteRelationedStat(it)
            }
        }
        statDao.insertAll(statsList)
    }

    override suspend fun insertAllTypes(typeList: List<TypeEntity>) {
        typeList.firstOrNull()?.let { typeEntity ->
            typeEntity.fkPoke?.let {
                typeDao.deleteRelationedType(it)
            }
        }
        typeDao.insertAll(typeList)
    }

    override suspend fun insertSpritesAndImage(listOfImages: SpritesAndImagesEntity) {
        listOfImages?.let { listEntity ->
            listEntity.fkPoke?.let {
                spritesAndImagesDao.deleteRelationedImage(it)
            }
        }
        spritesAndImagesDao.insert(listOfImages)
    }

    override suspend fun insertPokemon(pokeEntity: PokeEntity): Long {
        return pokeDao.insertPokemon(pokeEntity)
    }

    override suspend fun insertAllPokemon(pokeEntityList: List<PokeEntity>) {
        return pokeDao.insertAll(pokeEntityList = pokeEntityList)
    }

    override fun deletePokemon(pokeEntity: PokeEntity) {
        pokeDao.delete(pokeEntity)
    }

    override suspend fun getAllPokemon(idList: List<String>): List<PokemonWithRelationsEntity> {
        return pokeDao.getAllPokemon(idList)
    }

    override suspend fun getAllPokemon(): List<PokemonWithRelationsEntity> {
        return pokeDao.getAllPokemon()
    }

    override suspend fun getPokemon(name: String): PokemonWithRelationsEntity? {
        return pokeDao.getPokemon(name)
    }

    override fun setPokemonAsCached(name: String) {
        pokeDao.setPokemonAsChecked(name)
    }
}