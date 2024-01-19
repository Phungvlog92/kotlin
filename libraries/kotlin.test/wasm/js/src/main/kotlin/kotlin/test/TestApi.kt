/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.test

private fun isJasmine(): Boolean =
    js("typeof describe === 'function' && typeof it === 'function'")

@JsName("kotlinTest")
internal external val kotlinTestNamespace: ExternalKotlinTestNamespace

internal external interface ExternalKotlinTestNamespace : JsAny {
    public val adapterTransformer: ((ExternalFrameworkAdapter) -> ExternalFrameworkAdapter)?
}

private val adapter: FrameworkAdapter by lazy {
    if (!isJasmine()) return@lazy TeamcityAdapterWithPromiseSupport()

    val jasmineLikeAdapter = JasmineLikeAdapter()
    val transformer = kotlinTestNamespace.adapterTransformer ?: return@lazy jasmineLikeAdapter
    transformer(jasmineLikeAdapter.externalize()).internalize()
}

internal actual fun adapter(): FrameworkAdapter = adapter