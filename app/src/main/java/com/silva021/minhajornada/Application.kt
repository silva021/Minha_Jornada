package com.silva021.minhajornada

import android.app.Application
import com.silva021.minhajornada.di.repositoryModule
import com.silva021.minhajornada.di.usecasesModule
import com.silva021.minhajornada.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            printLogger()
            modules(
                viewModelModule,
                usecasesModule,
                repositoryModule
            )
        }
    }
}