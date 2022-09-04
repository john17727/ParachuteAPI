package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/area")
class AreaRoute {

    @Serializable
    @Resource("{id}")
    class Id(val parent: AreaRoute = AreaRoute(), val id: Int)
}