package com.adamkobus.compose.navigation.demo.ui.appbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.adamkobus.compose.navigation.demo.ui.R

data class AnimatedAppBarState(
    val titleState: AppBarTitleState? = null,
    val iconState: AppBarIconState? = null,
    val actionsState: List<AppBarActionState> = emptyList(),
    val searchState: AppBarSearchState = AppBarSearchState()
) {
    val isAnyContentAvailable: Boolean
        get() = titleState != null || iconState != null || actionsState.isNotEmpty() || searchState.isSearchEnabled
}

data class AppBarTitleState(
    @StringRes private val titleResId: Int? = null,
    private val title: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return other is AppBarTitleState && other.titleResId == titleResId && other.title == title
    }

    @Composable
    fun getText(): String? {
        return title ?: titleResId?.let { stringResource(id = it) }
    }

    override fun hashCode(): Int {
        var result = titleResId ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }
}

class AppBarIconState(
    val icon: ImageVector,
    @StringRes val contentDescriptionResId: Int?,
    val onClick: (() -> Unit) = {},
    val iconId: Int = nextIconId,
) {
    override fun equals(other: Any?): Boolean {
        return other is AppBarIconState && other.iconId == iconId
    }

    override fun hashCode(): Int {
        return iconId
    }

    companion object {
        private var _nextIconId = 0
        val nextIconId: Int
            get() {
                _nextIconId++
                return _nextIconId
            }

        fun back(onClick: () -> Unit): AppBarIconState =
            AppBarIconState(icon = Icons.Filled.ArrowBack, onClick = onClick, contentDescriptionResId = R.string.accessibility_back)
    }
}

class AppBarActionState(
    val actionId: Int = nextActionId,
    val icon: ImageVector,
    @StringRes val contentDescriptionResId: Int,
    val onClick: (() -> Unit)
) {

    override fun equals(other: Any?): Boolean {
        return other is AppBarActionState && other.actionId == actionId
    }

    override fun hashCode(): Int {
        return actionId
    }

    companion object {
        private var _nextActionId = 0
        val nextActionId: Int
            get() {
                _nextActionId++
                return _nextActionId
            }

        fun settings(onClick: () -> Unit): AppBarActionState =
            AppBarActionState(icon = Icons.Filled.Settings, onClick = onClick, contentDescriptionResId = R.string.accessibility_settings)
    }
}

data class AppBarSearchState(
    val isSearchEnabled: Boolean = false,
    val currentText: String = "",
    val onQueryChanged: ((String) -> Unit)? = null
)
