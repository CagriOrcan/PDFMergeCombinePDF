import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
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
        if(BuildConfig.DEBUG){
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

    fun showAd() {
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
            Log.d("AdManager", "Ad wasn't ready yet")
        }
    }

}
