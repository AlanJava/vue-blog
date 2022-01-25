package cn.tgkzxy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Logs {
    private int lid;
    private long time;
    private String ip;
    private String action;
}
