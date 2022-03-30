package co.touchlab.kampkit.graphql

import co.touchlab.kampkit.BaseTest
import co.touchlab.kampkit.LaunchesPastQuery
import kotlin.test.Test

class WorkingTest : BaseTest() {

    @Test
    fun `ios passes`() = runTest() {
        val client = MockApolloWrapper().client

        val result = client.query(LaunchesPastQuery()).execute()

        println(result)
    }
}