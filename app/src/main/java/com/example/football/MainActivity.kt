package com.example.football

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.football.model.Position
import com.example.football.ui.theme.FootballTheme
import com.example.football.ui.FootballViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import com.example.football.model.Player
import com.example.football.utils.FootballContentType

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootballTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
                        .padding(
                            start = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(layoutDirection)
                        )
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    FootballApp(
                        windowSize = windowSize.widthSizeClass,
                        onBackPressed = { finish() }
                    )
                }
            }
        }
    }
}

@Composable
fun FootballApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit
) {
    val viewModel: FootballViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> FootballContentType.ListOnly
        WindowWidthSizeClass.Expanded -> FootballContentType.ListsAndDetail
        else -> FootballContentType.ListOnly
    }

    Scaffold(
        topBar = {
            FootballAppBar(
                contentType = contentType,
                isShowingListPage = uiState.isShowingListPage,
                isPositionsListPage = uiState.isPositionsListPage,
                onBackButtonClick = { viewModel.navigateToPreviousPage(uiState.isShowingListPage, uiState.isPositionsListPage) },
            )
        }
    ) { innerPadding ->
        if (contentType == FootballContentType.ListOnly) {
            if (uiState.isShowingListPage && uiState.isPositionsListPage) {
                PositionsList(
                    positions = uiState.positionsList,
                    onClick = {
                        viewModel.updateCurrentPosition(it)
                        viewModel.navigateToPlayersListPage(it)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding
                )
            } else if (uiState.isShowingListPage && !uiState.isPositionsListPage) {
                PlayersList(
                    players = uiState.playersList,
                    onClick = {
                        viewModel.updateCurrentPlayer(it)
                        viewModel.navigateToDetailPage(it)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding
                )
            } else {
                PlayerInfo(
                    selectedPlayer = uiState.currentPlayer,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToPreviousPage(uiState.isShowingListPage, uiState.isPositionsListPage)
                    }
                )
            }
        } else if (contentType == FootballContentType.ListsAndDetail) {
            Row(modifier = Modifier.fillMaxWidth()) {
                PositionsList(
                    positions = uiState.positionsList,
                    onClick = {
                        viewModel.updateCurrentPosition(it)
                        viewModel.navigateToPlayersListPage(it)
                        viewModel.updateCurrentPlayerForDetail(it)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)).weight(2f),
                    contentPadding = innerPadding
                )
                PlayersList(
                    players = uiState.playersList,
                    onClick = {
                        viewModel.updateCurrentPlayer(it)
                        viewModel.navigateToDetailPage(it)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)).weight(2f),
                    contentPadding = innerPadding
                )
                PlayerInfo(
                    selectedPlayer = uiState.currentPlayer,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToPreviousPage(uiState.isShowingListPage, uiState.isPositionsListPage)
                    },
                    modifier = Modifier.weight(3f)
                )
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballAppBar(
    contentType: FootballContentType,
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    isPositionsListPage: Boolean,
    modifier: Modifier = Modifier
) {
    if (contentType != FootballContentType.ListsAndDetail) {
        TopAppBar(
            title = {
                Text(
                    text =
                    if (isPositionsListPage) {
                        stringResource(R.string.app_name)
                    } else if (isShowingListPage) {
                        stringResource(R.string.players_list_fragment_label)
                    } else {
                        stringResource(R.string.detail_fragment_label)
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = if (!isPositionsListPage) {
                {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            } else {
                { Box {} }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        )
    } else {
        TopAppBar(
            title = { Text(text = stringResource(R.string.app_name)) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        )
    }
}

@Composable
fun PositionsList(
    positions: List<Position>,
    onClick: (Position) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(positions, key = { position -> position.id }) { position ->
            PositionsListItem(
                position = position,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PositionsListItem(
    position: Position,
    onItemClick: (Position) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(position) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            PositionsListImageItem(
                position = position,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(position.titleResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(position.positionDetailsId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun PositionsListImageItem(position: Position, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(position.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun PlayersList(
    players: List<Player>,
    onClick: (Player) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(players, key = { player -> player.id }) { player ->
            PlayersListItem(
                player = player,
                onItemClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayersListItem(
    player: Player,
    onItemClick: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(player) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            PlayersListImageItem(
                player = player,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
                    // Следующие 2 строчки сам добавил, хотел текст посередине
                    //.fillMaxHeight()
                    //.wrapContentHeight(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(player.nameId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(player.shortInfoId),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun PlayersListImageItem(
    player: Player,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(player.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun PlayerInfo(
    selectedPlayer: Player,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    Image(
                        painter = painterResource(selectedPlayer.posterResourceId),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = stringResource(selectedPlayer.nameId),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
            Text(
                text = stringResource(R.string.honours),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = stringResource(selectedPlayer.honoursId),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    //vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FootballTheme {
        Greeting("Android")
    }
}