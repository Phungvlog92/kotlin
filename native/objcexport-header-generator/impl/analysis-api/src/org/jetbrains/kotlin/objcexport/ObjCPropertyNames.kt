package org.jetbrains.kotlin.objcexport

import org.jetbrains.kotlin.backend.konan.InternalKotlinNativeApi

/**
 * [org.jetbrains.kotlin.backend.konan.objcexport.ObjCExportNamer]
 */
internal object ObjCPropertyNames {
    @InternalKotlinNativeApi
    const val kotlinThrowableAsErrorMethodName: String = "asError"

    @InternalKotlinNativeApi
    const val objectPropertyName: String = "shared"

    @InternalKotlinNativeApi
    const val companionObjectPropertyName: String = "companion"
}