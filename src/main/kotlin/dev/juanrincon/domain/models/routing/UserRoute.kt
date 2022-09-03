package dev.juanrincon.domain.models.routing

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/users")
class UserRoute {
    @Serializable
    @Resource("login")
    class Login(val parent: UserRoute = UserRoute())

    @Serializable
    @Resource("register")
    class Register(val parent: UserRoute = UserRoute())
}