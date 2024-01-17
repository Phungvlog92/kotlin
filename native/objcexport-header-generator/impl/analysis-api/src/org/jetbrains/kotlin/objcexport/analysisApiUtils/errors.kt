package org.jetbrains.kotlin.objcexport.analysisApiUtils

import org.jetbrains.kotlin.analysis.api.types.KtClassErrorType
import org.jetbrains.kotlin.analysis.api.types.KtType
import org.jetbrains.kotlin.backend.konan.objcexport.*


internal const val errorClassName = "ERROR"
internal val objCErrorType = ObjCClassType(errorClassName)

/**
 * Traverses stubs and returns true if [objCErrorType] is used as a return, parameter or property type
 */
internal fun List<ObjCExportStub>.hasErrorTypes(): Boolean {
    forEach { stub ->
        if (stub is ObjCClass) {
            if (stub.members.hasErrorTypes()) return true
        } else if (stub is ObjCProperty) {
            if (stub.type == objCErrorType) return true
        } else if (stub is ObjCMethod) {
            if (stub.returnType == objCErrorType) return true
            stub.parameters.forEach { parameter ->
                if (parameter.type == objCErrorType) return true
            }
        }
    }
    return false
}

internal val KtType.isError
    get() = this is KtClassErrorType

internal val errorInterface = ObjCInterfaceImpl(
    errorClassName,
    null,
    null,
    emptyList(),
    emptyList(),
    emptyList(),
    null,
    emptyList(),
    defaultSuperClass,
    emptyList()
)

internal val errorForwardClass = ObjCClassForwardDeclaration(errorClassName)