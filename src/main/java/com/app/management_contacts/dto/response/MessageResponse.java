package com.app.management_contacts.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MessageResponse<T> {

    private String message;

    private int status;

    private T data;

    private Long time = System.currentTimeMillis();

    @JsonProperty("error_detail")
    private Map<String, String> errorDetail;

    private Long total;

    public static MessageResponse<Void> ofSuccess() {
        MessageResponse<Void> result = new MessageResponse<>();
        result.status = HttpStatus.OK.value();
        result.message = HttpStatus.OK.name();
        return result;
    }

    public static <T> MessageResponse<T> ofSuccess(T data) {
        MessageResponse<T> result = new MessageResponse<>();
        result.status = HttpStatus.OK.value();
        result.message = HttpStatus.OK.name();
        result.data = data;
        return result;
    }

    public static <T> MessageResponse<T> ofSuccess(Page<T> dataPage) {
        MessageResponse<T> result = new MessageResponse<>();
        result.setStatus(HttpStatus.OK.value());
        result.setMessage(HttpStatus.OK.name());
        MessageListData<T> data = new MessageListData<>();
        data.setItems(dataPage.getContent());
        data.setTotal(dataPage.getTotalElements());
        result.setData((T) data);
        return result;
    }

    public static <T> MessageResponse<T> ofError(int status, String message, Map<String, String> data) {
        MessageResponse<T> result = new MessageResponse<>();
        result.status = status;
        result.message = message;
        result.errorDetail = data;
        return result;
    }
}
