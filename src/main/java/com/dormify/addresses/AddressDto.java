package com.dormify.addresses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    @Schema(description = "Street number of the address", example = "20A")
    private String streetNumber;

    @Schema(description = "Street name of the address", example = "Mitropoligt Grigoriy")
    private String streetName;

    @Schema(description = "City of the address", example = "Sofia")
    private String city;

    @Schema(description = "Region or province of the current address", example = "Sofia")
    private String region;

    @Schema(description = "Country of the current address", example = "Bulgaria")
    private String country;

    @Schema(description = "Entrance or door identifier", example = "A")
    private String entrance;

    @Schema(description = "Floor number", example = "3")
    private Integer floor;

    @Schema(description = "Municipality of the current address", example = "Sofia Municipality", required = true)
    private String municipality;

    @Schema(description = "Apartment number or identifier", example = "5B")
    private String apartment;
}
