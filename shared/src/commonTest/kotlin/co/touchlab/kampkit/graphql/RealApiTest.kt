package co.touchlab.kampkit.graphql

import co.touchlab.kampkit.BaseTest
import co.touchlab.kampkit.LaunchesPastQuery
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.LoggingInterceptor
import kotlin.test.Test
import kotlinx.coroutines.test.runTest as coroutinesRunTest

class RealApiTest : BaseTest() {

    private val realClient = ApolloClient.Builder()
        .serverUrl("https://api.spacex.land/graphql/")
        .addHttpInterceptor(
            LoggingInterceptor { println("SpaceX API: $it") }
        )
        .build()

    @Test
    fun `ios fails with base test`() = runTest() {
        val result = realClient.query(LaunchesPastQuery()).execute()

        println(result)
    }
    @Test
    fun `ios fails with coroutines test`() = coroutinesRunTest(dispatchTimeoutMs = 5000) {
        val result = realClient.query(LaunchesPastQuery()).execute()

        println(result)
    }
}