package com.bagmanovam.data.di

import com.bagmanovam.data.repository.NewsRepositoryImpl
import com.bagmanovam.data.repository.SettingsRepositoryImpl
import com.bagmanovam.domain.interactor.AddSubscriptionInteractor
import com.bagmanovam.domain.interactor.ClearAllArticlesInteractor
import com.bagmanovam.domain.interactor.GetAllSubscriptionsInteractor
import com.bagmanovam.domain.interactor.GetArticlesByTopicsUseCaseInteractor
import com.bagmanovam.domain.interactor.GetSettingsInteractor
import com.bagmanovam.domain.interactor.RemoveSubscriptionInteractor
import com.bagmanovam.domain.interactor.StartRefreshDataInteractor
import com.bagmanovam.domain.interactor.UpdateArticlesForAllSubscriptionsInteractor
import com.bagmanovam.domain.interactor.UpdateNotificationsEnabledInteractor
import com.bagmanovam.domain.interactor.UpdateThemeInteractor
import com.bagmanovam.domain.interactor.UpdateWifiOnlyInteractor
import com.bagmanovam.domain.repository.NewsRepository
import com.bagmanovam.domain.repository.SettingsRepository
import com.bagmanovam.domain.usecase.AddSubscriptionUseCase
import com.bagmanovam.domain.usecase.ClearAllArticlesUseCase
import com.bagmanovam.domain.usecase.GetAllSubscriptionsUseCase
import com.bagmanovam.domain.usecase.GetArticlesByTopicsUseCase
import com.bagmanovam.domain.usecase.GetSettingsUseCase
import com.bagmanovam.domain.usecase.RemoveSubscriptionUseCase
import com.bagmanovam.domain.usecase.StartRefreshDataUseCase
import com.bagmanovam.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import com.bagmanovam.domain.usecase.UpdateNotificationsEnabledUseCase
import com.bagmanovam.domain.usecase.UpdateThemeUseCase
import com.bagmanovam.domain.usecase.UpdateWifiOnlyUseCase
import com.bagmanovam.news.core.database.di.databaseModule
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule)

    singleOf(::NewsRepositoryImpl).bind<NewsRepository>()
    singleOf(::SettingsRepositoryImpl).bind<SettingsRepository>()

    factoryOf(::AddSubscriptionInteractor).bind<AddSubscriptionUseCase>()
    factoryOf(::ClearAllArticlesInteractor).bind<ClearAllArticlesUseCase>()
    factoryOf(::GetAllSubscriptionsInteractor).bind<GetAllSubscriptionsUseCase>()
    factoryOf(::GetArticlesByTopicsUseCaseInteractor).bind<GetArticlesByTopicsUseCase>()
    factoryOf(::RemoveSubscriptionInteractor).bind<RemoveSubscriptionUseCase>()
    factoryOf(::UpdateArticlesForAllSubscriptionsInteractor).bind<UpdateArticlesForAllSubscriptionsUseCase>()
    factoryOf(::StartRefreshDataInteractor).bind<StartRefreshDataUseCase>()
    factoryOf(::GetSettingsInteractor).bind<GetSettingsUseCase>()
    factoryOf(::UpdateNotificationsEnabledInteractor).bind<UpdateNotificationsEnabledUseCase>()
    factoryOf(::UpdateWifiOnlyInteractor).bind<UpdateWifiOnlyUseCase>()
    factoryOf(::UpdateThemeInteractor).bind<UpdateThemeUseCase>()
}
