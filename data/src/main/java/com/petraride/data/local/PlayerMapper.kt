package com.petraride.data.local

import com.petraride.data.local.entity.PlayerEntity
import com.petraride.domain.model.PlayerLocal

fun PlayerEntity.toDomain(): PlayerLocal = PlayerLocal(id, firstName, lastName,teamName,isFavorite)
fun PlayerLocal.toEntity(): PlayerEntity = PlayerEntity(id, firstName, lastName,teamName,isFavorite)