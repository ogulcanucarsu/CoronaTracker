package org.ucarsu.coronaexample.injection.modules

import dagger.Module
import org.ucarsu.coronaexample.dashboard.domain.DashBoardDomainModule

@Module(
    includes = [
        DashBoardDomainModule::class
    ]
)
internal class DomainModule {
    //no-op
}
