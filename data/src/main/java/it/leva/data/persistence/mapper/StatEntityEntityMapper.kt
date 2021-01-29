package it.leva.data.persistence.mapper

import it.leva.data.network.dto.StatDTO
import it.leva.data.persistence.entity.StatEntity
import it.leva.domain.model.Stat

class StatEntityEntityMapper {


    fun mapToEntity(from: List<StatDTO>) =
        from.map { mapToEntity(it) }

    fun mapToDomain(from: List<StatEntity>?): List<Stat>? {
        from?.let {
            return it.map { mapToDomain(it) }
        }
        return null
    }


    private fun mapToEntity(from: StatDTO) =
        StatEntity(
            pk = null,
            effort = from.effort,
            baseStat = from.baseStat,
            name = from.statInfo.statName,
            url = from.statInfo.statUrl,
            fkPoke = ""
        )

    private fun mapToDomain(from: StatEntity) =
        Stat(
            id = from.pk ?: 0,
            effort = from.effort ?: 0,
            name = from.name ?: "",
            url = from.url ?: "",
            fkPoke = from.fkPoke ?: "",
            baseStat = from.baseStat ?: 0
        )

}