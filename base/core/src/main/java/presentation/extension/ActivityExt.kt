package presentation.extension

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import org.ucarsu.coronaexample.core.R

fun Activity.createBlockingPane(isCancelable: Boolean = false): Dialog = Dialog(this).apply {
    setCancelable(isCancelable)
    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    setContentView(R.layout.dialog_progress)
}
