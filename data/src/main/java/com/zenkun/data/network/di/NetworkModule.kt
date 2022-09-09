package com.zenkun.data.network.di

import com.apollographql.apollo3.ApolloClient
import com.zenkun.data.network.GraphqlDataSource
import com.zenkun.data.network.GraphqlDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindsGraphqlDataSource(impl: GraphqlDataSourceImpl): GraphqlDataSource

    companion object {

        @Provides
        @Singleton
        fun getApolloClient(): ApolloClient {
            return ApolloClient.Builder()
                .serverUrl("https://app.tibber.com/v4/gql")
                .build()
        }

    }
}

