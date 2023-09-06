package co.torpido.pdfcombine.mergepdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import co.torpido.pdfcombine.mergepdf.databinding.ActivityMainBinding
import co.torpido.pdfcombine.mergepdf.presentation.base.PdfPickerActivity
import co.torpido.pdfcombine.mergepdf.utils.PdfMergeTool
import com.wada811.databindingktx.dataBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : PdfPickerActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by dataBinding()

    @Inject
    lateinit var pdfMergeTool: PdfMergeTool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener() = with(binding) {
        button.setOnClickListener {
            openFilePicker()
        }
        setSuccessListener { b, uris ->
            pdfMergeTool.mergePDFs(uris)
        }
        pdfMergeTool.setSuccessListener { isSuccess, errorMessage ->
            if(isSuccess){
                Toast.makeText(this@MainActivity, "Download dizinine kayıt edilerek birleştirme tamamlandı", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@MainActivity, "HATA: $errorMessage", Toast.LENGTH_SHORT).show()

            }

        }

    }


}