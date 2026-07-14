package com.wheon.ourrecord.api.controller.v1

import com.wheon.ourrecord.api.controller.v1.request.AddRecordRequest
import com.wheon.ourrecord.domain.record.RecordImageValidator
import com.wheon.ourrecord.domain.record.RecordService
import com.wheon.ourrecord.domain.user.CoupleUser
import com.wheon.ourrecord.support.file.FileUploader
import com.wheon.ourrecord.support.file.ResourceType
import com.wheon.ourrecord.support.response.ApiResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class RecordController(
    private val recordService: RecordService,
    private val fileUploader: FileUploader,
    private val recordImageValidator: RecordImageValidator,
) {
    @PostMapping(
        value = ["/v1/records/upload/picture"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun uploadRecordPicture(
        coupleUser: CoupleUser,
        @RequestPart file: MultipartFile,
    ): ApiResponse<String> {
        recordImageValidator.validate(file)
        return ApiResponse.success(fileUploader.uploadFile(ResourceType.RECORD, file).url)
    }

    @PostMapping("/v1/records")
    fun addRecord(
        coupleUser: CoupleUser,
        @RequestBody request: AddRecordRequest,
    ): ApiResponse<Long> {
        val recordId = recordService.create(
            coupleUser = coupleUser,
            target = request.toTarget(),
            content = request.toContent(),
            media = request.toMedia(),
        )
        return ApiResponse.success(recordId)
    }
}
