package com.mercadolibre.varela_agustina.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderDTORequest {

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "'deliverydate' no puede ser nulo")
    private LocalDate deliverydate;

    @NotNull(message = "'serialnumber' no puede ser nulo")
    private String serialnumber;

    @NotNull(message = "'idshipping' no puede ser nulo")
    @Positive(message = "'idshipping' no puede ser nulo o negativo")
    private Integer idshipping;

    @NotNull(message = "'ordertype' no puede ser nulo")
    @Positive(message = "'ordertype' no puede ser nulo o negativo, tiene que ser 1(compra) y 2 venta(2)")
    private Integer ordertype;


    @NotNull(message = "'parts' no puede ser nulo")
    @Valid
    private List<NewPartOrderDTORequest> parts;
}
