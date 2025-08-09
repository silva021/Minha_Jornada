package com.silva021.minhajornada.di

import com.silva021.minhajornada.data.api.ChallengesApi
import com.silva021.minhajornada.data.api.CommunitiesApi
import com.silva021.minhajornada.data.api.ProfileApi
import com.silva021.minhajornada.data.repository.ChallengeRepository
import com.silva021.minhajornada.data.repository.ChallengesRepositoryImpl
import com.silva021.minhajornada.data.repository.CheckInRepository
import com.silva021.minhajornada.data.repository.CheckInRepositoryImpl
import com.silva021.minhajornada.data.repository.CommunitiesRepository
import com.silva021.minhajornada.data.repository.CommunitiesRepositoryImpl
import com.silva021.minhajornada.data.repository.ProfileRepository
import com.silva021.minhajornada.data.repository.ProfileRepositoryImpl
import com.silva021.minhajornada.data.repository.PublicChallengeRepository
import com.silva021.minhajornada.data.repository.PublicChallengeRepositoryImpl
import com.silva021.minhajornada.data.repository.ReminderRepository
import com.silva021.minhajornada.data.repository.ReminderRepositoryImpl
import com.silva021.minhajornada.domain.usecases.AcceptPublicChallengeUseCase
import com.silva021.minhajornada.domain.usecases.CompleteChallengeUseCase
import com.silva021.minhajornada.domain.usecases.CreateChallengeUseCase
import com.silva021.minhajornada.domain.usecases.CreateCheckInUseCase
import com.silva021.minhajornada.domain.usecases.CreateProfileUseCase
import com.silva021.minhajornada.domain.usecases.CreatePublicChallengeUseCase
import com.silva021.minhajornada.domain.usecases.CreateReminderUseCase
import com.silva021.minhajornada.domain.usecases.DeleteChallengeUseCase
import com.silva021.minhajornada.domain.usecases.DeleteReminderUseCase
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetChallengesUseCase
import com.silva021.minhajornada.domain.usecases.GetCommentsByPostIdUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunitiesByCategoryUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunitiesUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityPostByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityPostsUseCase
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.domain.usecases.GetPublicChallengeByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetPublicChallengesByCategoryUseCase
import com.silva021.minhajornada.domain.usecases.GetPublicChallengesUseCase
import com.silva021.minhajornada.domain.usecases.GetReminderByIdUseCase
import com.silva021.minhajornada.domain.usecases.LoginUseCase
import com.silva021.minhajornada.domain.usecases.UpdateReminderUseCase
import com.silva021.minhajornada.ui.screens.challenges.actives.ActiveChallengesViewModel
import com.silva021.minhajornada.ui.screens.challenges.completed.ChallengeCompletedViewModel
import com.silva021.minhajornada.ui.screens.challenges.create.CreateChallengeViewModel
import com.silva021.minhajornada.ui.screens.challenges.mine.ChallengesViewModel
import com.silva021.minhajornada.ui.screens.challenges.reminders.RemindersViewModel
import com.silva021.minhajornada.ui.screens.challenges.reminders.create.CreateReminderViewModel
import com.silva021.minhajornada.ui.screens.challenges.summary.ChallengeSummaryViewModel
import com.silva021.minhajornada.ui.screens.challenges.update.UpdateChallengeProgressViewModel
import com.silva021.minhajornada.ui.screens.communities.details.CommunityDetailsViewModel
import com.silva021.minhajornada.ui.screens.communities.feed.CommunityFeedViewModel
import com.silva021.minhajornada.ui.screens.communities.list.CommunitiesViewModel
import com.silva021.minhajornada.ui.screens.communities.post.CommunityPostViewModel
import com.silva021.minhajornada.ui.screens.explorer.challengedetails.ExplorerChallengeDetailsViewModel
import com.silva021.minhajornada.ui.screens.explorer.list.ExplorerViewModel
import com.silva021.minhajornada.ui.screens.login.LoginViewModel
import com.silva021.minhajornada.ui.screens.login.signup.SignUpViewModel
import com.silva021.minhajornada.ui.screens.profile.ProfileViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { ChallengesViewModel(get(), get()) }
    viewModel { CommunitiesViewModel(get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ExplorerViewModel(get(), get()) }
    viewModel { CommunityDetailsViewModel(get()) }
    viewModel { CommunityFeedViewModel(get(), get(), get()) }
    viewModel { CommunityPostViewModel(get(), get(), get()) }
    viewModel { RemindersViewModel(get(), get()) }
    viewModel { CreateReminderViewModel(get(), get(), get()) }
    viewModel { CreateChallengeViewModel(get()) }
    viewModel { ActiveChallengesViewModel(get()) }
    viewModel { ChallengeSummaryViewModel(get()) }
    viewModel { UpdateChallengeProgressViewModel(get(), get()) }
    viewModel { ChallengeCompletedViewModel(get()) }
    viewModel { ExplorerChallengeDetailsViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

val usecasesModule = module {
    factory { LoginUseCase() }

    factory { GetChallengesUseCase(get()) }
    factory { GetChallengeByIdUseCase(get()) }
    factory { GetPublicChallengeByIdUseCase(get()) }
    factory { CreateChallengeUseCase(get()) }
    factory { CreateReminderUseCase(get()) }
    factory { DeleteReminderUseCase(get()) }
    factory { DeleteChallengeUseCase(get()) }
    factory { GetReminderByIdUseCase(get()) }
    factory { UpdateReminderUseCase(get()) }
    factory { CompleteChallengeUseCase(get(), get()) }
    factory { GetMyProfileUseCase(get()) }
    factory { CreateProfileUseCase(get()) }
    factory { CreateCheckInUseCase(get()) }
    factory { CreatePublicChallengeUseCase(get()) }
    factory { AcceptPublicChallengeUseCase(get(), get()) }
    factory { GetCommunitiesUseCase(get()) }
    factory { GetCommunitiesByCategoryUseCase(get()) }
    factory { GetCommunityByIdUseCase(get()) }
    factory { GetCommunityPostsUseCase(get()) }
    factory { GetCommunityPostByIdUseCase(get()) }
    factory { GetCommentsByPostIdUseCase(get()) }

    factory { GetPublicChallengesUseCase(get()) }
    factory { GetPublicChallengesByCategoryUseCase(get()) }
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
            .baseUrl("https://minha-jornada-debug-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<ChallengesApi> { get<Retrofit>().create(ChallengesApi::class.java) }
    single<CommunitiesApi> { get<Retrofit>().create(CommunitiesApi::class.java) }

    single<ChallengeRepository> { ChallengesRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<CommunitiesRepository> { CommunitiesRepositoryImpl(get()) }
    single<CheckInRepository> { CheckInRepositoryImpl() }
    single<ReminderRepository> { ReminderRepositoryImpl() }
    single<PublicChallengeRepository> { PublicChallengeRepositoryImpl() }
}
