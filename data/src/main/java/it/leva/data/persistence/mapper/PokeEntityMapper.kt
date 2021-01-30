package it.leva.data.persistence.mapper

import it.leva.data.network.dto.BaseResponse
import it.leva.data.persistence.entity.PokeEntity

class PokeEntityMapper {

    fun mapToEntity(from: List<BaseResponse>) =
        from.map { mapToEntity(it) }

    fun mapToEntity(from: BaseResponse) =
        PokeEntity(
            name = from.name,
            url = from.url,
            cached = false
        )
}