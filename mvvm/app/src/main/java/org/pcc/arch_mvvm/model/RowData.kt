package org.pcc.arch_mvvm.model

data class RowData(
    val full_name: String,
    val description: String,
    val forks_count: Int,
    val stargazers_count: Int,
    val open_issues_count: Int,
    val owner: Owner)

data class Owner(
    val avatar_url: String,
    val html_url: String
)