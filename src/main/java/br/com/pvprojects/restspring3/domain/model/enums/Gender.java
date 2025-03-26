package br.com.pvprojects.restspring3.domain.model.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum Gender {

    M(0),
    F(1),

    I(2);

    private Integer id;

    Gender(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private static final Map<Integer, Gender> map = new HashMap<>(values().length, 1);

    static {
        for (Gender c : values()) {
            map.put(c.id, c);
        }
    }

    public static Gender of(Integer value) {
        Gender result = map.get(value);

        if (Objects.isNull(result)) {
            throw new RuntimeException("Invalid gender.");
//            BusinessException.of(JnevesErrorCode.ENUM_ERROR, String.valueOf(value));
        }

        return result;
    }
}
