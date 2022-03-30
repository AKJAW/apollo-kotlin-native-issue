package co.touchlab.kampkit.graphql

import co.touchlab.kampkit.LaunchesPastQuery
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class BrokenTest {

    @Test
    fun `ios fails`() = runTest(dispatchTimeoutMs = 5000) {
        val client = MockApolloWrapper().client

        val result = client.query(LaunchesPastQuery()).execute()

        println(result)
    }
}