package com.bagmanovam.data.di

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bagmanovam.data.db.NewsDatabase
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
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

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

    single<NewsDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = NewsDatabase::class.java,
            name = "newsDatabase"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("Room", "onCreate: ")
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.i("Room", "onOpen: ${db.path}")
                }

                override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                    super.onDestructiveMigration(db)
                    Log.i("Room", "onDestructiveMigration: ${db.version}")
                }
            })
            .build()
    }

    single {
        get<NewsDatabase>().dao()
    }
}