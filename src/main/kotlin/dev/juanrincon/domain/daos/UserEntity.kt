package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<User> {
    companion object : IntEntityClass<UserEntity>(UserTable)
    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var imageUrl by UserTable.imageUrl
    var email by UserTable.email
    var hash by UserTable.hash
    var salt by UserTable.salt
    override fun toModel() = User(
        id.value,
        firstName,
        lastName,
        imageUrl,
        email
    )
}