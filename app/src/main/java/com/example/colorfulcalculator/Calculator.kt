package com.example.colorfulcalculator

import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlin.random.Random

val buttonList = listOf(
    "C","(",")","/",
    "7","8","9","*",
    "4","5","6","-",
    "1","2","3","+",
    "AC","0",".","="
)




@Composable
fun Calculator(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()


    Box(modifier =modifier ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = equationText.value?:"",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = resultText.value?:"",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )
            
            Spacer(modifier = Modifier.height(20.dp))


            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(buttonList) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    })
                }
            }

        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
//        This floationg button gives an elevation , that's looks very cool
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .size(80.dp),
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(
                text = btn,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


fun getColor(btn: String): Color {
    val blue = Color(red = 135, green = 162, blue = 255)
    val peach = Color(red = 255, green = 215, blue = 196)
    val yellow = Color(red = 255, green = 244, blue = 181)

    if (btn == "C" || btn == "AC") {
        return Color.Red
    }else if (btn == "+" || btn == "-" || btn == "/" || btn == "*") {
        return Color.Green
    }
    else{
        return Color.DarkGray
    }

}




//@Preview(showSystemUi = true)
//@Composable
//fun CalculatorPreview(modifier: Modifier = Modifier) {
//    Calculator(
//        viewModel: CalculatorViewModel)
//}