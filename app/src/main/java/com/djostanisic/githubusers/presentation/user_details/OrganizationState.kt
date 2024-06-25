package com.djostanisic.githubusers.presentation.user_details

import com.djostanisic.githubusers.domain.model.Organization

data class OrganizationState (
    val isLoading: Boolean = false,
    val organizations: List<Organization?>? = null,
    val error: String = ""
)