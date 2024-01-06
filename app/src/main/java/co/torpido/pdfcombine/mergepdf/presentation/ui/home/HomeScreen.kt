package co.torpido.pdfcombine.mergepdf.presentation.ui.home

import AdManager
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import co.torpido.pdfcombine.mergepdf.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    addPDF: () -> Unit,
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(0xFF8EA7E9),
                fontSize = 58.24.sp,
                fontFamily = FontFamily(Font(R.font.abril)),
                fontWeight = FontWeight(400)
            )
        ) {
            append(stringResource(id = R.string.pdf))
        }
        withStyle(
            style = SpanStyle(
                color = Color(0xFF8EA7E9),
                fontSize = 24.sp,
                fontWeight = FontWeight(300)
            )
        ) {
            append(stringResource(id = R.string.merge))
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFE3F4F4))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 0.dp)
                )
        ) {
            Text(
                text = annotatedString,
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 0.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.ic_add_medium),
                contentDescription = stringResource(
                    id = R.string.add_button_content_description
                ),
                contentScale = ContentScale.None,
                modifier = modifier
                    .padding(top = 32.dp, end = 16.dp)
                    .clickable(onClick = addPDF),
                colorFilter = ColorFilter.tint(Color(0xFF8EA7E9))

            )
        }
       // AdvertView()
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(
                id = R.drawable.pdf_icon
            ),
            contentDescription = stringResource(
                id = R.string.pdf_icon_content_description
            ),
            modifier = modifier
                .width(109.dp)
                .height(109.dp),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(Color(0xFF8EA7E9))
        )

        Text(
            text = "You don’t have any PDF Documents",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(700),
                color = Color(0xFF8EA7E9),
            ),
            modifier = modifier.padding(top = 1.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.underline),
            contentDescription = "image description",
            modifier = modifier
                .padding(top = 5.dp, end = 31.dp)
                .width(134.dp)
                .height(9.dp)
                .align(Alignment.End),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(Color(0xFF8EA7E9))
        )
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "image description",
            modifier = modifier
                .padding(top = 5.dp, end = 50.dp)
                .width(112.32.dp)
                .align(Alignment.End),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(Color(0xFF8EA7E9))
        )
        Box(
            modifier = modifier
                .width(65.dp)
                .height(65.dp)
                .border(width = 5.dp, color = Color(0xE5FFFFFF), shape = CircleShape)
                .background(color = Color(0xFF8EA7E9), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add button",
                modifier = modifier
                    .clickable(onClick = addPDF),
                contentScale = ContentScale.None
            )
        }
        //AdvertView()
        bannersAds()

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(addPDF = {})
    }
}

@Composable
fun AdvertView(modifier: Modifier = Modifier) {
    val isInEditMode = LocalInspectionMode.current
    if (isInEditMode) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(horizontal = 2.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            text = "Advert Here",
        )
    } else {
        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}

@Composable
fun bannersAds() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color(0xFFE3F4F4))
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Spacer(modifier = Modifier.height(30.dp))
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}
