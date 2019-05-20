package org.pcc.arch_mvvm.model.api

import org.pcc.arch_mvvm.model.RowData

data class ApiResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RowData>
)