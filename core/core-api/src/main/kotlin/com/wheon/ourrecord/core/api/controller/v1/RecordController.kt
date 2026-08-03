package com.wheon.ourrecord.core.api.controller.v1

import com.wheon.ourrecord.core.api.assembler.RecordAssembler
import com.wheon.ourrecord.core.api.controller.v1.request.AddRecordRequest
import com.wheon.ourrecord.core.api.controller.v1.request.ModifyRecordRequest
import com.wheon.ourrecord.core.api.controller.v1.response.RecordResponse
import com.wheon.ourrecord.core.domain.record.RecordImageValidator
import com.wheon.ourrecord.core.domain.user.User
import com.wheon.ourrecord.core.enums.ResourceType
import com.wheon.ourrecord.core.support.OffsetLimit
import com.wheon.ourrecord.core.support.file.FileUploader
import com.wheon.ourrecord.core.support.file.StorageServe
import com.wheon.ourrecord.core.support.response.ApiResponse
import com.wheon.ourrecord.core.support.response.PageResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class RecordController(
    private val recordAssembler: RecordAssembler,
    private val recordImageValidator: RecordImageValidator,
    private val fileUploader: FileUploader,
) {
    @GetMapping("/api/v1/records")
    fun getRecords(
        user: User,
        @RequestParam placeId: Long,
        @RequestParam offset: Int?,
        @RequestParam limit: Int?,
    ): ApiResponse<PageResponse<RecordResponse>> {
        val responses = recordAssembler.getRecords(user, placeId, OffsetLimit(offset, limit))
        return ApiResponse.success(responses)
    }

    @PostMapping(
        value = ["/api/v1/records/upload/picture"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun uploadRecordPicture(
        user: User,
        @RequestPart file: MultipartFile,
    ): ApiResponse<String> {
        recordImageValidator.validate(file)
        val uploadResult = fileUploader.uploadFile(
            resourceType = ResourceType.RECORD,
            file = file,
        )
        return ApiResponse.success(
            StorageServe.CDN + uploadResult.path,
        )
    }

    @PostMapping("/api/v1/records")
    fun addRecord(
        user: User,
        @RequestBody request: AddRecordRequest,
    ): ApiResponse<Long> {
        val successId = recordAssembler.addRecord(
            user = user,
            target = request.toTarget(),
            content = request.toContent(),
            mediaHandle = request.toMediaHandle(),
        )
        return ApiResponse.success(successId)
    }

    @PutMapping("/api/v1/records/{recordId}")
    fun modifyRecord(
        user: User,
        @PathVariable recordId: Long,
        @RequestBody request: ModifyRecordRequest,
    ): ApiResponse<Long> {
        val successId = recordAssembler.modifyRecord(
            user = user,
            recordId = recordId,
            content = request.toContent(),
            mediaHandle = request.toMediaHandle(),
        )
        return ApiResponse.success(successId)
    }
}
