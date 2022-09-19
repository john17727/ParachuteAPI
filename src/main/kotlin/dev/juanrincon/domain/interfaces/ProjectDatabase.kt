package dev.juanrincon.domain.interfaces

import dev.juanrincon.domain.interfaces.utilities.MutableRepository
import dev.juanrincon.domain.models.Project
import dev.juanrincon.domain.models.request.ProjectRequest

interface ProjectDatabase: MutableRepository<ProjectRequest, Project> {

    suspend fun getByUser(userId: Int): List<Project>
}