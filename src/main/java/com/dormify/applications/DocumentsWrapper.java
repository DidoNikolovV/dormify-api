package com.dormify.applications;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DocumentsWrapper {
    @Schema(description = "Profile picture of the applicant")
    private MultipartFile profilePicture;
    @Schema(
            description = "Proof of payment for the application fee. This is optional and can be uploaded later if not available at submission.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "bank_transfer_receipt.jpg"
    )
    private MultipartFile proofOfPayment;

    @Schema(description = "Certificate of enrolled semester")
    private MultipartFile semesterAssurance;

    @Schema(
            description = "Official marriage certificate. Required only for married students.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "marriage_cert.pdf"
    )
    private MultipartFile marriageCertificate;

    private List<MultipartFile> birthCertificates = new ArrayList<>();
    @Schema(
            description = "Official disability document. Used for priority ranking in the allocation process.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "disability_doc.pdf"
    )
    private MultipartFile disabilityDocument;

    public DocumentsWrapper(MultipartFile semesterAssurance) {
        this.semesterAssurance = semesterAssurance;
    }
}
