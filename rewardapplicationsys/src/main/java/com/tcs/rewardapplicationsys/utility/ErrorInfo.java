package com.tcs.rewardapplicationsys.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
    String exceptionMessage;
    String errorCode;
}
