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

     //Dashboard
     const val dashBoard = ":dashboard"
     const val dashBoardData = "$dashBoard:dashboard_data"
     const val dashBoardDomain = "$dashBoard:dashboard_domain"
     const val dashBoardPresentation = "$dashBoard:dashboard_presentation"
}
