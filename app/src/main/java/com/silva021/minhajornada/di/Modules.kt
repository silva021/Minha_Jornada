package com.silva021.minhajornada.di

import com.silva021.minhajornada.data.api.ChallengesApi
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.ChallengesRepositoryImpl
import com.silva021.minhajornada.domain.usecases.GetChallengesUseCase
import com.silva021.minhajornada.ui.screens.challenges.ChallengesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ChallengesViewModel(get()) }
}

val usecasesModule = module {
    factory { GetChallengesUseCase(get()) }
}

val repositoryModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
//            .baseUrl(BuildConfig.JSON_SERVER_URL)
            .baseUrl("https://minha-jornada-debug-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<ChallengesApi> {
        get<Retrofit>().create(ChallengesApi::class.java)
    }

    single<ChallengeRepository> { ChallengesRepositoryImpl(get()) }
}

