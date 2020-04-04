/*
 * App Modules
 */

object Modules {
     // App
     const val app = ":app"

     //Directory
     private const val BASE_DIRECTORY = ":base"

     //Library
     const val core = "$BASE_DIRECTORY:core"
     const val appCore = "$BASE_DIRECTORY:app_core"
     const val appDomain = "$BASE_DIRECTORY:app_domain"
     const val appPresentation = "$BASE_DIRECTORY:app_presentation"
}
