package co.touchlab.kampkit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.test.runTest as coroutinesRunTest

actual abstract class BaseTest {
    actual fun <T> runTest(block: suspend CoroutineScope.() -> T) {
        coroutinesRunTest { block() }
    }
}
