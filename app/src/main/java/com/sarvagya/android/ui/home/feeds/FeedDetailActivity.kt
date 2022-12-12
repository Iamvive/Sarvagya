package com.sarvagya.android.ui.home.feeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.sarvagya.android.R
import com.sarvagya.android.databinding.ActivityFeedDetailBinding
import com.sarvagya.android.databinding.ActivityHomeBinding
import com.sarvagya.android.extension.loadImage

class FeedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedDetailBinding
    private val activity : FeedDetailActivity  = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting back icon
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)

        //back to home
        binding.toolbar.setNavigationOnClickListener {
            activity.finish()
        }

        //set title
        binding.feedTitleTV.text = "हिंदू शास्त्रों के अनुसार, भगवान गणेश की पूजा सबसे पहले की जाती है...."

        //set description
        binding.feedDescTV.text = "हिंदू धर्म में कुछ ऐसे भी व्रत होते हैं जो प्रत्येक माह किए जाते हैं. " +
                "इन्हीं में से एक व्रत है गणेश चतुर्थी का व्रत. भगवान गणेश को समर्पित यह व्रत इस बार मार्गशीर्ष की " +
                "शुक्ल पक्ष की चतुर्दशी को रखा जाएगा. भगवान गणेश की कृपा प्राप्त करने के लिए इस दिन का पूजन बेहद " +
                "महत्वपूर्ण और शुभ माना जाता है. जो कोई भी व्यक्ति सच्ची श्रद्धा के साथ विनायक चतुर्थी का व्रत करता है, " +
                "भगवान गणेश उसे ज्ञान, समृद्धि, सौभाग्य, बुद्धि आदि का आशीर्वाद प्रदान करते हैं. इस बार मार्गशीर्ष विनायक " +
                "चतुर्थी 27 नवंबर 2022 यानी आज के दिन मनाई जा रही है.\\n\\nहिंदू शास्त्रों के अनुसार, भगवान गणेश की प" +
                "ूजा सबसे पहले की जाती है. भगवान गणेश को विघ्नहर्ता के नाम से भी जाना जाता है. विनायक चतुर्थी सभी हानिकारक" +
                " प्रभावों को दूर करने के लिए मनाई जाती है. भगवान गणेश को मोदक, लड्डू, वस्त्र और अन्य मिठाई का भोग " +
                "लगाना चाहिए. ऐसा माना जाता है कि जो लोग इस व्रत को बड़ी भक्ति और समर्पण के साथ करते हैं, भगवान गणेश " +
                "उन्हें सुख, समृद्धि, सौभाग्य और धन का आशीर्वाद देते हैं. भगवान गणेश भक्तों के जीवन से सभी बुरे प्रभावों को भी दूर करते हैं." +
                " यह भी माना जाता है कि जिन लोगों की संतान नहीं है, उन्हें भी इस दिन उपवास रखना चाहिए. \\n\\nहिंदू पंचांग के अनुसार " +
                "मार्गशीर्ष माह की विनायक चतुर्थी 26 नवंबर 2022 शनिवार को शाम 07 बजकर 28 मिनट पर शुरू हो चुकी है और इसका समापन " +
                "27 नवंबर 2022 यानी आज " +
                "शाम 04 बजकर 25 मिनट पर होगा. उदयातिथि के अनुसार मार्गशीर्ष की विनायक चतुर्थी व्रत 27 नवंबर को ही मनाई जा रही है."

        //default image
        binding.feedIV.loadImage("https://sarvagya.blob.core.windows.net/images/7ea4b12e-b9f5-46b5-a870-adde9cae85b2.jpeg")

    }
}