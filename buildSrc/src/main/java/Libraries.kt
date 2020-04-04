/**
 * Common Libraries
 */
object Libraries {
    val dagger2AndroidSupport =
        "com.google.dagger:dagger-android-support:${DependencyVersions.dagger2Version}"
    val dagger2Compiler = "com.google.dagger:dagger-compiler:${DependencyVersions.dagger2Version}"
    val dagger2AndroidProcessor =
        "com.google.dagger:dagger-android-processor:${DependencyVersions.dagger2Version}"
    val javaxAnnotation =
        "org.glassfish:javax.annotation:${DependencyVersions.javaxAnnotationVersion}"
    val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.coroutinesVersion}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutinesVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${DependencyVersions.retrofitVersion}"
    val logInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.okHttpLoggingInterceptorVersion}"
    val gson = "com.squareup.retrofit2:converter-gson:${DependencyVersions.gsonVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${DependencyVersions.okHttpVersion}"
    val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependencyVersions.retrofitCoroutineAdapterVersion}"
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${DependencyVersions.archVersion}"
    val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${DependencyVersions.archVersion}"
    val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.archVersion}"
}
