package navigation

import android.content.Intent

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrReturnNull(): Intent? =
    try {
        Class.forName(this).run { intentTo((this@loadIntentOrReturnNull)) }
    } catch (e: ClassNotFoundException) {
        null
    }