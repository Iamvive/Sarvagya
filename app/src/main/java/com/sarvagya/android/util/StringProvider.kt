package com.sarvagya.android.util

import android.content.Context
import javax.inject.Inject

class StringProvider
@Inject
constructor(
    private val context: Context
) {
    operator fun invoke(id: Int) =
        context.resources.getString(id)
}