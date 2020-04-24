package data.modules.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultRequestInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().newBuilder()) {
            addHeader("content-type", "application/json")
            addHeader("authorization", "apikey 1P3B5u1IfhyTXFVzcQIu2O:10AaGpiIXp25kqdRzuxNby")
            build()
        })
    }
}