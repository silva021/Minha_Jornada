package com.silva021.minhajornada.di

import com.silva021.minhajornada.data.api.ChallengesApi
import com.silva021.minhajornada.data.api.CommunitiesApi
import com.silva021.minhajornada.data.api.ProfileApi
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.ChallengesRepositoryImpl
import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.data.repository.CommunitiesRepositoryImpl
import com.silva021.minhajornada.data.repository.ProfileRepository
import com.silva021.minhajornada.data.repository.ProfileRepositoryImpl
import com.silva021.minhajornada.domain.usecases.GetChallengesUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunitiesUseCase
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.ui.screens.challenges.ChallengesViewModel
import com.silva021.minhajornada.ui.screens.communities.CommunitiesViewModel
import com.silva021.minhajornada.ui.screens.profile.ProfileViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ChallengesViewModel(get()) }
    viewModel { CommunitiesViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}

val usecasesModule = module {
    factory { GetChallengesUseCase(get()) }
    factory { GetMyProfileUseCase(get()) }
    factory { GetCommunitiesUseCase(get()) }
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

    single<ChallengesApi> { get<Retrofit>().create(ChallengesApi::class.java) }
    single<ProfileApi> { get<Retrofit>().create(ProfileApi::class.java) }
    single<CommunitiesApi> { get<Retrofit>().create(CommunitiesApi::class.java) }

    single<ChallengeRepository> { ChallengesRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<CommunitiesRepository> { CommunitiesRepositoryImpl(get()) }

}
