package it.leva.data.persistence.mapper

import it.leva.data.network.dto.TypeDTO
import it.leva.data.persistence.entity.TypeEntity
import it.leva.domain.model.Type


class TypeEntityEntityMapper {

    fun mapToEntity(from: List<TypeDTO>) =
        from.map { mapToEntity(it) }

    fun mapToDomain(from: List<TypeEntity>?) : List<Type>? {
        from?.let{
            return it.map { mapToDomain(it) }
        }
        return null
    }


    private fun mapToEntity(from: TypeDTO) =
        TypeEntity(
            pk = null,
            slot = from.slot,
            name = from.typeInfo.typeName,
            url = from.typeInfo.typeUrl,
            fkPoke = ""
        )

    private fun mapToDomain(from: TypeEntity) =
        Type(
            id = from.pk ?: 0,
            slot = from.slot ?: 0,
            name = from.name ?: "",
            url = from.url ?: "",
            fkPoke = from.fkPoke ?: ""
        )
}