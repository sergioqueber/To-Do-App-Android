package com.example.to_do_app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_app.navigation.SetUpNavigation
import com.example.to_do_app.ui.theme.ToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.to_do_app.ui.viewmodels.ShareViewModel
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    private val shareViewModel: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {
                navController = rememberNavController()
                SetUpNavigation(
                    navController = navController,
                    shareViewModel = shareViewModel)
            }
        }
    }
}