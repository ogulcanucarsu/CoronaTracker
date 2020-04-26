/*
 * App configuration
 */
object Config {
    val applicationId = "org.ucarsu.coronaexample"
    val minSdkVersion = SdkVersions.minSdkVersion
    val targetSdkVersion = SdkVersions.targetSdkVersion
    val compileSdkVersion = SdkVersions.compileSdkVersion
    val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    val versionName = "\"1.0.0\""
}

/*
 * Auto generated buildConfig fileds
 */
object Fields {
    val rootUrl = "ROOT_URL"
    val pName = "PACKAGE_NAME"
    val hostName = "HOST_NAME"
    val vName = "vName"
}

/*
 * Flavor Dimensions
 */
object Dimensions {
    val default = "default"
}

object Dev {
    val suffix = ".dev"
    val versionNameSuffix = suffix
    val applicationIdSuffix = suffix
    val packageName = Config.applicationId + suffix
}

