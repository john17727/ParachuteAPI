package dev.juanrincon.domain.daos

import dev.juanrincon.domain.interfaces.utilities.DomainMapper
import dev.juanrincon.domain.models.Note
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class NoteEntity(id: EntityID<Int>): IntEntity(id), DomainMapper<Note> {
    companion object : IntEntityClass<NoteEntity>(NoteTable)
    var title by NoteTable.title
    var markdown by NoteTable.markdown
    var html by NoteTable.html
    var type by NoteTypeEntity referencedOn NoteTable.typeId
    var favorite by NoteTable.favorite
    var fleeting by NoteTable.fleeting
    var project by ProjectEntity optionalReferencedOn  NoteTable.projectId
    var resource by ResourceEntity optionalReferencedOn NoteTable.resourceId
    var area by AreaEntity optionalReferencedOn NoteTable.areaId
    var user by UserEntity referencedOn NoteTable.userId

    override fun toModel() = Note(
        id.value,
        title,
        markdown,
        html,
        type.id.value,
        favorite,
        fleeting,
        project?.id?.value,
        resource?.id?.value,
        area?.id?.value
    )
}