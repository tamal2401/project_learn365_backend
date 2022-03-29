package org.learn365.file.uploader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Identifier {
    private String key;
    private Object val;
}
