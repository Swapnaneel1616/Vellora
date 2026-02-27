package com.vellora.sb_ecom.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
    public String message;
    public boolean status;

}
