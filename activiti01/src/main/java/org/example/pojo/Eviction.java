package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eviction implements Serializable {
    Long id;
    String evictionName;
    Double num;
    Date beginDate;
    Date endDate;
    String reason;
}
