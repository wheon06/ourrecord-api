package com.wheon.ourrecord.support.file

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MediaAssetUrlResolver(
    @param:Value($$"${s3.endpoint}") private val endpoint: String,
) {
    fun resolve(bucket: String, objectKey: String): String {
        return "${endpoint.trimEnd('/')}/${bucket.trim('/')}/${objectKey.trimStart('/')}"
    }
}
