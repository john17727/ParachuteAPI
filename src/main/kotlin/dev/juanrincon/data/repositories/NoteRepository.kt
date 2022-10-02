package dev.juanrincon.data.repositories

import dev.juanrincon.domain.daos.*
import dev.juanrincon.domain.interfaces.NotesDatabase
import dev.juanrincon.domain.models.request.NoteRequest
import dev.juanrincon.plugins.dbQuery

class NoteRepository : NotesDatabase {
    override suspend fun getByArea(areaId: Int, userId: Int) = dbQuery {
        if (areaId < 1) {
            NoteEntity.find { NoteTable.userId eq userId }.filter { it.area == null }.map { it.toModel() }
        } else {
            NoteEntity.find { NoteTable.areaId eq areaId }.map { it.toModel() }
        }
    }

    override suspend fun getByProject(projectId: Int, userId: Int) = dbQuery {
        if (projectId < 1) {
            NoteEntity.find { NoteTable.userId eq userId }.filter { it.project == null }.map { it.toModel() }
        } else {
            NoteEntity.find { NoteTable.projectId eq projectId }.map { it.toModel() }
        }
    }

    override suspend fun getByResource(resourceId: Int, userId: Int) = dbQuery {
        if (resourceId < 1) {
            NoteEntity.find { NoteTable.userId eq userId }.filter { it.resource == null }.map { it.toModel() }
        } else {
            NoteEntity.find { NoteTable.resourceId eq resourceId }.map { it.toModel() }
        }
    }

    override suspend fun archive(id: Int) = dbQuery {
        NoteEntity.findById(id)?.let { note ->
            ArchivedNoteEntity.new {
                noteId = note.id.value
                title = note.title
                markdown = note.markdown
                html = note.html
                type = note.type
                favorite = note.favorite
                fleeting = note.fleeting
                project = note.project
                resource = note.resource
                area = note.area
                user = note.user
            }

            note.delete()
            true
        } ?: false
    }

    override suspend fun delete(id: Int) = dbQuery {
        NoteEntity.findById(id)?.let {
            it.delete()
            true
        } ?: false
    }

    override suspend fun add(entry: NoteRequest) = dbQuery {
        NoteEntity.new {
            title = entry.title
            markdown = entry.markdown
            html = entry.html
            type = getType(entry.type)
            favorite = entry.favorite
            fleeting = entry.fleeting
            project = getProject(entry.projectId)
            resource = getResource(entry.resourceId)
            area = getArea(entry.areaId)
            user = getUser(entry.userId)
        }.toModel()
    }

    override suspend fun update(id: Int, entry: NoteRequest) = dbQuery {
        NoteEntity.findById(id)?.let {
            it.title = entry.title
            it.markdown = entry.markdown
            it.html = entry.html
            it.type = getType(entry.type)
            it.favorite = entry.favorite
            it.fleeting = entry.fleeting
            it.project = getProject(entry.projectId)
            it.resource = getResource(entry.resourceId)
            it.area = getArea(entry.areaId)
            it.toModel()
        }
    }

    override suspend fun getByUser(userId: Int) = dbQuery {
        NoteEntity.find { NoteTable.userId eq userId }.map { it.toModel() }
    }

    private fun getArea(id: Int?) = id?.let {
        AreaEntity.findById(it)
    }

    private fun getProject(id: Int?) = id?.let {
        ProjectEntity.findById(it)
    }

    private fun getResource(id: Int?) = id?.let {
        ResourceEntity.findById(it)
    }

    private fun getType(id: Int) = NoteTypeEntity.findById(id)!!

    private fun getUser(id: Int) = UserEntity.findById(id)!!
}