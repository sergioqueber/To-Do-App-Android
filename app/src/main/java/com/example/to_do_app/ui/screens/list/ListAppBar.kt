@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.to_do_app.ui.screens.list

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_app.R
import com.example.to_do_app.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.to_do_app.components.PriorityItem
import com.example.to_do_app.ui.theme.LARGE_PADDING
import com.example.to_do_app.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.to_do_app.ui.util.SearchAppBarState
import com.example.to_do_app.ui.util.TrailingIconState
import com.example.to_do_app.ui.viewmodels.ShareViewModel


@Composable
fun ListAppBar(
    shareViewModel: ShareViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
)
{
    when(searchAppBarState){
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    shareViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteClicked = {}
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    shareViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    shareViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    shareViewModel.searchTextState.value = ""
                },
                onSearchClicked = {}
            )
        }
    }

}

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
)
{
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked)

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )

    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
){
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
){
    IconButton(
        onClick = {onSearchClicked()}
    ){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = {expanded =  true}
    )
    {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
                text = {
                    PriorityItem(priority = Priority.LOW)
                }
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.MEDIUM)
                },
                text = {
                    PriorityItem(priority = Priority.MEDIUM)
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                text = {
                    PriorityItem(priority = Priority.HIGH)
                }
            )
        }
    }
}
@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit

) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = { expanded = true }
    )
    {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteClicked()
                },
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(id = R.string.delete_all_action),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_DELETE) }

    TopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = { newText ->
                    onTextChange(newText)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(0.5f),
                        text = "Search",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    Icon( // No need for IconButton here
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        },
        actions = {
            IconButton(onClick = {
                when (trailingIconState) {
                    TrailingIconState.READY_TO_DELETE -> {
                        if (text.isEmpty()) {
                            onCloseClicked()
                        }
                        onTextChange("")
                        trailingIconState = TrailingIconState.READY_TO_CLOSE
                    }
                    TrailingIconState.READY_TO_CLOSE -> {
                        if(text.isNotEmpty()){
                            onTextChange("")
                        }else{
                            onCloseClicked()
                            trailingIconState = TrailingIconState.READY_TO_DELETE
                        }
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
@Preview
private fun DefaultListAppBarPreview()
{
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

@Composable
@Preview
private fun SearchAppBarPreview(){
    SearchAppBar(
        text = "Search",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}