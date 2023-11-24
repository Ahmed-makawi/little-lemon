package com.ahmed3.littlelemonCapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ahmed3.littlelemonCapstone.navigation.Navigation
import com.ahmed3.littlelemonCapstone.ui.theme.LittleLemonTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                Navigation()
            }
        }
    }
}
