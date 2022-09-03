package dev.juanrincon.domain.daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(UserTable)
    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var imageUrl by UserTable.imageUrl
    var email by UserTable.email
    var password by UserTable.password
}