package com.example.clock.form;


import com.example.clock.annotation.SensitiveFields;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoFrom {

    private Long gid;

    private Long uid;

    @SensitiveFields
    private String mobile;

    @SensitiveFields
    private String email;

    private Integer status;

    @SensitiveFields
    private String name;

    private String sign;

    private Integer start;

    private Integer size;
}
