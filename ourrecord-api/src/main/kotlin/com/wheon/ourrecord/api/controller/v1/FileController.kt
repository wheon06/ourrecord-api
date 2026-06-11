package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.support.ApiCoupleUser
import com.wheon.ourrecord.support.file.FileUploader
import com.wheon.ourrecord.support.file.UploadResult
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController(
    private val fileUploader: FileUploader,
) {
    @PostMapping("/v1/files")
    fun upload(
        apiCoupleUser: ApiCoupleUser,
        @RequestParam file: MultipartFile,
    ): ApiResponse<UploadResult> {
        return ApiResponse.success(fileUploader.uploadFile(apiCoupleUser, file))
    }
}
