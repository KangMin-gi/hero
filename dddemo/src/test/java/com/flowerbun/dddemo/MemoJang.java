package com.flowerbun.dddemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MemoJang {

    @Test
    public void enumToJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DAS> das = Arrays.asList(DAS.values());
        System.out.println(das);
        String s = objectMapper.writeValueAsString(das);


    }

    @JsonFormat(shape = Shape.OBJECT)
    enum DAS {
        FIRST("a1", "FIR"),
        SECOND("a2", "SEC");

        private String first;
        private String second;

        DAS(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }
}
