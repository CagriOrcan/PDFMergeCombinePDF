import android.app.Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.wada811.databindingktx.BuildConfig

/**
 * Change Ad Id for needs of usage
 */


const val adIdInterstitial = "ca-app-pub-4425985446742140/6293550809"
const val adIdTest = "ca-app-pub-3940256099942544/1033173712"
const val adIdBannerTest = "ca-app-pub-3940256099942544/6300978111"

class AdManager(private val activity: Activity) {

    private var interstitialAd: InterstitialAd? = null
    private var adId: String? = ""

    init {
        if(BuildConfig.DEBUG) {
            adId = adIdTest
        } else {
            adId = adIdInterstitial
        }

        MobileAds.initialize(activity) {
            Log.d("AdManager", "AdMob SDK initialized")
        }
        loadInterstitialAd()
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        adId?.let {
            InterstitialAd.load(activity, it, adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    Log.d("AdManager", "Interstitial ad loaded")
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("AdManager", "Failed to load interstitial ad: ${adError.message}")
                    interstitialAd = null
                }
            })
        }
    }

    fun showInterstitialAd() {
        interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d("AdManager", "Ad dismissed")
                loadInterstitialAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                Log.d("AdManager", "Ad failed to show")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d("AdManager", "Ad showed fullscreen content.")
                interstitialAd = null
            }
        }

        if (interstitialAd != null) {
            interstitialAd?.show(activity)
        } else {
            Log.d("AdManager", "Interstitial Ad wasn't ready yet")
        }
    }

    @Composable
    fun bannersAds() {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color(0xFAFAFA))
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Google Admob Banner Ads in Android",
                textAlign = TextAlign.Center,
                color = Color(0xFAFAFA),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "ca-app-pub-4425985446742140/2375400795"
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }

    @Composable
    fun AdaptiveBanner(
        modifier: Modifier = Modifier,
    ) {
        val applicationContext = LocalContext.current.applicationContext

        Column(
            modifier = modifier.fillMaxWidth(),
        ) {
            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = {
                    // Using application context to avoid memory leak
                    AdView(applicationContext).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "ca-app-pub-3940256099942544/6300978111"
                        loadAd(AdRequest.Builder().build())
                    }
                },
            )
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

}

/*@Composable
fun bannersAds(context: Context) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(20.dp))
        // on below line we are adding a text
        Text(
            // on below line specifying text for heading.
            text = "Google Admob Banner Ads in Android",
            // adding text alignment,
            textAlign = TextAlign.Center,
            // on below line adding text color.
            color = Color(0xFFFAFAFA),
            // on below line adding font weight.
            fontWeight = FontWeight.Bold,
            // on below line adding padding from all sides.
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(30.dp))

        // on below line adding admob banner ads.
        AndroidView(
            // on below line specifying width for ads.
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                // on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    setAdSize(AdSize.BANNER)
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    // calling load ad to load our ad.
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }

 */

