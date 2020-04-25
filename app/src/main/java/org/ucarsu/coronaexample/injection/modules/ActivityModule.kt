package org.ucarsu.coronaexample.injection.modules

import dagger.Module
import org.ucarsu.coronaexample.dashboard.presentation.DashBoardActivityModule

@Module(
    includes = [
        DashBoardActivityModule::class
    ]
)
abstract class ActivityModule{
    //no-op
}