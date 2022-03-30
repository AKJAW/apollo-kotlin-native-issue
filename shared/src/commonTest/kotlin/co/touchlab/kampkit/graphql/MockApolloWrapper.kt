package co.touchlab.kampkit.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.apollographql.apollo3.network.http.LoggingInterceptor
import okio.ByteString.Companion.encodeUtf8

class MockApolloWrapper {

    val client = ApolloClient.Builder()
        .serverUrl("https://api.spacex.land/graphql/")
        .addHttpInterceptor(
            LoggingInterceptor { println("SpaceX API: $it") }
        )
        .addHttpInterceptor(MockInterceptor())
        .build()
}

class MockInterceptor : HttpInterceptor {

    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        val operation = request.headers.first { it.name == "X-APOLLO-OPERATION-NAME" }.value

        if (operation == "LaunchesPast") {
            val response = successResponse
            return HttpResponse.Builder(statusCode = 200)
                .body(response.encodeUtf8())
                .build()
        }

        return chain.proceed(request)
    }
}


private val successResponse = """
    {
      "data": {
        "launchesPast": [
          {
            "mission_name": "Starlink-15 (v1.0)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Sentinel-6 Michael Freilich",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Crew-1",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "GPS III SV04 (Sacagawea)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Starlink-14 (v1.0)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Starlink-13 (v1.0)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Starlink-12 (v1.0)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Starlink-11 (v1.0)",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "SAOCOM 1B, GNOMES-1, Tyvak-0172",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          },
          {
            "mission_name": "Starlink-10 (v1.0) & SkySat 19-21",
            "rocket": {
              "rocket_name": "Falcon 9"
            }
          }
        ]
      }
    }
""".trimIndent()
